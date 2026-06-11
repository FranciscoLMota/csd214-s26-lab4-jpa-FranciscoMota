CREATE TABLE IF NOT EXISTS physicalReleases (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(36),
    title VARCHAR(255),
    artist VARCHAR(255),
    dateOfRelease VARCHAR(10),
    price DOUBLE,
    copies INT,
    hasAutoReverse BOOLEAN NOT NULL DEFAULT FALSE,
    sizeInches INT,
    rotationsPerMinute INT
);