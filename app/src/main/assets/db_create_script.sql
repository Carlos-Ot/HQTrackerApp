CREATE TABLE IF NOT EXISTS tb_collection (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    collection_id TEXT NOT NULL,
    collection_name TEXT,
    status INTEGER NOT NULL,
    publishing INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_comic_book (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    volume INTEGER NOT NULL,
    title TEXT NOT NULL,
    book_spine TEXT,
    url TEXT,
    read_order INTEGER,
    collection_name TEXT,
    collection_id TEXT NOT NULL,
    img_large TEXT,
    img_small TEXT,
    status INTEGER NOT NULL,
    FOREIGN KEY (collection_id) REFERENCES tb_collection (collection_id)
);
