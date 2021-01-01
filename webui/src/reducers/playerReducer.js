import {
    ADD_ACCOUNT,
    CREATE_PLAYER,
    DELETE_PLAYER,
    LOGIN,
    LOGOUT,
    UPDATE_PLAYER,
    UPDATE_ROLE,
    UPDATE_STATUS,
} from "../actions/playerActions";

export const initialState = {
    firstName: "",
    lastName: "",
    emailAddress: "",
    account: {
        username: "",
        status: "",
    },
    status: "",
    accessToken: "",
};

export const playerReducer = (state, action) => {
    switch (action.type) {
        case LOGIN: {
            if (action.player) {
                return {...state, ...action.player, accessToken: action.accessToken};
            }
            break;
        }
        case CREATE_PLAYER: {
            if (action.player) {
                return {...state, ...action.player};
            }
            break;
        }
        case ADD_ACCOUNT: {
            if (action.player) {
                return {...state, account: action.player.account};
            }
            break;
        }
        case UPDATE_PLAYER: {
            if (action.player) {
                const {firstName, lastName, emailAddress} = action.player;
                return {...state, firstName, lastName, emailAddress};
            }
            return state;
        }
        case UPDATE_STATUS: {
            if (action.player) {
                return {...state, status: action.player.status};
            }
            return state;
        }
        case UPDATE_ROLE: {
            if (action.player) {
                return {...state, role: action.player.role};
            }
            return state;
        }
        case LOGOUT:
        case DELETE_PLAYER: {
            return initialState;
        }
        default: {
            return state;
        }
    }
    return state;
};
