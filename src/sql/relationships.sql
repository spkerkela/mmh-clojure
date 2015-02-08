-- name: create-relationship!
-- Follow a person
INSERT INTO relationships(follower_id, followed_id, created_at)
    VALUES (
      :follower_id,
      :followed_id,
      NOW()
    );

-- name: delete-relationship!
-- Unfollow a person
DELETE FROM relationships
WHERE follower_id=:follower_id AND followed_id=:followed_id;

-- name: followers-of
-- Followers of given user
SELECT * FROM users
INNER JOIN relationships
ON users.id = relationships.follower_id AND relationships.followed_id = :id;

-- name: followed-by
-- Users followed by given user
SELECT * FROM users
INNER JOIN relationships
ON users.id = relationships.followed_id AND relationships.follower_id = :id;
