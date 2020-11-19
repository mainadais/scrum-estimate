import axios from "axios";

const endpoint = 'http://localhost:7080/graphql'

export function handleLogin(login) {
  axios
    .post(endpoint, {
      query: `
      mutation login ($emailAddress: String!, $password: String!){
        login( emailAddress: $emailAddress, password: $password){
              username
              status
              role
              token
          }
      }`,
      operationName: "login",
      variables: login,
    })
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export function handleCreatePlayer(player) {
  axios
    .post(endpoint, {
      query: `
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
    }`,
      operationName: "createPlayer",
      variables: { createPlayer: player },
    })
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export function handleAdAccount(account) {
  axios
    .post(endpoint, {
      query: `
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
    }`,
      operationName: "addAccount",
      variables: { addAccount: account },
    })
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
      console.log(err);
    });
}
