DROP TABLE IF EXISTS author_ISBN;

CREATE TABLE to_do_items (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   title TEXT NOT NULL,
   description TEXT NOT NULL,
   due_date TEXT NOT NULL,
   completed BOOLEAN NOT NULL DEFAULT 0
);
