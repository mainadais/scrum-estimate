const router = require('express').Router()
const playerRoutes = require('./player')

router.use('/player', playerRoutes)

module.exports = router