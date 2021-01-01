import axios from "axios";
import * as mutations from "./mutations";
import * as queries from "./queries";
import { ALERT_TYPE } from "./alertActions";
import { API_URL, getCookie } from "../util";

export const SELECT_TEAM = "SELECT_TEAM";
export const TEAM_BY_ID = "TEAM_BY_ID";
export const TEAM_BY_NAME = "TEAM_BY_NAME";
export const TEAMS_JOINED = "TEAMS_JOINED";
export const START_ROUTE = "START_ROUTE";
export const SCRUM_TEAM = "SCRUM_TEAM"

export const selectTeam = (dispatch) => (team) => {
  dispatch({ type: SELECT_TEAM, team });
};

export const scrumTeam = (dispatch) => (team) => {
  dispatch({ type: SCRUM_TEAM, team });
};

export const findTeamById = (dispatch, dispatchAlert) => (teamId) => {
  axios
    .post(
      API_URL,
      {
        query: queries.findTeamByIdQuery,
        operationName: "findTeamById",
        variables: { teamId },
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
      dispatch({
        type: TEAM_BY_ID,
        scrum: data.findTeamById.data,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        title: "Error fetching teams",
        message: err.message,
      });
    });
};

export const findTeamByName = (dispatch, dispatchAlert) => (
  organizer,
  team
) => {
  axios
    .post(
      API_URL,
      {
        query: queries.findTeamByNameQuery,
        operationName: "findTeamByName",
        variables: { organizerId: organizer, teamName: team },
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
      dispatch({ type: TEAM_BY_NAME, scrum: data.findTeamByName.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Scrum team fetchedsuccessfully`,
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

export const findTeamsJoined = (dispatch, dispatchAlert) => (playerId) => {
  axios
    .post(
      API_URL,
      {
        query: queries.findTeamsJoinedQuery,
        operationName: "findTeamsJoined",
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
      dispatch({
        type: TEAMS_JOINED,
        joined: data.findTeamsJoined.data,
      });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        title: "Error fetching teams",
        message: err.message,
      });
    });
};

export const recoverLogin = (dispatch, dispatchAlert) => ({
  emailAddress,
  emailToken,
  newPassword,
}) => {
  axios
    .post(API_URL, {
      query: mutations.recoverLoginQuery,
      operationName: "recoverLogin",
      variables: { recoverLogin: { emailAddress, emailToken, newPassword } },
    })
    .then((data) => {
      if (data.data.errors) {
        throw new Error(data.data.errors[0].message);
      } else {
        return data.data.data;
      }
    })
    .then((data) => {
      return data.recoverLogin.data;
    })
    .then((data) => {
      dispatch({
        type: START_ROUTE,
        redirectTo: "/signIn",
      });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: data.result,
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
