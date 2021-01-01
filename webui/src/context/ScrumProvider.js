import { createContext, useContext, useReducer } from "react";

import {
  initialState as initialPlayer,
  playerReducer,
} from "../reducers/playerReducer";

import {
  alertReducer,
  initialState as initialAlert,
} from "../reducers/alertReducer";

import {
  initialState as initialTeams,
  teamsReducer,
} from "../reducers/teamsReducer";

import {
  cacheReducer,
  initialState as initialCache,
} from "../reducers/cacheReducer";

import {
  createPlayer,
  deletePlayer,
  addAccount,
  signIn,
  signOut,
  updatePassword,
  updatePlayer,
  updateRole,
  updateStatus,
} from "../actions/playerActions";

import { hideAlert, showAlert } from "../actions/alertActions";

import {
  addParticipant,
  clearParticipants,
  createTeam,
  dropParticipant,
  dropTeam,
  findTeamsByOrganizer,
  updateTeam,
  submitVote,
  joinTeam,
  leaveTeam,
} from "../actions/teamsActions";

import {
  selectTeam,
  scrumTeam,
  findTeamById,
  findTeamByName,
  findTeamsJoined,
  recoverLogin,
} from "../actions/cacheActions";

const ScrumContext = createContext();

export function useScrum() {
  return useContext(ScrumContext);
}

export function ScrumProvider({ children }) {
  const [alert, alertDispatch] = useReducer(alertReducer, initialAlert);
  const [player, playerDispatch] = useReducer(playerReducer, initialPlayer);
  const [teams, teamsDispatch] = useReducer(teamsReducer, initialTeams);
  const [cache, cacheDispatch] = useReducer(cacheReducer, initialCache);

  const showAlertDispatcher = showAlert(alertDispatch);
  const hideAlertDispatcher = hideAlert(alertDispatch);

  const combined = {
    alert,
    player,
    teams,
    cache,
    showAlert: showAlertDispatcher,
    hideAlert: hideAlertDispatcher,
    signIn: signIn(playerDispatch, showAlertDispatcher),
    signUp: createPlayer(playerDispatch),
    signOut: signOut(playerDispatch),
    createPlayer: createPlayer(playerDispatch, showAlertDispatcher),
    deletePlayer: deletePlayer(playerDispatch, showAlertDispatcher),
    addAccount: addAccount(playerDispatch, showAlertDispatcher),
    updatePlayer: updatePlayer(playerDispatch, showAlertDispatcher),
    updatePassword: updatePassword(playerDispatch, showAlertDispatcher),
    updateStatus: updateStatus(playerDispatch, showAlertDispatcher),
    updateRole: updateRole(playerDispatch, showAlertDispatcher),
    createTeam: createTeam(teamsDispatch, showAlertDispatcher),
    updateTeam: updateTeam(teamsDispatch, showAlertDispatcher),
    addParticipant: addParticipant(teamsDispatch, showAlertDispatcher),
    dropParticipant: dropParticipant(teamsDispatch, showAlertDispatcher),
    clearParticipants: clearParticipants(teamsDispatch, showAlertDispatcher),
    dropTeam: dropTeam(teamsDispatch, showAlertDispatcher),
    joinTeam: joinTeam(teamsDispatch, showAlertDispatcher),
    leaveTeam: leaveTeam(teamsDispatch, showAlertDispatcher),
    findTeamsByOrganizer: findTeamsByOrganizer(
      teamsDispatch,
      showAlertDispatcher
    ),
    submitVote: submitVote(teamsDispatch, showAlertDispatcher),
    selectTeam: selectTeam(cacheDispatch),
    scrumTeam: scrumTeam(cacheDispatch),
    findTeamById: findTeamById(cacheDispatch, showAlertDispatcher),
    findTeamByName: findTeamByName(cacheDispatch, showAlertDispatcher),
    findTeamsJoined: findTeamsJoined(cacheDispatch, showAlertDispatcher),
    recoverLogin: recoverLogin(cacheDispatch, showAlertDispatcher),
  };

  return (
    <ScrumContext.Provider value={{ ...combined }}>
      {children}
    </ScrumContext.Provider>
  );
}
