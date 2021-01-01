import axios from "axios";
import * as mutations from "./mutations";
import * as queries from "./queries";
import { ALERT_TYPE } from "./alertActions";
import { API_URL, getCookie } from "../util";

export const CREATE_TEAM = "CREATE_TEAM";
export const DROP_TEAM = "DROP_TEAM";
export const UPDATE_TEAM = "UPDATE_TEAM";
export const JOIN_TEAM = "JOIN_TEAM";
export const LEAVE_TEAM = "LEAVE_TEAM";
export const ADD_PARTICIPANT = "ADD_PARTICIPANT";
export const DROP_PARTICIPANT = "DROP_PARTICIPANT";
export const CLEAR_PARTICIPANTS = "CLEAR_PARTICIPANTS";
export const TEAMS_BY_ORGANIZER = "TEAMS_BY_ORGANIZER";
export const TEAMS_BY_ORGANIZER_ERROR = "TEAMS_BY_ORGANIZER_ERROR";
export const SUBMIT_VOTE = "SUBMIT_VOTE";

export const createTeam = (dispatch, dispatchAlert) => (team) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.createTeamQuery,
        operationName: "createTeam",
        variables: { createTeam: team },
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
      dispatch({ type: CREATE_TEAM, team: data.createTeam.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Team ${data.createTeam.data.id} created`,
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

export const updateTeam = (dispatch, dispatchAlert) => (team) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.updateTeamQuery,
        operationName: "updateTeam",
        variables: { updateTeam: team },
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
      dispatch({ type: UPDATE_TEAM, team: data.updateTeam.data });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const joinTeam = (dispatch, dispatchAlert) => ({ team, player }) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.joinTeamQuery,
        operationName: "joinTeam",
        variables: { playerId: player, teamId: team },
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
      dispatch({ type: JOIN_TEAM, team: data.joinTeam.data });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const leaveTeam = (dispatch, dispatchAlert) => ({ team, player }) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.dropParticipantQuery,
        operationName: "dropParticipant",
        variables: {
          dropParticipant: {
            player,
            team,
          },
        },
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
      dispatch({ type: LEAVE_TEAM, team: data.dropParticipant.data });
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const addParticipant = (dispatch, dispatchAlert) => (player, team) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.addParticipantQuery,
        operationName: "addParticipant",
        variables: { addParticipant: { player, team } },
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
      dispatch({ type: ADD_PARTICIPANT, team: data.addParticipant.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Participant has joined team ${data.addParticipant.data.id}`,
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

export const dropParticipant = (dispatch, dispatchAlert) => (participant) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.dropParticipantQuery,
        operationName: "dropParticipant",
        variables: { dropParticipant: participant },
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
      dispatch({ type: DROP_PARTICIPANT, team: data.dropParticipant.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Participant has left team ${data.dropParticipant.data.id}  `,
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

export const clearParticipants = (dispatch, dispatchAlert) => (teamId) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.clearParticipantsQuery,
        operationName: "clearParticipants",
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
      dispatch({ type: CLEAR_PARTICIPANTS, team: data.clearParticipants.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Cleared participants from team ${data.clearParticipants.data.id}`,
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

export const dropTeam = (dispatch, dispatchAlert) => (teamId) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.dropTeamQuery,
        operationName: "dropTeam",
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
      if (data.dropTeam.data === 1) {
        dispatch({ type: DROP_TEAM, team: { id: teamId } });
        dispatchAlert({
          severity: ALERT_TYPE.success,
          message: `Team ${teamId} dropped`,
          title: "Success",
          duration: 3000,
        });
      } else {
      }
    })
    .catch((err) => {
      dispatchAlert({
        severity: ALERT_TYPE.error,
        message: err.message,
        title: "Error",
      });
    });
};

export const findTeamsByOrganizer = (dispatch, dispatchAlert) => (
  organizerId
) => {
  axios
    .post(
      API_URL,
      {
        query: queries.findTeamsByOrganizerQuery,
        operationName: "findTeamsByOrganizer",
        variables: { organizerId },
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
        type: TEAMS_BY_ORGANIZER,
        teams: data.findTeamsByOrganizer.data,
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

export const submitVote = (dispatch, dispatchAlert) => (vote) => {
  axios
    .post(
      API_URL,
      {
        query: mutations.submitVoteQuery,
        operationName: "submitVote",
        variables: { submitVote: vote },
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
      dispatch({ type: SUBMIT_VOTE, team: data.submitVote.data });
      dispatchAlert({
        severity: ALERT_TYPE.success,
        message: `Vote submitted successfully`,
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
