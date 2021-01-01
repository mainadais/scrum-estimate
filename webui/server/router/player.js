const axios = require("axios");
const router = require("express").Router();
const {preAuth, setCookie} = require("../middleware");

const endpoint = "http://localhost:7080/graphql";

router.post("/signIn", async (req, res) => {
    try {
        const playerInfo = await axios.post(`${endpoint}/signIn`, req.body);
        if (playerInfo.accesToken) {
            setCookie(res, "accessToken", accessToken);
        }
        res.json(data);
    } catch (err) {
        res.json(err);
    }
});

router.post("/signOut", preAuth, async (req, res) => {
    try {
        await axios.post(`${endpoint}/signOut`, req.body);
        res.clearCookie("accessToken");
        res.json(data);
    } catch (err) {
        res.json(err);
    }
});

module.exports = router;
