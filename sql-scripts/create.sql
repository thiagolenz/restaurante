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

create table Feedback (
	id bigint NOT NULL,
	userGaveId bigint NOT NULL,
	userReceivedId bigint NOT NULL,
	score numeric (8,2) not null,
	message varchar (500) not null,
	feedbackDate timestamp not null
);

create table FeedbackAverage (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	averageValue numeric (8,2) not null
);

create table LocationTrack (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	latitude varchar (50) not null,
	longitude varchar (50) not null,
	trackDate timestamp not null
);

create table CellPhoneRegister (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	regId varchar (500) not null,
	lastConnected timestamp not null,
	type varchar (100) not null
);


create table UserProfile (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	cellPhoneNumber varchar (30),
	cellPhoneType varchar (30),
	whatsAppNumber varchar (30),
	address varchar (255),
	addressType varchar (30),
	city_id bigint,
	country_id bigint,
	province_id bigint,
	zipCode varchar (30),
	contactTwitter boolean default false,
	contactFacebook boolean default false,
	contactGoogle boolean default false,
	contactLinkedin boolean default false,
	facebookUrl varchar (355),
	googleUrl varchar (355),
	twitterUrl varchar (355),
	linkedinUrl varchar (355)
);


create table Orders (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	productName varchar (255) not null,
	productBrand varchar (255),
	productPrice varchar (255),
	productDescription varchar (500),
	productImageBase64 text not null,
	country_destiny_id bigint not null,
	province_destiny_id bigint not null,
	city_destiny_id bigint not null,
	country_origin_id bigint not null,
	bonus numeric (8,2) not null,
	createDate timestamp not null,
	wishDeliveryDate timestamp,
	orderStatus varchar (50) not null
);

create table travel (
	id bigint NOT NULL,
	userId bigint NOT NULL,
	
	country_destiny_id bigint not null,
	province_destiny_id bigint not null,
	city_destiny_id bigint not null,
	
	city_origin_id bigint not null,
	province_origin_id bigint not null,
	country_origin_id bigint not null,
	
	departureDate timestamp not null,
	backDate timestamp,
	confirmed boolean default true,
	canceled boolean default false,
	createDate timestamp not null
);


create table Dealing (
	id bigint NOT NULL,
	travel_id bigint not null,
	order_id bigint not null,
	order_user_id bigint not null,
	traveler_user_id bigint not null,
	requesterPaid boolean default false,
	travelerPaid boolean default false,
	amountTravelerPaid numeric (8,2),
	amountRequesterPaid numeric (8,2),
	createDate timestamp not null
);


create table Location (
	id bigint NOT NULL,
	type varchar (20) not null,
	nameEnglish varchar (255) not null,
	namePortuguese varchar (255) not null,
	nameSpanish varchar (255) not null,
	entityId bigint not null
);	



