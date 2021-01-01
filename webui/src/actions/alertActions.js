export const SHOW_ALERT = "SHOW_ALERT";
export const HIDE_ALERT = "HIDE_ALERT";
export const ALERT_TYPE = {
    error: "error",
    warning: "warning",
    success: "success",
    info: "info",
};

export const showAlert = (dispatch) => ({
                                            severity,
                                            title,
                                            message,
                                            duration,
                                        }) => {
    dispatch({
        type: SHOW_ALERT,
        alert: {severity, title, message, duration},
    });
};

export const hideAlert = (dispatch) => () => {
    dispatch({type: HIDE_ALERT});
};
