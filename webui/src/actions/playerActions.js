import axios from "axios";
import * as mutations from "./mutations";
import * as queries from "./queries";
import { ALERT_TYPE } from "./alertActions";
import { API_URL, getCookie, removeCookie, setCookie } from "../util";

export const LOGIN_ERROR = "ERROR";
export const LOGIN = "LOGIN_ERROR";
export const LOGOUT = "LOGOUT";
export const CREATE_PLAYER = "CREATE_PLAYER";
export const ADD_ACCOUNT = "ADD_ACCOUNT";
export const UPDATE_PASSWORD = "UPDATE_PASSWORD";
export const UPDATE_PLAYER = "UPDATE_PLAYER";
export const UPDATE_STATUS = "UPDATE_STATUS";
export const UPDATE_ROLE = "UPDATE_ROLE";
export const DELETE_PLAYER = "DELETE_PLAYER";

const findByEmail = async (emailAddress) => {
  return new Promise((resolve, reject) => {
    axios
      .post(
        API_URL,
        {
          query: queries.findByEmailQuery,
          operationName: "findByEmail",
          variables: { emailAddress },
        },
        {
          headers: {
            Authorization: `Bearer ${getCookie("accessToken")}`,
          },
        }
      )
      .then((data) => {
        if (data.data.errors) {
          throw new Error(data.data.errors[0].message);
        } else {
          return data.data.data;
        }
      })
      .then((data) => {
        return data.findByEmail.data;
      })
      .then((data) => {
        resolve(data);
      })
      .catch((err) => {
        reject(err);
      });
  });
};

export const signIn = (dispatch, dispatchAlert) => (login) => {
  axios
    .post(API_URL, {
      query: mutations.loginQuery,
      operationName: "login",
      variables: login,
    })
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then(async ({ login }) => {
      try {
        setCookie("accessToken", login.token, 30);
        const player = await findByEmail(login.username);
        dispatch({ type: LOGIN, player, accessToken: login.token });
      } catch (err) {
        dispatch({ type: LOGIN_ERROR, err });
        removeCookie("accessToken");
      }
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
      removeCookie("accessToken");
    });
};

export const signOut = (dispatch) => () => {
  dispatch({ type: LOGOUT });
  removeCookie("accessToken");
};

export const createPlayer = (dispatch, dispatchAlert) => (player) => {
  axios
    .post(API_URL, {
      query: mutations.createPlayerQuery,
      operationName: "createPlayer",
      variables: { createPlayer: player },
    })
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: CREATE_PLAYER, player: data.createPlayer.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `New player ${player.id} created`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const addAccount = (dispatch) => (account, dispatchAlert) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.addAccountQuery,
        operationName: "addAccount",
        variables: { addAccount: account },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: ADD_ACCOUNT, account: data.addAccount.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `${account.username} account created`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const updatePassword = (dispatch, dispatchAlert) => (update) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.updatePasswordQuery,
        operationName: "updatePassword",
        variables: { updatePassword: update },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: UPDATE_PASSWORD, account: data.updatePassword.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Account password updated`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const updatePlayer = (dispatch, dispatchAlert) => (player) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.updatePlayerQuery,
        operationName: "updatePlayer",
        variables: { updatePlayer: player },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: UPDATE_PLAYER, player: data.updatePlayer.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Player ${player.id} updated`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const updateStatus = (dispatch, dispatchAlert) => (update) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.updateStatusQuery,
        operationName: "updateStatus",
        variables: { updateStatus: update },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: UPDATE_STATUS, player: data.updateStatus.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Player ${update.player}'s status updated`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const updateRole = (dispatch, dispatchAlert) => (update) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.updateRoleQuery,
        operationName: "updateRole",
        variables: { updateRole: update },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: UPDATE_ROLE, player: data.updateRole.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Player ${update.player}'s role updated`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const deletePlayer = (dispatch, dispatchAlert) => (playerId) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.deletePlayerQuery,
        operationName: "deletePlayer",
        variables: { playerId },
      },
      {
        headers: {
          Authorization: `Bearer ${getCookie("accessToken")}`,
        },
      }
    )
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      dispatch({ type: UPDATE_ROLE, player: data.updateRole.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Player ${playerId} has been removed successfully`,
        title: "Success",
        duration: 3000,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};
