CREATE TABLE if NOT EXISTS
Users (
  id int,
  email varchar(255),
  password varchar(255),
  roles varchar(255),
  enabled boolean,
);