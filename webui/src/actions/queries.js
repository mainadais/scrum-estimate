export const findByEmailQuery = `
query findByEmail($emailAddress: String!){
    findByEmail(emailAddress: $emailAddress){
        data{
            id
            emailAddress
            firstName
            lastName
            role
            dateCreated
            account {
                id
                username
                status
            }
        }
        error
    }
}`;

export const findByIdQuery = `
query findById($playerId: ID!){
    findById(playerId: $playerId){
        data{
            id
            emailAddress
            firstName
            lastName
            role
            dateCreated
            account {
                id
                username
                status
            }
        }
        error
    }
}`;

export const findByUsernameQuery = `
query findByUsername ($username: String!){
    findByUsername (username: $username){
        data{
            id
            emailAddress
            firstName
            lastName
            role
            dateCreated
            account {
                id
                username
                status
            }
        }
        error
    }
}`;

export const findPlayersByTeamQuery = `
query findPlayersByTeam ($teamId: ID!){
    findPlayersByTeam (teamId: $teamId){
        data{
            id
            firstName
            lastName
            role
        }
        error
    }
}`;

export const findTeamByIdQuery = `
query findTeamById ($teamId: ID!){
    findTeamById (teamId: $teamId){
        data{
            id
            name
            choices
            organizer
            participants{
                id
                player
                team
                vote
            }
        }
        error
    }
}`;

export const findTeamByNameQuery = `
query findTeamByName ($organizerId: ID!, $teamName: String!){
    findTeamByName (organizerId: $organizerId, teamName: $teamName){
        data{
            id
            name
            choices
            organizer
            participants{
                id
                player
                team
            }
            votes {
                id
                participant
                team
                vote
            }
        }
        error
    }
}`;

export const findTeamsByOrganizerQuery = `
query findTeamsByOrganizer ($organizerId: ID!){
    findTeamsByOrganizer (organizerId: $organizerId){
        data{
            id
            name
            choices
            organizer
            participants{
                id
                player
                team
                vote
            }
        }
        error
    }
}`;

export const findTeamsJoinedQuery = `
query findTeamsJoined ($playerId: ID!){
    findTeamsJoined (playerId: $playerId){
        data{
            id
            name
            choices
            organizer
            participants{
                id
                player
                team
                vote
            }
        }
        error
    }
}`;
