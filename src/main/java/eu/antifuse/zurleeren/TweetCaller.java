package eu.antifuse.zurleeren;

import twitter4j.TwitterException;

import java.time.ZonedDateTime;
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
            String sent = "Gesendet " + ZonedDateTime.now().toString() + ":";
            System.out.println(sent);
            if (this.tlog) {
                this.tweeter.t.sendDirectMessage(this.logUser, sent);
            }

            this.tweeter.sendTweet();
        } catch (TwitterException var5) {
            var5.printStackTrace();
        }

        int delay = this.min + this.rd.nextInt(this.max - this.min + 1);
        this.timer.schedule(new TweetCaller(this.tweeter, this.min, this.max), (long)(delay * 1000 * 60));
        String var10000 = ZonedDateTime.now().plusMinutes((long)delay).toString();
        String nae = "Naechster Tweet um " + var10000;
        System.out.println(nae);
        if (this.tlog) {
            try {
                this.tweeter.t.sendDirectMessage(this.logUser, nae);
            } catch (TwitterException var4) {
                var4.printStackTrace();
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