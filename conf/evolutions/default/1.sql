# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table command (
  id                        bigint auto_increment not null,
  ref                       varchar(255) not null,
  body                      TEXT not null,
  constraint pk_command primary key (id))
;

create table user (
  user_id                   bigint auto_increment not null,
  login                     varchar(255) not null,
  password                  varchar(255) not null,
  constraint uq_user_login unique (login),
  constraint pk_user primary key (user_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table command;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

