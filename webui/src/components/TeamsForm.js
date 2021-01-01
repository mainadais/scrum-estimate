import { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Typography from "@material-ui/core/Typography";
import GroupIcon from "@material-ui/icons/Group";
import Grid from "@material-ui/core/Grid";
import Link from "@material-ui/core/Link";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import ListItemSecondaryAction from "@material-ui/core/ListItemSecondaryAction";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import PersonIcon from "@material-ui/icons/Person";
import ClearAllIcon from "@material-ui/icons/ClearAll";
import RemoveCircleOutlineIcon from "@material-ui/icons/RemoveCircleOutline";
import IconButton from "@material-ui/core/IconButton";
import EditAttributesIcon from "@material-ui/icons/EditAttributes";
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import AccordionDetails from "@material-ui/core/AccordionDetails";
import Paper from "@material-ui/core/Paper";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
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
  bottomLink: {
    display: "flex",
    flexDirection: "row-reverse",
  },
  myTeams: {
    marginTop: theme.spacing(2),
  },
  participantsList: {
    width: "100%",
  },
}));

function TeamForm() {
  const classes = useStyles();
  const history = useHistory();

  const {
    player,
    teams,
    cache,
    scrumTeam,
    createTeam,
    selectTeam,
    dropTeam,
    updateTeam,
    joinTeam,
    dropParticipant,
    clearParticipants,
    findTeamsByOrganizer,
    findTeamsJoined,
  } = useScrum();

  const navigateTo = (e, path) => {
    e.preventDefault();
    history.push(`/${path}`);
  };

  const resetForm = () => ({
    name: "",
    choices: "1,2,3,5,8,13,21",
  });

  useEffect(() => {
    findTeamsJoined(player.id);
  }, []);

  const [form, setForm] = useState(resetForm());

  const handleSubmit = (e) => {
    e.preventDefault();
    const team = {
      ...form,
      organizer: player.id,
      choices: form.choices.split(","),
    };
    createTeam(team);
    setForm({ ...form, name: "" });
  };

  const handleChange = (e) => {
    const id = e.target.id;
    setForm({ ...form, [id]: e.target.value });
  };

  const handleSelected = (e, id) => {
    e.preventDefault();
    handleJoinTeam(id);
    history.push("/vote");
  };

  const handleDropTeam = (id) => {
    dropTeam(id);
  };

  const handleDropParticipant = ({ player, team }) => {
    dropParticipant({ player, team });
  };

  const handleClearParticipants = (id) => {
    clearParticipants(id);
  };

  const handleEditTeam = () => {
    const teamInfo = { ...cache.selectedTeam, ...form };
    updateTeam(teamInfo);
  };

  const handleJoinTeam = (id) => {
    joinTeam({ player: player.id, team: id });
    scrumTeam(id)
    selectTeam(id);
  };

  function ListItemLink({ player, team }) {
    return (
      <ListItem button component="a">
        <ListItemIcon>
          <PersonIcon />
        </ListItemIcon>
        <ListItemText id="team-icon" primary={player} onClick={resetForm} />
        <ListItemSecondaryAction>
          <IconButton
            edge="end"
            color="secondary"
            aria-label="delete"
            onClick={() => handleDropParticipant({ player, team })}
          >
            <RemoveCircleOutlineIcon />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    );
  }

  useEffect(() => {
    const { id } = player;
    if (id) {
      findTeamsByOrganizer(id);
    }
  }, [player.id]);

  return (
    <div className={classes.paper}>
      <Avatar className={classes.avatar}>
        <GroupIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        Manage Teams
      </Typography>
      <form className={classes.form} noValidate>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          id="name"
          label="Team Name"
          name="name"
          autoComplete="name"
          autoFocus
          value={form.name}
          onChange={handleChange}
        />
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          name="choices"
          label="Choices (comma-separated)"
          type="choices"
          id="choices"
          autoComplete="choices"
          value={form.choices}
          onChange={handleChange}
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={handleSubmit}
        >
          Submit
        </Button>

        <div className={classes.myTeams}>
          {teams.map((team, index) => (
            <Accordion key={team.id}>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-label="Expand"
                aria-controls={`additional-actions${index}-content`}
                id={`additional-actions${index}-header`}
              >
                <RemoveCircleOutlineIcon
                  style={{ marginRight: "5px" }}
                  color="secondary"
                  onClick={() => handleDropTeam(team.id)}
                />
                <ClearAllIcon
                  style={{ marginRight: "5px" }}
                  color="secondary"
                  onClick={() => handleClearParticipants(team.id)}
                />
                <EditAttributesIcon
                  style={{ marginRight: "5px" }}
                  color="primary"
                  onClick={() => handleEditTeam(team.id)}
                />
                <Paper elevation={0}>
                  <i>{team.name}</i>{" "}
                  {cache.team !== team.id ? (
                    <a href="#" onClick={(e) => handleSelected(e, team.id)}>
                      join
                    </a>
                  ) : (
                    <i color="secondary">joined</i>
                  )}{" "}
                  <a
                    href={`/vote/${team.id}`}
                    onClick={(e) => e.preventDefault()}
                  >
                    share
                  </a>
                </Paper>
              </AccordionSummary>
              <AccordionDetails>
                <List
                  component="nav"
                  aria-label="secondary"
                  className={classes.participantsList}
                >
                  {team.participants &&
                    team.participants.map(({ player, team }) => (
                      <ListItemLink key={player} player={player} team={team} />
                    ))}
                </List>
              </AccordionDetails>
            </Accordion>
          ))}
        </div>

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

export default TeamForm;
