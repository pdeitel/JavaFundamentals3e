DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS playlists;
DROP TABLE IF EXISTS playlist_songs;

CREATE TABLE songs (
   song_id INTEGER PRIMARY KEY AUTOINCREMENT,
   title TEXT NOT NULL,
   artist TEXT NOT NULL,
   genre TEXT
);

CREATE TABLE playlists (
   playlist_id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL,
   description TEXT
);

CREATE TABLE playlist_songs (
   playlist_id INTEGER,
   song_id INTEGER,
   position INTEGER NOT NULL, 
   PRIMARY KEY (playlist_id, song_id),
   FOREIGN KEY (playlist_id) REFERENCES playlists(playlist_id),
   FOREIGN KEY (song_id) REFERENCES songs(song_id)
);

-- Expanded sample data
INSERT INTO songs (title, artist, genre) VALUES
('Imagine', 'John Lennon', 'Rock'),
('Bohemian Rhapsody', 'Queen', 'Rock'),
('Hey Jude', 'The Beatles', 'Rock'), 
('Purple Rain', 'Prince', 'Rock'),
('Like a Rolling Stone', 'Bob Dylan', 'Rock'),
('Sweet Child O Mine', 'Guns N Roses', 'Rock'),
('Stairway to Heaven', 'Led Zeppelin', 'Rock'),
('Take on Me', 'A-ha', 'Pop'),
('Billie Jean', 'Michael Jackson', 'Pop'),
('Dancing Queen', 'ABBA', 'Pop'),
('I Will Survive', 'Gloria Gaynor', 'Pop'),
('Sweet Dreams', 'Eurythmics', 'Pop'),
('Like a Prayer', 'Madonna', 'Pop'),
('Girls Just Want to Have Fun', 'Cyndi Lauper', 'Pop'),
('Giant Steps', 'John Coltrane', 'Jazz'),
('So What', 'Miles Davis', 'Jazz'),
('Take Five', 'Dave Brubeck', 'Jazz'),
('My Favorite Things', 'John Coltrane', 'Jazz'),
('Round Midnight', 'Thelonious Monk', 'Jazz'),
('A Love Supreme', 'John Coltrane', 'Jazz'),
('Birdland', 'Weather Report', 'Jazz');

INSERT INTO playlists (name, description) VALUES
('Classic Rock Hits', 'Greatest rock songs of all time'),
('Pop Classics', '80s and 90s pop favorites'),
('Jazz Essentials', 'Must-hear jazz standards');

-- Populate playlists
INSERT INTO playlist_songs (playlist_id, song_id, position) 
SELECT 1, song_id, ROW_NUMBER() OVER (ORDER BY title)
FROM songs WHERE genre = 'Rock' LIMIT 7;

INSERT INTO playlist_songs (playlist_id, song_id, position)
SELECT 2, song_id, ROW_NUMBER() OVER (ORDER BY title)
FROM songs WHERE genre = 'Pop' LIMIT 7;

INSERT INTO playlist_songs (playlist_id, song_id, position)
SELECT 3, song_id, ROW_NUMBER() OVER (ORDER BY title)
FROM songs WHERE genre = 'Jazz' LIMIT 7;