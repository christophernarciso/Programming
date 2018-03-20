package org.script.orb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RsBuddyPriceGrab {
    private static InputStream is;
    private static InputStreamReader isr;
    private static BufferedReader br;

    private static String[] getData(int itemID) {
        try {
            URL url = new URL("https://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + itemID);
            HttpURLConnection huc = (HttpURLConnection)url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            huc.setConnectTimeout(2000);
            huc.setReadTimeout(2000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();
            is = huc.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = br.readLine();
            if (line != null) {
                return line.split(",");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                } else if (isr != null) {
                    isr.close();
                } else if (is != null) {
                    is.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getOverall(int itemID) {
        String[] data = RsBuddyPriceGrab.getData(itemID);
        if (data != null) {
            return Integer.parseInt(data[0].substring(data[0].indexOf("ll") + 4, data[0].length()));
        }
        return 0;
    }

    public static int getBuyingPrice(int itemID) {
        String[] data = RsBuddyPriceGrab.getData(itemID);
        if (data != null) {
            return Integer.parseInt(data[1].substring(data[1].indexOf("ng") + 4, data[1].length()));
        }
        return 0;
    }

    public static int getSellingPrice(int itemID) {
        String[] data = RsBuddyPriceGrab.getData(itemID);
        if (data != null) {
            return Integer.parseInt(data[3].substring(data[3].indexOf("ng") + 4, data[3].length()));
        }
        return 0;
    }
}

