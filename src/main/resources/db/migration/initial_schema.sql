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
VALUES ('tt0232500','The Fast and the Furious',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt0322259','2 Fast 2 Furious',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt0463985','The Fast and the Furious: Tokyo Drift',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt1013752','Fast & Furious',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt1596343','Fast Five',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt1905041','Fast & Furious 6',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt2820852','Furious 7',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt4630562','The Fate of the Furious',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );

insert into "SMALL_CINEMA"."MOVIE" ("ID","TITLE","SHOW_TIMES","PRICE","REVIEW")
VALUES ('tt5433138','F9: The Fast Saga',ARRAY [ 'wed 8:00 PM','SAT 9:00 PM' ], 14, 5 );