CREATE TABLE users (
    id VARCHAR(8) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL,
    debit_card_number VARCHAR(20) NOT NULL
);

CREATE TABLE license_plates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(8),
    plate_number VARCHAR(15),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE charging_stations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(100) NOT NULL,
    status ENUM('AVAILABLE', 'UNAVAILABLE', 'RESERVED', 'OCCUPIED') NOT NULL
);

CREATE TABLE reservations (
	id VARCHAR(20) NOT NULL UNIQUE,
    user_id VARCHAR(8),
    station_id INT,
    plate_number VARCHAR(15),
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    is_guaranteed BOOLEAN NOT NULL,
    is_completed BOOLEAN NOT NULL,
	is_arrived BOOLEAN NOT NULL,
    is_paid BOOLEAN NOT NULL,
	extension_count INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (station_id) REFERENCES charging_stations(id)
);

CREATE TABLE fees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE,
    amount DOUBLE
);

INSERT INTO fees (name, amount) VALUES
('charging_fee', 0.1),
('reservation_fee', 1.0),
('penalty_fee', 5.0),
('extension_fee', 2.0);
