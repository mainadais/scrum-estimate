import {
  ADD_PARTICIPANT,
  CLEAR_PARTICIPANTS,
  CREATE_TEAM,
  DROP_PARTICIPANT,
  DROP_TEAM,
  JOIN_TEAM,
  LEAVE_TEAM,
  TEAMS_BY_ORGANIZER,
  UPDATE_TEAM,
  SUBMIT_VOTE,
} from "../actions/teamsActions";

export const initialState = [];

export const teamsReducer = (state, action) => {
  switch (action.type) {
    case CREATE_TEAM: {
      if (action.team) {
        return [...state, action.team];
      }
      break;
    }
    case LEAVE_TEAM:
    case DROP_TEAM: {
      if (action.team) {
        return state.filter((team) => team.id !== action.team.id);
      }
      break;
    }
    case SUBMIT_VOTE:
    case JOIN_TEAM:
    case UPDATE_TEAM:
    case ADD_PARTICIPANT:
    case DROP_PARTICIPANT:
    case CLEAR_PARTICIPANTS: {
      if (action.team) {
        return state.map((team) => {
          if (team.id === action.team.id) {
            return action.team;
          }
          return team;
        });
      }
      break;
    }
    case TEAMS_BY_ORGANIZER: {
      if (action.teams) {
        return action.teams;
      }
      break;
    }
    default: {
      return state;
    }
  }
  return state;
};
