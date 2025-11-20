
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS device;

CREATE TABLE IF NOT EXISTS device (
    id INT AUTO_INCREMENT PRIMARY KEY,
    device_name VARCHAR(100) NOT NULL,
    issued_by VARCHAR(100),
    emp_id VARCHAR(100),
    serial_no VARCHAR(100) NOT NULL UNIQUE,
    mac_address VARCHAR(50) NOT NULL UNIQUE,
    ip_address VARCHAR(45),
    location_name VARCHAR(100),
    area_name VARCHAR(100),
    manufacturer VARCHAR(100),
    created_at DATE NOT NULL,
    registered_by VARCHAR(20) NOT NULL,
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
