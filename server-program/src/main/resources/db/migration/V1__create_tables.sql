-- Створення таблиці "city"
CREATE TABLE cities (
    city_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    population INT NOT NULL
);

-- Створення таблиці "airport"
CREATE TABLE airports (
    airport_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) NOT NULL,
    city_id BIGINT NOT NULL,
    is_working_now BOOLEAN NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities(city_id)
);

-- Створення таблиці "aircraft"
CREATE TABLE aircrafts (
    aircraft_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    airline_name VARCHAR(255) NOT NULL,
    number_of_passengers INT NOT NULL
);

-- Створення таблиці "passenger"
CREATE TABLE passengers (
    passenger_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities(city_id)
);

-- Створення таблиці "flight"
CREATE TABLE flights (
    flight_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    departure_airport_id BIGINT NOT NULL,
    arrival_airport_id BIGINT NOT NULL,
    aircraft_id BIGINT NOT NULL,
    flight_date DATETIME NOT NULL,
    passengers_ids TEXT NOT NULL,
    FOREIGN KEY (departure_airport_id) REFERENCES airports(airport_id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airports(airport_id),
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id)
);


