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

CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', '$2a$10$omCzYs22cU9CLXEdaXA41eQACJj1IVY7QNNKm70ngf4Zr9cPaN.bG',
        (select id from authorities where authority = 'ROLE_ADMIN'));


insert into types(name) values ('Две машины');
insert into types(name) values ('Машина и человек');
insert into types(name) values ('Машина и велосипед');
insert into articleofthelaw(name) values ('Статья 1');
insert into articleofthelaw(name) values ('Статья 2');
insert into articleofthelaw(name) values ('Статья 3');
select accident.id, accident.name, text, address, accident.type_id as typeId, t.name from accident join types t on accident.type_id = t.id

