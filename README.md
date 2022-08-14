# UserNotes
Study project on JDBC and Servlets

Program to store users' notes. User can register it's account, manipulate with it
and work with notes(create, update, read and delete).

DB:
- User table:
    - id INTEGER PRIMARY_KEY,
    - username VCHAR(32) NOT NULL,
    - email VCHAR(128) NOT NULL UNIQUE,
    - password VCHAR(64) NOT NULL.

- Note table:
    - id INTEGER PRIMARY_KEY,
    - note_name VCHAR(32) NOT NULL,
    - note VCHAR(1024) NOT NULL,
    - created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    - user_id INTEGER FOREIGN_KEY,