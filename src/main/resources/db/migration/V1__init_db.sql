-- ========================
-- BASE TABLES
-- ========================

-- Review table (base class for inheritance)
CREATE TABLE booking_review (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    content VARCHAR(255) NOT NULL,
    rating DOUBLE PRECISION
);

-- Driver table
CREATE TABLE driver (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    name VARCHAR(255),
    license_number VARCHAR(255) NOT NULL UNIQUE
);

-- Passenger table
CREATE TABLE passenger (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    name VARCHAR(255)
);

-- ========================
-- INHERITANCE TABLE (JOINED)
-- ========================

-- PassengerReview extends Review (JOINED inheritance strategy)
CREATE TABLE passenger_review (
    id BIGINT PRIMARY KEY,
    passenger_review_content VARCHAR(255) NOT NULL,
    passenger_rating VARCHAR(255) NOT NULL,
    CONSTRAINT fk_passenger_review_booking_review
        FOREIGN KEY (id) REFERENCES booking_review(id)
        ON DELETE CASCADE
);

-- ========================
-- BOOKING TABLE
-- ========================

-- Booking table with relationships
CREATE TABLE booking (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    -- OneToOne relationship with Review
    review_id BIGINT UNIQUE,

    -- Enum stored as VARCHAR
    booking_status VARCHAR(50),

    -- Booking details
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    total_distance BIGINT,

    -- ManyToOne relationships
    driver_id BIGINT,
    passenger_id BIGINT,

    -- Foreign key constraints
    CONSTRAINT fk_booking_review
        FOREIGN KEY (review_id) REFERENCES booking_review(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_booking_driver
        FOREIGN KEY (driver_id) REFERENCES driver(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_booking_passenger
        FOREIGN KEY (passenger_id) REFERENCES passenger(id)
        ON DELETE SET NULL
);

