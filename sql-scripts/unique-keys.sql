CREATE UNIQUE INDEX UK_USER_EMAIL ON USERS (email, socialMediaId);
CREATE INDEX UK_APP_USER_AUTH ON AppUserAuth (appToken, userToken);
CREATE INDEX UK_CITY ON City (name);
CREATE INDEX UK_PROVINCE ON Province (countryId, abbreviation);

CREATE INDEX UK_CITY_ENGLISH ON Country (nameEnglish);
CREATE INDEX UK_CITY_SPANISH ON Country (nameSpanish);
CREATE INDEX UK_CITY_PORTUGUESE ON Country (namePortuguese);

CREATE UNIQUE INDEX UK_EXTERNAL_ID ON Country (externalId);

CREATE UNIQUE INDEX UK_FEEDBACK_AVERAGE ON FeedbackAverage (userId);

CREATE INDEX UK_FEEDBACK ON Feedback (userReceivedId);

CREATE INDEX UK_LOCATION_TRACK ON LocationTrack (userId);
CREATE INDEX UK_CELLPHONE_REGISTER ON CellPhoneRegister (userId);
CREATE UNIQUE INDEX UK_USER_PROFILE ON UserProfile (userId);

CREATE INDEX UK_ORDERs ON Orders (userId);
CREATE INDEX UK_travel ON Travel (userId);

CREATE INDEX UK_DEALING_ORDER ON DEALING (order_user_id);
CREATE INDEX UK_DEALING_TRAVELER ON DEALING (traveler_user_id);

CREATE INDEX UK_LOCATION_ENGLISH ON Location (nameEnglish);
CREATE INDEX UK_LOCATION_PORTUGUESE ON Location (namePortuguese);
CREATE INDEX UK_LOCATION_SPANISH ON Location (nameSpanish);


CREATE INDEX UK_RECEIPT ON Receipt (userId);

CREATE UNIQUE INDEX UK_USER_AVATAR ON UserAvatar (userId);
