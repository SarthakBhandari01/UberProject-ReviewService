-- Step 1: Drop existing foreign key from booking table
ALTER TABLE booking
DROP
CONSTRAINT FK_BOOKING_REVIEW;

-- Step 2: Add booking_id column in booking_review
ALTER TABLE booking_review
ADD COLUMN booking_id BIGINT;

 -- Step 3: Add foreign key from booking_review → booking
 ALTER TABLE booking_review
 ADD CONSTRAINT fk_booking_review_on_booking
 FOREIGN KEY (booking_id) REFERENCES booking(id);

-- Step 4: (Optional but recommended) enforce 1:1 relationship
ALTER TABLE booking_review
ADD CONSTRAINT uk_booking_review_booking UNIQUE (booking_id);

-- Step 5: Drop old column from booking table
ALTER TABLE booking
DROP COLUMN review_id;


