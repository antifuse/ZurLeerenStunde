package eu.antifuse.zurleeren;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        File configFile = new File("config.txt");
        if (configFile.createNewFile()) {
            PrintWriter pw = new PrintWriter(new FileWriter(configFile));
            pw.println("# Fill in your Twitter app credentials here:");
            pw.println("ConsumerKey=");
            pw.println("ConsumerSecret=");
            pw.println("AccessToken=");
            pw.println("AccessTokenSecret=");
            pw.println("# Configure the bot here. Set maximum and minimum intervals (in minutes) for the bot to trigger at (40/120 by default):");
            pw.println("MinInterval=40");
            pw.println("MaxInterval=120");
            pw.println("# Admin twitter handle for DM updates");
            pw.println("AdminUser=");
            pw.close();
            System.out.println("Please fill in the config file.");
            System.exit(0);
        }

        BufferedReader cfgReader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
        HashMap<String,String> configVals = new HashMap<>();

        String line;
        while((line = cfgReader.readLine()) != null) {
            if (line.charAt(0) != '#') {
                String[] lineA = line.split("=", 2);
                if (!lineA[1].equals("")) {
                    configVals.put(lineA[0], lineA[1]);
                }
            }
        }
        if (!configVals.containsKey("ConsumerKey") || !configVals.containsKey("ConsumerSecret") || !configVals.containsKey("AccessToken") || !configVals.containsKey("AccessTokenSecret") || !configVals.containsKey("MinInterval") || !configVals.containsKey("MaxInterval")) {
            System.exit(0);
        }
        Tweeter tweeter = new Tweeter(configVals.get("ConsumerKey"), configVals.get("ConsumerSecret"), configVals.get("AccessToken"), configVals.get("AccessTokenSecret"));
        TweetCaller tweetCaller = new TweetCaller(tweeter, Integer.parseInt(configVals.get("MinInterval")), Integer.parseInt(configVals.get("MaxInterval")));
        if (configVals.containsKey("AdminUser")) {
            tweeter.setLogUser(configVals.get("AdminUser"));
            tweetCaller.setLogUser(configVals.get("AdminUser"));
        }
        tweetCaller.run();
    }
}
