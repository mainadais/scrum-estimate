import React from "react";
import AppBar from "@material-ui/core/AppBar";
import PollIcon from "@material-ui/icons/Poll";
import CssBaseline from "@material-ui/core/CssBaseline";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Link from "@material-ui/core/Link";
import Container from "@material-ui/core/Container";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SignInForm from "./components/SignInForm";
import SignUpForm from "./components/SignUpForm";
import TokenView from "./components/TokenView";
import { handleLogin } from "./actions/playerActions";

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {"Copyright © "}
      <Link color="inherit" href="https://material-ui.com/">
        Graphql Demo
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    flexDirection: "column",
    minHeight: "100vh",
  },
  icon: {
    marginRight: theme.spacing(2),
  },
  main: {
    marginTop: theme.spacing(8),
    marginBottom: theme.spacing(2),
  },
  footer: {
    padding: theme.spacing(3, 2),
    marginTop: "auto",
    backgroundColor:
      theme.palette.type === "light"
        ? theme.palette.grey[200]
        : theme.palette.grey[800],
  },
}));

function App() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="relative">
        <Toolbar>
          <PollIcon className={classes.icon} />
          <Typography variant="h6" color="inherit" noWrap>
            <Link color="inherit" href="/" style={{ textDecoration: "none" }}>
              Scrum Estimate
            </Link>
          </Typography>
        </Toolbar>
      </AppBar>
      <Container component="main" className={classes.main} maxWidth="sm">
        <Router>
          <Switch>
            <Route
              path="/signIn"
              render={(props) => (
                <SignInForm {...props} handleLogin={handleLogin} />
              )}
            />
            <Route
              path="/signUp"
              render={(props) => <SignUpForm {...props} />}
            />
            <Route path="/" render={(props) => <TokenView {...props} />} />
          </Switch>
        </Router>
      </Container>
      <footer className={classes.footer}>
        <Typography variant="h6" align="center" gutterBottom>
          Footer
        </Typography>
        <Typography
          variant="subtitle1"
          align="center"
          color="textSecondary"
          component="p"
        >
          Scrumming away!
        </Typography>
        <Copyright />
      </footer>
    </div>
  );
}

export default App;
