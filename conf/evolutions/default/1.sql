# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table command (
  id                        bigint not null,
  ref                       varchar(255) not null,
  body                      TEXT not null,
  constraint pk_command primary key (id))
;

create table user (
  user_id                   bigint not null,
  login                     varchar(255) not null,
  password                  varchar(255) not null,
  constraint uq_user_login unique (login),
  constraint pk_user primary key (user_id))
;

create sequence command_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists command;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists command_seq;

drop sequence if exists user_seq;

