import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import { Link } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  gridPaper: {
    padding: theme.spacing(2),
    textAlign: "center",
    color: theme.palette.text.secondary,
  },
}));

function TokenView() {
  const classes = useStyles();

  return (
    <div className={classes.paper}>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6}>
          <Paper className={classes.gridPaper}>
            <Link to="/signIn">Sign In</Link>
          </Paper>
        </Grid>
        <Grid item xs={12} sm={6}>
          <Paper className={classes.gridPaper}>
            <Link to="/signUp">Sign Up</Link>
          </Paper>
        </Grid>
        <Grid item xs={12}>
          <Paper className={classes.gridPaper}>Output</Paper>
        </Grid>
      </Grid>
    </div>
  );
}

export default TokenView;
