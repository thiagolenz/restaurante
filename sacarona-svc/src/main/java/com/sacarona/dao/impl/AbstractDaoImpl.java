package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.GenericDAO;
import com.sacarona.model.AbstractEntity;

public abstract class AbstractDaoImpl <T extends AbstractEntity> implements GenericDAO <T>{
	@Value("${mongodb.host}" )
	protected String mongodbHost;

	@Value("${mongodb.port}" )
	protected String mongoDbPort;

	@Value("${mongodb.database}" )
	protected String database;
	
	@Value("${mongodb.user}" )
	protected String mongoUser;
	
	@Value("${mongodb.password}" )
	protected String mongoPwd;

	private BulkWriteOperation builder;

	private DB db;

	@Override
	public T insert(T obj) {
		try {
			BasicDBObject doc = new BasicDBObject();
			Long id = getNextSequence(getSequenceName());
			doc.append(SEQUENCE_VALUE, id);
			obj.setId(id);
			mapObject(obj, doc);
			openBulk();
			this.builder.insert(doc);
			this.executeBulk();
			return obj;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update (T obj, Long id) {
		try {
			BasicDBObject destiny = new BasicDBObject ();
			obj.setId(id);

			mapObject(obj, destiny);
			destiny.append(""+ SEQUENCE_VALUE, id);

			BasicDBObject query = new BasicDBObject();
			query.append (SEQUENCE_VALUE, id);
			
			getCollection().update(query, destiny);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T findById(Class<T> clazz, Long id) {
		try {
			DBCollection coll = getCollection();
			BasicDBObject query = new BasicDBObject();
			query.append (SEQUENCE_VALUE, id);
			BasicDBObject result = (BasicDBObject) coll.findOne(query);
			if (result == null)
				return null;
			return mapResult(result);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getNextSequence (String sequenceName) throws UnknownHostException {
		DBCollection coll = getDB().getCollection("sequences");

		BasicDBObject query = new BasicDBObject();
		query.append("name", sequenceName);

		BasicDBObject updateObject = new BasicDBObject();
		updateObject.append("$inc", new BasicDBObject(SEQUENCE_VALUE, 1));

		DBObject result = coll.findAndModify(query, updateObject);
		if (result == null) {
			BasicDBObject basic = new BasicDBObject ().append(SEQUENCE_VALUE, 0L);
			basic.append("name", sequenceName);
			coll.insert(basic);
			result = coll.findAndModify(query, updateObject);
		} 
		return ((Number) result.get(SEQUENCE_VALUE)).longValue();
	}

	@Override
	public void remove(T obj) {
		try {
			DBCollection coll = getCollection();
			BasicDBObject query = new BasicDBObject();
			query.append (SEQUENCE_VALUE, obj.getId());
			DBObject find = coll.findOne(query);
			coll.remove(find);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected DBCollection getCollection() throws UnknownHostException {
		DB db  = getDB();
		return db.getCollection(getCollectionName());
	}

	protected DB getDB () throws UnknownHostException {
		if (this.db == null) {
			MongoCredential credential = MongoCredential.createMongoCRCredential(mongoUser, database, mongoPwd.toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress(mongodbHost), Arrays.asList(credential));
//			MongoClientURI uri = new MongoClientURI("mongodb://"+mongoUser+":"+mongoPwd+"@"+mongodbHost+"/?authSource="+database);
//			MongoClient mongoClient = new MongoClient( mongodbHost , Integer.valueOf(mongoDbPort) );
//			MongoClient mongoClient = new MongoClient(uri);
			this.db = mongoClient.getDB(database);
		}
		return db;
	}

	public void openBulk () throws UnknownHostException {
		this.builder = getCollection().initializeOrderedBulkOperation();
	}

	public void executeBulk () throws UnknownHostException {
		this.builder.execute();
	}
	
	protected ServiceCollectionResponse<T> executeQueryPatination (ServiceRequest<T> request, BasicDBObject query) throws UnknownHostException {
		return executeQueryPatination(request, query, null);
	}
	
	protected ServiceCollectionResponse<T> executeQueryPatination (ServiceRequest<T> request, BasicDBObject query, BasicDBObject sort) throws UnknownHostException {
		DBCursor result = getCollection().find(query);
		if (sort != null)
			result.sort(sort);
		ServiceCollectionResponse<T> response = new ServiceCollectionResponse<>();
		response.setTotalRecordsCount(result.count());
		result = getCollection().find(query).skip(request.getOffset()).limit(request.getRecordsRange());
		if (sort != null)
			result.sort(sort);
		while (result.hasNext()) {
			T item = mapResult((BasicDBObject) result.next());
			response.getDataList().add(item);
		}
		return response;
	}
	
	protected T singleQuery (BasicDBObject query) throws UnknownHostException {
		DBCursor result = getCollection().find(query);
		
		if (result.hasNext()) {
			DBObject next = result.next();
			T bean = mapResult((BasicDBObject) next);
			result.close();
			return bean;
		}
		return null;
	}

	protected abstract String getSequenceName ();

	protected abstract String getCollectionName ();

	protected abstract void mapObject (T source, BasicDBObject destiny);

	protected abstract T mapResult (BasicDBObject source);
}
