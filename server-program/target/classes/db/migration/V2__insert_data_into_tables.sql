-- Вставка міст
INSERT INTO cities (name, country, population) VALUES
('Berlin', 'Germany', 3645000),
('Paris', 'France', 2148000),
('Madrid', 'Spain', 3266000),
('Rome', 'Italy', 2873000),
('Amsterdam', 'Netherlands', 872000);

-- Вставка аеропортів
INSERT INTO airports (name, code, city_id, is_working_now) VALUES
('Berlin Brandenburg Airport', 'BER', 1, TRUE),
('Charles de Gaulle Airport', 'CDG', 2, TRUE),
('Adolfo Suárez Madrid-Barajas Airport', 'MAD', 3, TRUE),
('Leonardo da Vinci International Airport', 'FCO', 4, TRUE),
('Amsterdam Schiphol Airport', 'AMS', 5, TRUE),
('Eindhoven Airport', 'EIN', 5, TRUE),
('Rome Ciampino Airport', 'CIA', 4, TRUE),
('Madrid Cuatro Vientos Airport', 'MCV', 3, FALSE),
('Rome Fiumicino Airport', 'FCO', 4, FALSE);


-- Вставка літаків
INSERT INTO aircrafts (type, airline_name, number_of_passengers) VALUES
('Boeing 737', 'Lufthansa', 150),
('Airbus A320', 'Air France', 180),
('Boeing 777', 'Iberia', 300),
('Airbus A380', 'Emirates', 500),
('Boeing 787', 'KLM', 250),
('Airbus A321', 'Ryanair', 220),
('Boeing 737 Max', 'EasyJet', 170),
('Airbus A319', 'Vueling', 150),
('Boeing 747', 'British Airways', 350),
('Airbus A330', 'Alitalia', 290);

-- Вставка пасажирів
INSERT INTO passengers (first_name, last_name, birth_date, phone_number, city_id) VALUES
('John', 'Doe', '1985-03-15', '+491701234567', 1),
('Emma', 'Smith', '1990-07-22', '+33123456789', 2),
('Olivia', 'Johnson', '1982-11-30', '+34912345678', 3),
('Liam', 'Williams', '1995-05-10', '+390612345678', 4),
('Noah', 'Brown', '1978-09-25', '+31234567890', 5),
('Sophia', 'Davis', '1988-02-12', '+491702345678', 1),
('Jackson', 'Miller', '1980-04-18', '+33123456780', 2),
('Ava', 'Wilson', '1992-06-07', '+34923456789', 3),
('Lucas', 'Moore', '1987-10-20', '+390623456789', 4),
('Mia', 'Taylor', '1993-08-11', '+31245678901', 5),
('Ethan', 'Anderson', '1986-01-29', '+491703456789', 1),
('Isabella', 'Thomas', '1991-03-12', '+33134567890', 2),
('Aiden', 'Jackson', '1984-07-30', '+34934567890', 3),
('Charlotte', 'White', '1990-12-21', '+390634567890', 4),
('James', 'Harris', '1979-11-05', '+31256789012', 5),
('Amelia', 'Martin', '1985-04-16', '+491704567890', 1),
('Alexander', 'Thompson', '1992-09-27', '+33145678901', 2),
('Mia', 'Garcia', '1981-06-22', '+34945678901', 3),
('Benjamin', 'Martinez', '1989-10-03', '+39064567890', 4),
('Emily', 'Roberts', '1983-02-14', '+31267890123', 5);

-- Вставка польотів
INSERT INTO flights (departure_airport_id, arrival_airport_id, aircraft_id, flight_date, passengers_ids) VALUES
(1, 2, 1, '2024-08-01 10:00:00', '1,2,3'),
(2, 3, 2, '2024-08-02 14:30:00', '4,5,6'),
(3, 4, 3, '2024-08-03 16:45:00', '7,8,9'),
(4, 5, 3, '2024-08-04 18:00:00', '10,11,12'),
(5, 1, 5, '2024-08-05 08:15:00', '13,14,15'),
(1, 3, 6, '2024-08-06 12:30:00', '16,17,18'),
(2, 4, 6, '2024-08-07 15:00:00', '19,20,1'),
(3, 5, 2, '2024-08-08 17:15:00', '2,3,4'),
(4, 1, 9, '2024-08-09 19:30:00', '5,6,7'),
(5, 2, 9, '2024-08-10 21:00:00', '8,9,10');
