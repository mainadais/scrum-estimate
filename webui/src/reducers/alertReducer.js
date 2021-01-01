import {HIDE_ALERT, SHOW_ALERT} from "../actions/alertActions";

export const initialState = {
    severity: "info",
    title: "testing message",
    message: "Show relevant message here",
    hide: true,
    duration: -1,
};

export const alertReducer = (state, action) => {
    switch (action.type) {
        case SHOW_ALERT: {
            if (action.alert) {
                const {severity, title, message, duration} = action.alert;
                return {
                    ...state,
                    severity,
                    title,
                    message,
                    duration,
                    hide: false,
                };
            }
            break;
        }
        case HIDE_ALERT: {
            return {...state, hide: true};
        }
        default: {
            return state;
        }
    }
    return state;
};
