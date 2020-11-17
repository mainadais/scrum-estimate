CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop table if exists tbl_vote;
drop table if exists tbl_participant;
drop table if exists tbl_team;
drop table if exists tbl_account;
drop table if exists tbl_player;
drop type if exists player_role;
drop type if exists account_status;

CREATE TABLE tbl_player
(
    id            uuid        DEFAULT uuid_generate_v4(),
    first_name    varchar(32)  NOT NULL,
    last_name     varchar(32)  not null,
    email_address varchar(128) not null,
    role          varchar(32) default 'GUEST',
    date_created  timestamp   default current_timestamp,
    CONSTRAINT uniq_email unique (email_address),
    CONSTRAINT tbl_player_pk PRIMARY KEY (id)
);

CREATE TABLE tbl_account
(
    id       uuid        DEFAULT uuid_generate_v4(),
    username varchar(32)  NOT NULL,
    password varchar(256) not null,
    player   uuid         not null,
    status   varchar(32) default 'pending_confirmation',
    foreign key (player) references tbl_player (id) on delete cascade,
    CONSTRAINT uniq_username unique (username),
    CONSTRAINT tbl_account_pk PRIMARY KEY (id)
);

CREATE TABLE tbl_team
(
    id           uuid      DEFAULT uuid_generate_v4(),
    name         varchar(64) NOT NULL,
    organization varchar(128),
    organizer    uuid        not null,
    date_created timestamp default current_timestamp,
    foreign key (organizer) references tbl_player (id),
    CONSTRAINT tbl_team_pk PRIMARY KEY (id)
);

CREATE TABLE tbl_participant
(
    id           uuid DEFAULT uuid_generate_v4(),
    player       uuid not null,
    team         uuid not null,
    tbl_team_key int  not null,
    foreign key (player) references tbl_player (id),
    foreign key (team) references tbl_team (id),
    CONSTRAINT uniq_team_player unique (player, team),
    CONSTRAINT tbl_scrum_pk PRIMARY KEY (id)
);

CREATE TABLE tbl_vote
(
    id          uuid DEFAULT uuid_generate_v4(),
    participant uuid         not null,
    scrum       varchar(512) not null,
    vote        int          NOT NULL,
    foreign key (participant) references tbl_participant (id) on delete cascade,
    CONSTRAINT uniq_participant unique (participant, scrum),
    CONSTRAINT tbl_vote_pk PRIMARY KEY (id)
);

COMMIT;