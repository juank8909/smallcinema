create table  if not exists movie(
  id varchar(20) primary key,
  title varchar(200) not null,
  show_times TEXT [] not null,
  price numeric,
  review integer
);