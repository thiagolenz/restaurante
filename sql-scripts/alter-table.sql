alter table userprofile add column createDate timestamp;

alter table orders add column removed boolean default false;


alter table Receipt add column otherUserId bigint;
ALTER TABLE Receipt ADD FOREIGN KEY (otherUserId) REFERENCES users(id);


--novos

delete from receipt;
delete from feedbackaverage ;
delete from feedback;
delete from appuserauth;
delete from cellphoneregister ;
delete from dealing;
delete from orderavatar ;
delete from orders;
delete from travel;
delete from useravatar;
delete from locationtrack;
delete from location;
delete from userprofile;
delete from users;
delete from city;
delete from province;
delete from country;

alter table country add column alternativeNames varchar (3000);
alter table province add column countryIso varchar (30);
alter table province add column provinceId varchar (30);

CREATE INDEX UK_COUNTRY_ENGLISH ON country (nameEnglish);
CREATE INDEX UK_COUNTRY_PORTUGUESE ON country (namePortuguese);
CREATE INDEX UK_COUNTRY_SPANISH ON country (nameSpanish);
CREATE INDEX UK_COUNTRY_ALT_NAMES ON country (alternativeNames);

alter table city add column alternativeNames varchar (10000);
alter table city add column importerId varchar (30);

CREATE INDEX UK_CITY_FIND ON CITY (countryIso, provinceAbbreviation, name);

drop table locations;



