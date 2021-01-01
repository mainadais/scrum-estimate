import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Typography from "@material-ui/core/Typography";
import LockOpenIcon from "@material-ui/icons/LockOpen";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import { useHistory } from "react-router";
import { useScrum } from "../context/ScrumProvider";

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

function RecoverForm() {
  const classes = useStyles();
  const history = useHistory();

  const { cache, recoverLogin } = useScrum();

  const [form, setForm] = useState({
    emailAddress: "",
    emailToken: "",
    newPassword: "",
    tokenAvailable: false,
  });

  useEffect(() => {
    if (cache.startRoute !== "/") {
      setForm({ email: "", emailToken: "", newPassword: "" });
      // navigateTo(cache.startRoute);
    }
  }, [cache.startRoute]);

  const navigateTo = (e, path) => {
    e.preventDefault();
    history.push(`/${path}`);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    recoverLogin(form);
  };

  const handleChange = (e) => {
    const id = e.target.id;
    setForm({ ...form, [id]: e.target.value });
  };

  const handleChecked = (e) => {
    setForm({ ...form, tokenAvailable: e.target.checked });
  };

  return (
    <div className={classes.paper}>
      <Avatar className={classes.avatar}>
        <LockOpenIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        Recover Password
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
        <FormControlLabel
          control={
            <Checkbox
              id="tokenAvailable"
              checked={form.tokenAvailable}
              color="primary"
              onClick={handleChecked}
            />
          }
          label="I have a recovery token already"
        />
        {form.tokenAvailable && (
          <>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="emailToken"
              label="Recovery Token"
              type="text"
              id="emailToken"
              autoComplete="recovery-token"
              value={form.emailToken}
              onChange={handleChange}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="newPassword"
              label="New Password"
              type="password"
              id="newPassword"
              autoComplete="new-password"
              value={form.newPassword}
              onChange={handleChange}
            />
          </>
        )}
        <Button
          type="submit"
          fullWidth
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={handleSubmit}
        >
          Recover
        </Button>
        <Grid container>
          <Grid item>
            <Link
              href="#"
              variant="body2"
              onClick={(e) => navigateTo(e, "signIn")}
            >
              {"Sign In instead"}
            </Link>
          </Grid>
        </Grid>
      </form>
    </div>
  );
}

export default RecoverForm;
