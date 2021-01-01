import AppBar from "@material-ui/core/AppBar";
import PollIcon from "@material-ui/icons/Poll";
import CssBaseline from "@material-ui/core/CssBaseline";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";
import Link from "@material-ui/core/Link";
import Container from "@material-ui/core/Container";
import {BrowserRouter as Router, Redirect, Route, Switch,} from "react-router-dom";
import SignInForm from "./components/SignInForm";
import SignUpForm from "./components/SignUpForm";
import RecoverForm from "./components/RecoverForm";
import SignOutPage from "./components/SignOutPage";
import HomeView from "./components/HomeView";
import VoteForm from "./components/VoteForm";
import TeamForm from "./components/TeamsForm";
import ProfileForm from "./components/ProfileForm";
import Notification from "./components/Notification";
import ProtectedRoute from "./route/ProtectedRoute";
import {useScrum} from "./context/ScrumProvider";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            <Link color="inherit" href="https://material-ui.com/">
                Graphql Demo
            </Link>
            {" "}
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

    const {player} = useScrum();

    const isAuthenticated = player && player.accessToken;
    const redirectRoute = isAuthenticated ? `/` : "/signIn";
    return (
        <div className={classes.root}>
            <CssBaseline/>
            <AppBar position="relative">
                <Toolbar>
                    <PollIcon className={classes.icon}/>
                    <Typography variant="h6" color="inherit" noWrap>
                        <Link color="inherit" href="/" style={{textDecoration: "none"}}>
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
                            render={(props) => <SignInForm {...props} />}
                        />
                        <Route
                            path="/signUp"
                            render={(props) => <SignUpForm {...props} />}
                        />
                        <Route
                            path="/recover"
                            render={(props) => <RecoverForm {...props} />}
                        />
                        <ProtectedRoute
                            isAuthenticated={isAuthenticated}
                            path="/signOut"
                            render={(props) => (
                                <SignOutPage {...props} redirectTo={redirectRoute}/>
                            )}
                        />
                        <ProtectedRoute
                            isAuthenticated={isAuthenticated}
                            redirectRoute={redirectRoute}
                            path="/vote"
                            render={(props) => <VoteForm {...props} />}
                        />
                        <ProtectedRoute
                            isAuthenticated={isAuthenticated}
                            redirectRoute={redirectRoute}
                            path="/team"
                            render={(props) => <TeamForm {...props} />}
                        />
                        <ProtectedRoute
                            isAuthenticated={isAuthenticated}
                            redirectRoute={redirectRoute}
                            path="/profile"
                            render={(props) => <ProfileForm {...props} />}
                        />
                        <Route path="/" render={(props) => <HomeView {...props} />}/>
                    </Switch>
                    <Redirect to={redirectRoute}/>
                </Router>

                <Notification/>
            </Container>
            <footer className={classes.footer}>
                <Typography variant="h6" align="center" gutterBottom>
                    works.hop
                </Typography>
                <Typography
                    variant="subtitle1"
                    align="center"
                    color="textSecondary"
                    component="p"
                >
                    Scrumming away!
                </Typography>
                <Copyright/>
            </footer>
        </div>
    );
}

export default App;
