import { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import clsx from "clsx";
import Button from "@material-ui/core/Button";
import Link from "@material-ui/core/Link";
import { useHistory } from "react-router";
import { useScrum } from "../context/ScrumProvider";

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
    "&:hover": {
      background: "#cccccc",
      border: "solid 1px #cccccc",
    },
  },
  buttonPaper: {
    padding: theme.spacing(1),
  },
  selectedPaper: {
    background: "#cccccc",
    border: "solid 1px #cccccc",
  },
  bottomLink: {
    display: "flex",
    flexDirection: "row-reverse",
    marginRight: 20,
  },
}));

function VoteForm() {
  const classes = useStyles();
  const history = useHistory();

  const { teams, cache, player, submitVote } = useScrum();

  const navigateTo = (e, path) => {
    e.preventDefault();
    history.push(`/${path}`);
  };

  const [values, setValues] = useState([]);
  const [selected, setSelected] = useState("");

  useEffect(() => {
    if (cache.team) {
      const choices = cache.scrum.choices;
      setValues(choices);
    } else {
      setValues([]);
    }
  }, [cache.team]);

  const handleSubmitVote = () => {
    if (selected) {
      const participant = teams
        .find((team) => team.id === cache.team)
        .participants.find((p) => p.player === player.id);
      if (participant) {
        submitVote({
          participant: participant.id,
          team: cache.team,
          vote: selected,
        });
      }
    }
  };

  return (
    <div className={classes.paper}>
      <Grid container spacing={3}>
        {values && values.length > 0 ? (
          <>
            {values.map((value, key) => (
              <Grid item xs={6} sm={3} key={key}>
                <Paper
                  className={clsx(
                    classes.gridPaper,
                    value === selected && classes.selectedPaper
                  )}
                  onClick={() => setSelected(value)}
                >
                  {value}
                </Paper>
              </Grid>
            ))}
            <Grid item xs={12}>
              <Paper className={classes.buttonPaper}>
                <Button color="primary" fullWidth onClick={handleSubmitVote}>
                  Send
                </Button>
              </Paper>
            </Grid>
          </>
        ) : (
          <Grid item xs={12}>
            <Paper className={classes.gridPaper}>No Scrum is selected</Paper>
          </Grid>
        )}
        <Grid container className={classes.bottomLink}>
          <Grid item>
            <Link href="#" variant="body2" onClick={(e) => navigateTo(e, "")}>
              {"Exit"}
            </Link>
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
}

export default VoteForm;
