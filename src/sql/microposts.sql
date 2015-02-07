-- name: create-micropost!
-- Create a micropost for the given user
INSERT INTO microposts(user_id, content, created_at)
    VALUES (
        :user_id,
        :content,
        NOW()
    );

-- name: update-micropost!
-- Update the given micropost
UPDATE microposts
SET
  content=:content,
  upated_at=NOW()
WHERE id=:id;

-- name: delete-micropost!
-- Delete the micropost with the id
DELETE FROM microposts
WHERE id=:id;

-- name: microposts-by-user-id
-- Get all microposts from the given user
SELECT * FROM microposts
WHERE user_id=:user_id;

-- name: all-microposts
-- Get all microposts
SELECT * FROM microposts ORDER BY created_at DESC ;