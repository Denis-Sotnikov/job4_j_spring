DROP TABLE accident;
CREATE TABLE accident (
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

create table accident_article_of_the_law(
   id serial primary key,
   accident_id int not null references accident(id),
   article_id int not null references articleofthelaw(id)
);

select  * from accident_article_of_the_law where accident_id = 1;

select accident.id, accident.name, text, address, accident.type_id as typeId, t.name  as tName, aaotl.article_id from accident join types t on accident.type_id = t.id join accident_article_of_the_law aaotl on accident.id = aaotl.accident_id;
select a.id as aId, name from accident_article_of_the_law join articleofthelaw a on accident_article_of_the_law.article_id = a.id where accident_id = 1;


insert into types(name) values ('Две машины');
insert into types(name) values ('Машина и человек');
insert into types(name) values ('Машина и велосипед');
insert into articleofthelaw(name) values ('Статья 1');
insert into articleofthelaw(name) values ('Статья 2');
insert into articleofthelaw(name) values ('Статья 3');
select accident.id, accident.name, text, address, accident.type_id as typeId, t.name from accident join types t on accident.type_id = t.id

