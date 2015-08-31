ALTER TABLE AppUserAuth ADD FOREIGN KEY (userId) REFERENCES users(id);
ALTER TABLE Feedback ADD FOREIGN KEY (userGaveId) REFERENCES users(id);
ALTER TABLE Feedback ADD FOREIGN KEY (userReceivedId) REFERENCES users(id);
ALTER TABLE FeedbackAverage ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE LocationTrack ADD FOREIGN KEY (userId) REFERENCES users(id);
ALTER TABLE CellPhoneRegister ADD FOREIGN KEY (userId) REFERENCES users(id);
ALTER TABLE UserProfile ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE UserProfile ADD FOREIGN KEY (city_id) REFERENCES city(id);
ALTER TABLE UserProfile ADD FOREIGN KEY (province_id) REFERENCES province(id);
ALTER TABLE UserProfile ADD FOREIGN KEY (country_id) REFERENCES country(id);



ALTER TABLE Orders ADD FOREIGN KEY (city_destiny_id) REFERENCES city(id);
ALTER TABLE Orders ADD FOREIGN KEY (province_destiny_id) REFERENCES province(id);
ALTER TABLE Orders ADD FOREIGN KEY (country_destiny_id) REFERENCES country(id);
ALTER TABLE Orders ADD FOREIGN KEY (country_origin_id) REFERENCES country(id);
ALTER TABLE Orders ADD FOREIGN KEY (userId) REFERENCES users(id);


ALTER TABLE Travel ADD FOREIGN KEY (city_destiny_id) REFERENCES city(id);
ALTER TABLE Travel ADD FOREIGN KEY (province_destiny_id) REFERENCES province(id);
ALTER TABLE Orders ADD FOREIGN KEY (country_destiny_id) REFERENCES country(id);

ALTER TABLE Travel ADD FOREIGN KEY (city_origin_id) REFERENCES city(id);
ALTER TABLE Travel ADD FOREIGN KEY (province_origin_id) REFERENCES province(id);
ALTER TABLE Travel ADD FOREIGN KEY (country_origin_id) REFERENCES country(id);

ALTER TABLE Travel ADD FOREIGN KEY (userId) REFERENCES users(id);


ALTER TABLE Dealing ADD FOREIGN KEY (order_user_id) REFERENCES users(id);
ALTER TABLE Dealing ADD FOREIGN KEY (traveler_user_id) REFERENCES users(id);

ALTER TABLE Dealing ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE Dealing ADD FOREIGN KEY (travel_id) REFERENCES travel(id);
ALTER TABLE Receipt ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE UserAvatar ADD FOREIGN KEY (userId) REFERENCES users(id);
ALTER TABLE OrderAvatar ADD FOREIGN KEY (orderid) REFERENCES orders(id);










