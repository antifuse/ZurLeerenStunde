package eu.antifuse.zurleeren;

import twitter4j.TwitterException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TweetCaller extends TimerTask {
    Tweeter tweeter;
    Timer timer = new Timer();
    Random rd = new Random();
    int min;
    int max;
    boolean tlog;
    String logUser;

    public TweetCaller(Tweeter tweeter, int min, int max) {
        this.min = min;
        this.max = max;
        this.tweeter = tweeter;
        this.tlog = false;
    }

    public void run() {
        try {
            String sent = "Gesendet " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)) + ":";
            System.out.println(sent);
            if (this.tlog) this.tweeter.t.sendDirectMessage(this.logUser, sent);
            this.tweeter.sendTweet();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        int delay = this.min + this.rd.nextInt(this.max - this.min + 1);
        TweetCaller tc = new TweetCaller(this.tweeter, this.min, this.max);
        if (tlog) tc.setLogUser("ntifuse");
        this.timer.schedule(tc, (delay * 1000 * 60));
        String nae = "Naechster Tweet um " + ZonedDateTime.now().plusMinutes(delay).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG));
        System.out.println(nae);
        if (this.tlog) {
            try {
                this.tweeter.t.sendDirectMessage(this.logUser, nae);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

    }

    public String getLogUser() {
        return this.logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
        this.tlog = true;
    }
}