
-- table person
create table if not exists persons(
    id_person serial primary key,
    first_name varchar(255),
    last_name varchar(255),
	city varchar(255),
    country varchar(150),
    first_name2 varchar(255),
	last_name2 varchar(255),
	email varchar(255),
    random integer,
    random_float numeric,
    bool varchar(100),
    dates varchar(200),
    enume varchar(100),
    reg_ex varchar(200),
    last_update varchar(200)
    );

-- index for name
create index name_person
    on persons("first_name");

