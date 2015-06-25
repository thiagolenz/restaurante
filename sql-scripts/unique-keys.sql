CREATE UNIQUE INDEX UK_USER_EMAIL ON USERS (email, socialMediaId);
CREATE INDEX UK_APP_USER_AUTH ON AppUserAuth (appToken, userToken);
CREATE INDEX UK_CITY ON City (name);
CREATE INDEX UK_PROVINCE ON City (countryId, abbreviation);

CREATE INDEX UK_CITY_ENGLISH ON Country (nameEnglish);
CREATE INDEX UK_CITY_SPANISH ON Country (nameSpanish);
CREATE INDEX UK_CITY_PORTUGUESE ON Country (namePortuguese);

CREATE UNIQUE INDEX UK_EXTERNAL_ID ON Country (externalId);