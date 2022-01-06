create schema if not exists small_cinema authorization current_user;

SET search_path TO small_cinema;

create table  if not exists small_cinema.movie(
  id varchar(20) primary key,
  title varchar(200) not null,
  show_times jsonb not null,
  price numeric,
  review integer
);