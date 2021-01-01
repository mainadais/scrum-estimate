import {useEffect} from "react";
import {makeStyles} from "@material-ui/core/styles";
import {Alert, AlertTitle} from "@material-ui/lab";
import clsx from "clsx";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import {useScrum} from "../context/ScrumProvider";

const useStyles = makeStyles((theme) => ({
    root: {
        width: "100%",
        "& > * + *": {
            marginTop: theme.spacing(2),
        },
    },
    displayable: {
        display: "none",
    },
    alert: {
        marginTop: theme.spacing(2),
    },
    alertPaper: {
        color: theme.palette.text.secondary,
    },
}));

export default function Notification() {
    const classes = useStyles();

    const {alert, hideAlert} = useScrum();

    useEffect(() => {
        if (alert.duration && alert.duration > 0) {
            setTimeout(() => {
                hideAlert();
            }, alert.duration);
        }
    }, [alert]);

    return (
        <div className={clsx(classes.root, alert.hide && classes.displayable)}>
            <Grid container spacing={3} className={classes.alert}>
                <Grid item xs={12}>
                    <Paper className={classes.alertPaper}>
                        <Alert severity={alert.severity} onClose={hideAlert}>
                            <AlertTitle>{alert.severity}</AlertTitle>
                            {alert.title && <span>{alert.title} -</span>}
                            <strong>{alert.message}</strong>
                        </Alert>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}
