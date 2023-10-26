-- LOGS

CREATE TABLE IF NOT EXISTS logs (
    id SERIAL PRIMARY KEY,

    req_desc VARCHAR(10000) NOT NULL,
    ip VARCHAR(50) NOT NULL,
    endpoint_dest VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT NOW()
);

-- CATALOG_REQUESTS

CREATE TABLE IF NOT EXISTS catalog_requests (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(36) UNIQUE NOT NULL,

    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    poster VARCHAR(255) NOT NULL,
    trailer VARCHAR(255),
    category ENUM('ANIME', 'DRAMA', 'MIXED'),

    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

DELIMITER $$
    CREATE TRIGGER IF NOT EXISTS catalog_requests_updated_at
    BEFORE UPDATE ON catalog_requests FOR EACH ROW
    BEGIN
        SET NEW.updated_at = NOW();
    END;
$$
DELIMITER ;

-- ACCOUNT_VERIFICATION_REQUEST

CREATE TABLE account_verification_requests (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(36) UNIQUE NOT NULL,

    user_id INT UNIQUE NOT NULL,
    status ENUM('PENDING', 'ACCEPTED', 'REJECTED'),

    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

DELIMITER $$
    CREATE TRIGGER IF NOT EXISTS account_verification_requests_updated_at
    BEFORE UPDATE ON account_verification_requests FOR EACH ROW
    BEGIN
        SET NEW.updated_at = NOW();
    END;
$$
DELIMITER ;

-- REPORT_USERS

CREATE TABLE report_users (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(36) UNIQUE NOT NULL,

    content VARCHAR(255) NOT NULL,
    reporter_id INT NOT NULL,
    reported_id INT NOT NULL,

    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

DELIMITER $$
    CREATE TRIGGER IF NOT EXISTS report_users_updated_at
    BEFORE UPDATE ON report_users FOR EACH ROW
    BEGIN
        SET NEW.updated_at = NOW();
    END;
$$
DELIMITER ;