package eu.antifuse.zurleeren;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class Tweeter {
    Twitter t;
    TwitterFactory tf;
    boolean tlog;
    String logUser;

    public Tweeter(String CKey, String CSecret, String AToken, String ASecret) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(CKey).setOAuthConsumerSecret(CSecret).setOAuthAccessToken(AToken).setOAuthAccessTokenSecret(ASecret);
        this.tf = new TwitterFactory(cb.build());
        this.t = this.tf.getInstance();
        this.tlog = false;
    }

    public void sendTweet() throws TwitterException {
        Random rd = new Random();
        int random = rd.nextInt(100);
        String s;
        String log;
        if (random < 33) {
            s = this.exakt();
            log = "(Richtige Uhrzeit): " + s;
        } else if (random < 66) {
            s = this.knapp();
            log = "(GeÃ¤nderte Uhrzeit): " + s;
        } else {
            s = this.weit();
            log = "(ZufÃ¤llige Uhrzeit): " + s;
        }

        this.t.updateStatus(s);
        System.out.println(log);
        if (this.tlog) {
            this.t.sendDirectMessage(this.logUser, log);
        }

    }

    public String exakt() {
        ZonedDateTime z = ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
        int h = z.getHour();
        return "Es ist jetzt " + h + ":" + String.format("%02d", z.getMinute()) + " Uhr.";
    }

    public String knapp() {
        ZonedDateTime z = ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
        Random rd = new Random();
        z = z.plusMinutes((long)(rd.nextInt(30) - 15));
        int h = z.getHour();
        return "Es ist jetzt " + h + ":" + String.format("%02d", z.getMinute()) + " Uhr.";
    }

    public String weit() {
        Random rd = new Random();
        int std = rd.nextInt(24);
        int min = rd.nextInt(60);
        String minS = String.format("%02d", min);
        return "Es ist jetzt " + std + ":" + minS + " Uhr.";
    }

    public String getLogUser() {
        return this.logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
        this.tlog = true;
    }
}
