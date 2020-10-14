import Twitter = require("twit");
import * as moment from "moment";
const config = require("./config.json")
const t = new Twitter({
    consumer_key: config["consumer-key"],
    consumer_secret: config["consumer-secret"],
    access_token: config["access-token"],
    access_token_secret: config["access-secret"]
});

function doit() {
    let action = between(1,91);
    if (action < 31) exact()
    else if (action < 61) altered()
    else random()
    setTimeout(doit,between(config.min * 60000, config.max * 60000));
}

function exact() {
    let status = `Es ist jetzt ${moment().locale("de").format("LT")} Uhr.`;
    t.post("statuses/update", {
        status: status
    })
    console.log(status);
}

function altered() {
    let status = `Es ist jetzt ${moment().add(between(1,15),"minutes").locale("de").format("LT")} Uhr.`;
    t.post("statuses/update", {
        status: status
    })
    console.log(status);
}

function random() {
    let status = `Es ist jetzt ${moment({hour:between(0,24), minute: between(0,60)}).locale("de").format("LT")} Uhr.`;
    t.post("statuses/update", {
        status: status
    })
    console.log(status);
}

function between(min, max) {
    return Math.floor(
        Math.random() * (max - min) + min
    )
}

doit();
