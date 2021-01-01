import {
  SELECT_TEAM,
  TEAM_BY_ID,
  TEAM_BY_NAME,
  TEAMS_JOINED,
  START_ROUTE,
  SCRUM_TEAM,
} from "../actions/cacheActions";

export const initialState = {
  team: '',
  scrum: {},
  joined: [],
  startRoute: '/'
};

export const cacheReducer = (state, action) => {
  switch (action.type) {
    case SELECT_TEAM: {
      if (action.team) {
        return { ...state, team: action.team };
      }
      break;
    }
    case SCRUM_TEAM: {
      if(action.team){
        return {...state, scrum: state.joined.find(team => team.id === action.team)}
      }
    }
    case TEAM_BY_ID:
    case TEAM_BY_NAME: {
      if (action.scrum) {
        return { ...state, scrum: action.scrum };
      }
      break;
    }
    case TEAMS_JOINED: {
      if (action.joined) {
        return { ...state, joined: action.joined };
      }
      break;
    }
    case START_ROUTE: {
      if(action.redirectTo){
        return { ...state, startRoute: action.redirectTo };
      }
      break;
    }
    default: {
      return state;
    }
  }
  return state;
};
