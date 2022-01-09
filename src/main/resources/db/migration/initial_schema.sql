create schema if not exists "SMALL_CINEMA" authorization current_user;

SET search_path TO "SMALL_CINEMA";

create table  if not exists "SMALL_CINEMA"."MOVIE"(
  "ID" varchar(20) primary key,
  "TITLE" varchar(200) not null,
  "SHOW_TIMES" varchar [] not null,
  "PRICE" numeric,
  "REVIEW" integer
);

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt0232500','The Fast and the Furious',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 )