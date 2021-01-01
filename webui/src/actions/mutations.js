export const loginQuery = `
mutation login ($emailAddress: String!, $password: String!){
  login( emailAddress: $emailAddress, password: $password){
        username
        status
        role
        token
    }
}`;

export const createPlayerQuery = `
mutation createPlayer($createPlayer: CreatePlayer) {
  createPlayer(createPlayer: $createPlayer){
      data{
          id
          firstName
          lastName
          emailAddress
          account{
              id
              username
          }
          role
          dateCreated
      }
  }
}`;

export const addAccountQuery = `
mutation addAccount($addAccount: AddAccount) {
  addAccount(addAccount: $addAccount){
      data{
          id
          firstName
          lastName
          emailAddress
          account{
              id
              username
          }
          role
          dateCreated
      }
  }
}`;

export const updatePasswordQuery = `
mutation updatePassword($updatePassword: UpdatePassword) {
    updatePassword(updatePassword: $updatePassword){
        data{
            id
            firstName
            lastName
            emailAddress
            account{
                id
                username
                password
            }
            role
            dateCreated
        }
    }
}`;

export const updatePlayerQuery = `
mutation updatePlayer($updatePlayer: UpdatePlayer) {
    updatePlayer(updatePlayer: $updatePlayer){
        data{
            id
            firstName
            lastName
            emailAddress
            account{
                id
                username
                password
            }
            role
            dateCreated
        }
    }
}`;

export const updateStatusQuery = `
mutation updateStatus($updateStatus: UpdateStatus) {
    updateStatus(updateStatus: $updateStatus){
        data{
            id
            firstName
            lastName
            emailAddress
            account{
                id
                username
                status
            }
            role
            dateCreated
        }
    }
}`;

export const updateRoleQuery = `
mutation updateRole($updateRole: UpdateRole) {
    updateRole(updateRole: $updateRole){
        data{
            id
            firstName
            lastName
            emailAddress
            account{
                id
                username
                status
            }
            role
            dateCreated
        }
    }
}`;

export const deletePlayerQuery = `
mutation deletePlayer($playerId: String) {
    deletePlayer(playerId:$playerId){
        data
    }
}`;

export const createTeamQuery = `
mutation createTeam($createTeam: CreateTeam!){
    createTeam(createTeam: $createTeam){
        data{
            id
            name
            choices
            organizer
        }
    }
}`;

export const updateTeamQuery = `
mutation updateTeam($updateTeam: UpdateTeam!){
    updateTeam(updateTeam: $updateTeam){
        data{
            id
            name
            choices
            organizer
        }
    }
}`;

export const addParticipantQuery = `
mutation addParticipant($addParticipant: AddParticipant!) {
    addParticipant(addParticipant: $addParticipant){
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
    }
}`;

export const dropParticipantQuery = `
mutation dropParticipant($dropParticipant: DropParticipant!) {
    dropParticipant(dropParticipant: $dropParticipant){
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
    }
}`;

export const clearParticipantsQuery = `
mutation clearParticipants($teamId: ID!) {
    clearParticipants(teamId: $teamId){
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
    }
}`;

export const dropTeamQuery = `
mutation dropTeam ($teamId: ID!){
    dropTeam(teamId: $teamId){
        data
    }
}`;

export const joinTeamQuery = `
mutation joinTeam ($playerId: ID!, $teamId: ID!){
    joinTeam(playerId: $playerId, teamId: $teamId){
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
    }
}`;

export const submitVoteQuery = `
mutation submitVote ($submitVote: SubmitVote){
    submitVote(submitVote: $submitVote){
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
    }
}`;

export const recoverLoginQuery = `
mutation recoverLogin ($recoverLogin: RecoverLogin){
    recoverLogin(recoverLogin: $recoverLogin){
      data{
        result
      }
      error
    }
  }`;
