package eu.antifuse.zurleeren;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IpChecker {

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://bot.whatismyipaddress.com/");
        BufferedReader b = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        return b.readLine();
    }
}