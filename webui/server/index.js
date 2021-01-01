const express = require("express");
const cors = require("cors");
const cookieParser = require("cookie-parser");
const bodyParser = require("body-parser");

const app = express();
app.use(cookieParser());
app.use(cors());
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

const apiRoutes = require("./router");
app.use("/api", apiRoutes);

const port = 3000;
const socketio = require("socket.io");
const server = app.listen(port, () => {
    console.log(`server up on port ${port}`);
});

const io = socketio(server);

io.on("connection", (socket) => {
    socket.on("join-scrum", (teamId, playerId) => {
        socket.join(teamId);

        socket.to(teamId).broadcast.emit("player-joined", playerId);

        socket.on("disconnect", () => {
            socket.to(teamId).broadcast.emit("player-jeft", playerId);
        });
    });
});
