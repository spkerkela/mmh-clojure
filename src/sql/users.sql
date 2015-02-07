-- name: all-users
-- Return all users from database
SELECT * FROM users;

-- name: create-user!
-- Create a new user
INSERT INTO users(username, email, password_digest, created_at, updated_at, last_login)
VALUES (
   :username,
   :email,
   :password_digest,
   NOW(),
   NULL,
   NULL
);

-- name: delete-user!
-- Delete user with the given id
DELETE FROM users
WHERE id=:id;

-- name: get-user-by-id
-- Find user with the given id
SELECT * FROM users
WHERE id=:id;

-- name: update-user!
-- Update the user with the given params
UPDATE users
SET
  username=:username,
  email=:email,
  password_digest=:password_digest,
  updated_at=NOW()
WHERE id=:id;