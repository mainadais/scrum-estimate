import { useState } from "react";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import { Link } from "react-router-dom";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import InputAdornment from "@material-ui/core/InputAdornment";
import FormControl from "@material-ui/core/FormControl";
import SearchIcon from "@material-ui/icons/Search";
import IconButton from "@material-ui/core/IconButton";
import AddBoxIcon from "@material-ui/icons/AddBox";
import { useScrum } from "../context/ScrumProvider";
import { useHistory } from "react-router";

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
  margin: {
    margin: theme.spacing(1),
  },
}));

function HomeView() {
  const classes = useStyles();
  const history = useHistory();

  const [teamId, setTeamId] = useState();
  const { player, selectTeam, joinTeam, scrumTeam } = useScrum();

  const handleTeamId = (e) => {
    setTeamId(e.target.value);
  };

  const handleJoin = () => {
    joinTeam({ player: player.id, team: teamId });
    scrumTeam(teamId)
    selectTeam(teamId);
    history.push("/vote");
  };

  return (
    <div className={classes.paper}>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={4}>
          <Paper className={classes.gridPaper}>
            <Link to="/signOut">Sign Out</Link>
          </Paper>
        </Grid>
        <Grid item xs={12} sm={4}>
          <Paper className={classes.gridPaper}>
            <Link to="/vote">Play Board</Link>
          </Paper>
        </Grid>
        <Grid item xs={12}>
          <Paper className={classes.gridPaper}>
            <Typography variant="h1" component="h2">
              Dashboard
            </Typography>
          </Paper>
        </Grid>
        <Grid item xs={12} sm={4}>
          <Paper className={classes.gridPaper}>
            <Link to="/team">Manage Team</Link>
          </Paper>
        </Grid>
        <Grid item xs={12} sm={4}>
          <Paper className={classes.gridPaper}>
            <Link to="/profile">Manage Profile</Link>
          </Paper>
        </Grid>
        <Grid item xs={12}>
          <Paper className={classes.gridPaper}>
            <FormControl fullWidth className={classes.margin}>
              <InputLabel htmlFor="input-with-icon-adornment">
                Enter id and join
              </InputLabel>
              <Input
                id="input-with-icon-adornment"
                onChange={handleTeamId}
                startAdornment={
                  <InputAdornment position="start">
                    <SearchIcon />
                  </InputAdornment>
                }
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      color="secondary"
                      aria-label="join"
                      onClick={handleJoin}
                    >
                      <AddBoxIcon />
                    </IconButton>
                  </InputAdornment>
                }
              />
            </FormControl>
          </Paper>
        </Grid>
      </Grid>
    </div>
  );
}

export default HomeView;
