import { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Typography from "@material-ui/core/Typography";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import { useHistory } from "react-router";
import { useScrum } from "../context/ScrumProvider";
import useLocalStorage from "../hooks/useLocalStorage";

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
}));

function SignInForm() {
  const classes = useStyles();
  const history = useHistory();

  const { signIn } = useScrum();
  const [loginForm, saveForm] = useLocalStorage("login", {});

  const navigateTo = (e, path) => {
    e.preventDefault();
    history.push(`/${path}`);
  };

  const [form, setForm] = useState({
    emailAddress: "",
    password: "",
    remember: false,
    ...loginForm,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    saveForm(form.remember ? form : {});
    signIn(form);
  };

  const handleChange = (e) => {
    const id = e.target.id;
    setForm({ ...form, [id]: e.target.value });
  };

  const handleRemember = (e) => {
    setForm({ ...form, remember: e.target.checked });
  };

  return (
    <div className={classes.paper}>
      <Avatar className={classes.avatar}>
        <LockOutlinedIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        Sign In
      </Typography>
      <form className={classes.form} noValidate>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          id="emailAddress"
          label="Email Address"
          name="emailAddress"
          autoComplete="emailAddress"
          autoFocus
          value={form.emailAddress}
          onChange={handleChange}
        />
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          name="password"
          label="Password"
          type="password"
          id="password"
          autoComplete="current-password"
          value={form.password}
          onChange={handleChange}
        />
        <FormControlLabel
          control={
            <Checkbox
              id="remember"
              checked={form.remember}
              value="remember"
              color="primary"
              onClick={handleRemember}
            />
          }
          label="Remember me"
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={handleSubmit}
        >
          Sign In
        </Button>
        <Grid container>
          <Grid item xs>
            <Link
              href="#"
              variant="body2"
              onClick={(e) => navigateTo(e, "recover")}
            >
              Forgot password?
            </Link>
          </Grid>
          <Grid item>
            <Link
              href="#"
              variant="body2"
              onClick={(e) => navigateTo(e, "signUp")}
            >
              {"Don't have an account? Sign Up"}
            </Link>
          </Grid>
        </Grid>
      </form>
    </div>
  );
}

export default SignInForm;
