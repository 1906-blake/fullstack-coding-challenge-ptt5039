CREATE TABLE grocery_type (
	type_id SERIAL PRIMARY KEY.
	type_name TEXT UNIQUE NOT NULL
);

CREATE TABLE grocery_list (
	list_id SERIAL PRIMARY KEY,
	list_name TEXT NOT NULL,
	description TEXT NOT NULL
);

CREATE TABLE grocery_item (
	item_id SERIAL PRIMARY KEY,
	item_name TEXT UNIQUE NOT NULL,
	item_type INTEGER NOT NULL REFERENCES grocery_type(type_id),
	list INTEGER REFERENCES grocery_list(list_id)
);