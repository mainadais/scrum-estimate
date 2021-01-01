const preAuth = (req, res, next) => {
    try {
        if (req.cookies["accessToken"]) {
            req.headers("Authorization", `Bearer ${accessToken}`);
        } else {
            throw new Error("missing access token");
        }
    } catch (error) {
        res.clearCookie("accessToken");
        next(error);
    } finally {
        next();
    }
};

const setCookie = (res, name, value) => {
    let options = {
        maxAge: 1000 * 60 * 60 * 24 * 30, // would expire after 30 days
        httpOnly: true, // The cookie only accessible by the web server
        signed: false, // Indicates if the cookie should be signed
    };

    // Set cookie
    res.cookie(name, value, options);
};

module.exports = {
    preAuth,
    setCookie,
};
