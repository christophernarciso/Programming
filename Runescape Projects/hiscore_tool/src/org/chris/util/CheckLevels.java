package org.chris.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
//usage int woodLevel = new CheckLevels("PlayerName",wood).getTargetLevel();

public class CheckLevels {
    private int targetLevel;

    public CheckLevels(String username, int levelToCheck) {
        try {
            URL url = new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username);
            URLConnection urlConnection = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String scores;
            while ((scores = input.readLine()) != null) {
                sb.append(scores);
            }
            input.close();
            String jsonResult = sb.toString();
            if (jsonResult.contains("404 - Page not found")) {
                this.targetLevel = 1;

            } else {
                List<String> splitSkills = Arrays.asList(jsonResult.split(","));

                this.targetLevel = Integer.parseInt(splitSkills.get(levelToCheck));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTargetLevel() {
        return this.targetLevel;
    }

}