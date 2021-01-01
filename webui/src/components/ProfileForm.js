import {useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Typography from "@material-ui/core/Typography";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import Grid from "@material-ui/core/Grid";
import Link from "@material-ui/core/Link";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import {useHistory} from "react-router";
import {useScrum} from "../context/ScrumProvider";

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: "100%", // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    bottomLink: {
        display: "flex",
        flexDirection: "row-reverse",
    },
}));

function ProfileForm() {
    const classes = useStyles();
    const history = useHistory();

    const {player, updatePlayer} = useScrum();

    const navigateTo = (e, path) => {
        e.preventDefault();
        history.push(`/${path}`);
    };

    const [profile, setProfile] = useState({
        id: player.id,
        firstName: player.firstName,
        lastName: player.lastName,
        emailAddress: player.emailAddress,
        role: player.role,
        account: {
            username: player.account.username,
            status: player.account.status,
        },
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        if (
            profile.firstName !== player.firstName ||
            profile.lastName !== player.lastName
        ) {
            const {firstName, lastName} = profile;
            updatePlayer({player: profile.id, firstName, lastName});
        }
    };

    const handleChange = (e) => {
        const id = e.target.id;
        setProfile({...profile, [id]: e.target.value});
    };

    return (
        <div className={classes.paper}>
            <Avatar className={classes.avatar}>
                <AccountCircleIcon/>
            </Avatar>
            <Typography component="h1" variant="h5">
                Manage Profile
            </Typography>
            <form className={classes.form} noValidate>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="firstName"
                    label="First Name"
                    name="firstName"
                    autoComplete="first name"
                    autoFocus
                    value={profile.firstName}
                    onChange={handleChange}
                />
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="lastName"
                    label="Last Name"
                    name="lastName"
                    autoComplete="last name"
                    autoFocus
                    value={profile.lastName}
                    onChange={handleChange}
                />
                <TextField
                    disabled
                    variant="filled"
                    margin="normal"
                    fullWidth
                    name="emailAddress"
                    label="Email Address"
                    type="email"
                    id="emailAddress"
                    autoComplete="email address"
                    value={profile.emailAddress}
                />
                <TextField
                    disabled
                    variant="filled"
                    margin="normal"
                    fullWidth
                    name="status"
                    label="Status"
                    type="status"
                    id="status"
                    autoComplete="status"
                    value={profile.account.status}
                />
                <TextField
                    disabled
                    variant="filled"
                    margin="normal"
                    fullWidth
                    name="username"
                    label="Username"
                    id="username"
                    autoComplete="username"
                    value={profile.account.username}
                />
                <TextField
                    disabled
                    variant="filled"
                    margin="normal"
                    fullWidth
                    name="role"
                    label="Role"
                    id="role"
                    autoComplete="role"
                    value={profile.role}
                />
                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    className={classes.submit}
                    onClick={handleSubmit}
                >
                    Update
                </Button>
                <Grid container className={classes.bottomLink}>
                    <Grid item>
                        <Link href="#" variant="body2" onClick={(e) => navigateTo(e, "")}>
                            {"Back"}
                        </Link>
                    </Grid>
                </Grid>
            </form>
        </div>
    );
}

export default ProfileForm;
