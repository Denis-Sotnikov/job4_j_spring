DROP TABLE accident;
CREATE TABLE accidents (
  id serial primary key,
  name varchar(2000),
  text text,
  address text,
  type_id int not null references types(id)
);

CREATE TABLE types (
  id serial primary key,
  name varchar(2000)
);

create table articleofthelaw(
  id serial primary key,
  name varchar(2000)
);

create table accidents_articleofthelaw(
   id serial primary key,
   accident_id int not null references accident(id),
   article_id int not null references articleofthelaw(id)
);

insert into types(name) values ('Две машины');
insert into types(name) values ('Машина и человек');
insert into types(name) values ('Машина и велосипед');
insert into articleofthelaw(name) values ('Статья 1');
insert into articleofthelaw(name) values ('Статья 2');
insert into articleofthelaw(name) values ('Статья 3');
select accident.id, accident.name, text, address, accident.type_id as typeId, t.name from accident join types t on accident.type_id = t.id

