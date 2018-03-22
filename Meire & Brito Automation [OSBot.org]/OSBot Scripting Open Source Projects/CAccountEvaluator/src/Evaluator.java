import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

/* Some Source From https://osbot.org/forum/topic/139400-basic-bank-evaluator/ */
@ScriptManifest(author = "Chris", name = "Bank Evaluator", info = "Determines the bank value [ignores cosmetic items [lava whip, slayer helm (i),...etc]}", logo = "", version = 1)
public class Evaluator extends Script {

    private int wealth = 0, index = 0, pin = 0;
    private long startTime, endTime;
    private java.util.List<String> accList;
    private String user, pass;
    private static final String newLine = System.getProperty("line.separator");
    private static final Pattern pattern = Pattern.compile("(?:\"overall\":)([0-9]+)");

    @Override
    public void onPaint(Graphics2D paint) {
        paint.setColor(Color.ORANGE);
        paint.drawString("Wealth: " + formatIntegers(wealth), 10, 340);
    }

    @Override
    public void onStart() {
        startTime = System.currentTimeMillis();
        user = getBot().getUsername();
        pass = "Default";
        try {
            accList = Files.readAllLines(Paths.get(getDirectoryData() + "UnevaluatedAccounts.txt")
                    , StandardCharsets.UTF_8);
        } catch (IOException f) {
            log(f + "\n");
        }
        log("Loaded accounts: " + (accList.size()) + "\n");
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (getBank().isOpen()) {
            wealth = 0;
            //Check equipment
            for (Item h : getEquipment().getItems()) {
                if (h != null) {
                    try {
                        wealth += (getPrice(h.getId()) * h.getAmount());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Check inventory
            for (Item i : getInventory().getItems()) {
                if (i != null) {
                    try {
                        wealth += i.getId() == 995 ? i.getAmount() : i.getId() == 13204 ? (i.getAmount() * 1000) : (getPrice(i.getId()) * i.getAmount());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Check bank
            for (Item j : getBank().getItems()) {
                if (j != null) {
                    try {
                        wealth += j.getId() == 995 ? j.getAmount() : j.getId() == 13204 ? (j.getAmount() * 1000) : (getPrice(j.getId()) * j.getAmount());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Write to EvaluatedAccounts.txt & Switch to next account
            endTime = System.currentTimeMillis() - startTime;
            if (wealth != 0) {
                writeToFile("" + user + ":" + pass + ":" + pin + " Bank Overall $: " + formatIntegers(wealth));
                if (index >= accList.size()) {
                    stop(false);
                } else {
                    String[] x = accList.get(index).split(":");
                    user = x[0];
                    pass = x[1];
                    pin = x.length > 2 ? Integer.parseInt(x[2]) : 1234;
                    if (new ConditionalSleep(20000) {
                        @Override
                        public boolean condition() throws InterruptedException {
                            return getBot().switchAccount(user, pass, pin);
                        }
                    }.sleep()) {
                        index++;
                    } else {
                        log("Failed to switch accounts.");
                        stop(false);
                    }
                }
            }
        } else {
            if (getBank().open()) {
                new ConditionalSleep(3000, 1500) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getBank().isOpen();
                    }
                }.sleep();
            } else
                getWalking().webWalk(Banks.EDGEVILLE, Banks.AL_KHARID, Banks.DUEL_ARENA, Banks.CAMELOT, Banks.GRAND_EXCHANGE, Banks.ARDOUGNE_NORTH, Banks.ARDOUGNE_SOUTH,
                        Banks.CASTLE_WARS, Banks.CATHERBY, Banks.VARROCK_EAST, Banks.VARROCK_WEST, Banks.YANILLE, Banks.TZHAAR, Banks.LUMBRIDGE_UPPER, Banks.PEST_CONTROL,
                        Banks.GNOME_STRONGHOLD, Banks.DRAYNOR);
        }
        return 2000;
    }

    private String formatIntegers(int num) {
        return NumberFormat.getInstance().format(num);
    }

    private static int getPrice(int id) throws MalformedURLException, IOException {
        String url = "http://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + id;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new URL(String.format(url, id)).openConnection().getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher m = pattern.matcher(line);
            if (m.find() && m.groupCount() > 0) {
                int overallPrice = Integer.parseInt(m.group(1));
                if (overallPrice == 0) break;
                return overallPrice;
            }
        }
        reader.close();
        return 0;
    }

    private synchronized void writeToFile(String msg) {
        log(msg);
        String fileName = getDirectoryData() + "EvaluatedAccounts.txt";
        PrintWriter printWriter = null;
        File file = new File(fileName);
        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(newLine + msg);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

}