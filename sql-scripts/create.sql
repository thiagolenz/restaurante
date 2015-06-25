create table users (
	id bigint NOT NULL,
	name varchar(255) not null,
	lang varchar (5) not null,
	socialMediaId varchar (255) not null,
	email varchar (50) not null
);

create table AppUserAuth (
	id bigint NOT NULL,
	appToken varchar(255) not null,
	userToken varchar (255) not null,
	userId bigint not null
);

create table City (
	id bigint NOT NULL,
	name varchar (255) not null,
	code varchar (50),
	countryIso varchar (50) not null,
	provinceAbbreviation varchar (50) not null
);

create table Province (
	id bigint NOT NULL,
	name varchar (255) not null,
	abbreviation varchar (10) not null,
	countryId bigint not null
);

create table Country (
	id bigint NOT NULL,
	nameEnglish varchar (255) not null,
	nameSpanish varchar (255) not null,
	namePortuguese varchar (255) not null,
	iso varchar (50) not null,
	un varchar (50) not null,
	externalId bigint not null
);