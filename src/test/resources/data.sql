--create players
insert into tbl_player (id, first_name, last_name, email_address, date_created)
values (uuid_generate_v4(), 'cassie_first', 'cassie_last', 'cassie_email@email.com', current_timestamp)
on conflict do nothing;
insert into tbl_player (id, first_name, last_name, email_address, date_created)
values (uuid_generate_v4(), 'jacob_first', 'jacob_last', 'jacob_email@email.com', current_timestamp)
on conflict do nothing;
insert into tbl_player (id, first_name, last_name, email_address, date_created)
values (uuid_generate_v4(), 'melissa_first', 'melissa_last', 'melissa_email@email.com', current_timestamp)
on conflict do nothing;
insert into tbl_player (id, first_name, last_name, email_address, date_created)
values (uuid_generate_v4(), 'steve_first', 'steve_last', 'steve_email@email.com', current_timestamp)
on conflict do nothing;

--create accounts
insert into tbl_account (id, username, password, player)
values (uuid_generate_v4(), 'steve', '$2a$10$s2FxLYuOcM7hK7yZjtdmxu2lkyFbFc7qVr.TZ9pypWkucKmjx2KQa',
        (select id from tbl_player where email_address = 'steve_email@email.com'))
on conflict do nothing;
insert into tbl_account (id, username, password, player)
values (uuid_generate_v4(), 'melissa', '$2a$10$s2FxLYuOcM7hK7yZjtdmxu2lkyFbFc7qVr.TZ9pypWkucKmjx2KQa',
        (select id from tbl_player where email_address = 'melissa_email@email.com'))
on conflict do nothing;
insert into tbl_account (id, username, password, player)
values (uuid_generate_v4(), 'jacob', '$2a$10$s2FxLYuOcM7hK7yZjtdmxu2lkyFbFc7qVr.TZ9pypWkucKmjx2KQa',
        (select id from tbl_player where email_address = 'jacob_email@email.com'))
on conflict do nothing;
insert into tbl_account (id, username, password, player)
values (uuid_generate_v4(), 'cassie', '$2a$10$s2FxLYuOcM7hK7yZjtdmxu2lkyFbFc7qVr.TZ9pypWkucKmjx2KQa',
        (select id from tbl_player where email_address = 'cassie_email@email.com'))
on conflict do nothing;

--create teams
insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'cassie_team_1', null,
        (select id from tbl_player where email_address = 'cassie_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'cassie_team_2', null,
        (select id from tbl_player where email_address = 'cassie_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'jacob_team_1', null,
        (select id from tbl_player where email_address = 'jacob_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'jacob_team_2', null,
        (select id from tbl_player where email_address = 'jacob_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'melissa_team_1', null,
        (select id from tbl_player where email_address = 'melissa_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'melissa_team_2', null,
        (select id from tbl_player where email_address = 'melissa_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'steve_team_1', null,
        (select id from tbl_player where email_address = 'steve_email@email.com'), current_timestamp)
on conflict do nothing;

insert into tbl_team (id, name, organization, organizer, date_created)
values (uuid_generate_v4(), 'steve_team_2', null,
        (select id from tbl_player where email_address = 'steve_email@email.com'), current_timestamp)
on conflict do nothing;

--create participants for cassie's scrum using team_1
insert into tbl_participant (id, player, team, tbl_team_key)
values (uuid_generate_v4(),
        (select pl.id from tbl_player pl where pl.email_address = 'steve_email@email.com'),
        (select team.id
         from tbl_team team
                  inner join tbl_player player on player.id = team.organizer
         where player.email_address = 'cassie_email@email.com'
           and team.name = 'cassie_team_1'), 0)
on conflict do nothing;

insert into tbl_participant (id, player, team, tbl_team_key)
values (uuid_generate_v4(),
        (select pl.id from tbl_player pl where pl.email_address = 'melissa_email@email.com'),
        (select team.id
         from tbl_team team
                  inner join tbl_player player on player.id = team.organizer
         where player.email_address = 'cassie_email@email.com'
           and team.name = 'cassie_team_1'), 0)
on conflict do nothing;

insert into tbl_participant (id, player, team, tbl_team_key)
values (uuid_generate_v4(),
        (select pl.id from tbl_player pl where pl.email_address = 'jacob_email@email.com'),
        (select team.id
         from tbl_team team
                  inner join tbl_player player on player.id = team.organizer
         where player.email_address = 'cassie_email@email.com'
           and team.name = 'cassie_team_1'), 0)
on conflict do nothing;

insert into tbl_participant (id, player, team, tbl_team_key)
values (uuid_generate_v4(),
        (select pl.id from tbl_player pl where pl.email_address = 'cassie_email@email.com'),
        (select team.id
         from tbl_team team
                  inner join tbl_player player on player.id = team.organizer
         where player.email_address = 'cassie_email@email.com'
           and team.name = 'cassie_team_1'), 0)
on conflict do nothing;

--create votes
insert into tbl_vote (participant, scrum, vote)
values ((select participant.id
         from tbl_participant participant
                  inner join tbl_player player on player.id = participant.player
         where player.email_address = 'cassie_email@email.com'),
        'cassie scrum',
        3)
on conflict do nothing;

insert into tbl_vote (participant, scrum, vote)
values ((select participant.id
         from tbl_participant participant
                  inner join tbl_player player on player.id = participant.player
         where player.email_address = 'jacob_email@email.com'),
        'cassie scrum',
        4)
on conflict do nothing;

insert into tbl_vote (participant, scrum, vote)
values ((select participant.id
         from tbl_participant participant
                  inner join tbl_player player on player.id = participant.player
         where player.email_address = 'melissa_email@email.com'),
        'cassie scrum',
        5)
on conflict do nothing;

insert into tbl_vote (participant, scrum, vote)
values ((select participant.id
         from tbl_participant participant
                  inner join tbl_player player on player.id = participant.player
         where player.email_address = 'steve_email@email.com'),
        'cassie scrum',
        2)
on conflict do nothing;