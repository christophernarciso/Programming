import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.util.ItemContainer;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import util.json.JsonObject;
import util.json.util.exchange.ExchangeItem;

@ScriptManifest(author = "Chris", name = "Bank Evaluator", info = "Determines the bank value [ignores cosmetic items [lava whip, slayer helm (i),...etc]}", logo = "", version = 1)
public class Evaluator extends Script {

    private int wealth = 0, index = 0, pin = 0;
    private java.util.List<String> accList;
    private String user, pass;
    private static final String newLine = System.getProperty("line.separator");
    private Map<String, ExchangeItem> itemCacheData = new HashMap<>();

    @Override
    public void onPaint(Graphics2D paint) {
        paint.setColor(Color.ORANGE);
        paint.drawString("Wealth: " + formatIntegers(wealth), 10, 340);
    }

    @Override
    public void onStart() {
        //Default starting parameters
        user = getBot().getUsername();
        pass = "Default";
        //Load file "UnevaluatedAccounts.txt" which holds user:pass:pin format list of accounts. ex. chris:lolman21:1234
        try {
            accList = Files.readAllLines(Paths.get(getDirectoryData() + "UnevaluatedAccounts.txt")
                    , StandardCharsets.UTF_8);
        } catch (IOException f) {
            log(f + "\n");
        }
        log("Loaded accounts: " + accList.size() + "\n");
        //Grab RSBuddy summary.json
        itemCacheData = getJsonPriceCache();
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (getBank().isOpen()) {
            //Track calculation speed
            long startTime = System.currentTimeMillis();
            wealth = 0;
            //Check equipment
            calculate(getEquipment());
            //Check inventory
            calculate(getInventory());
            //Check bank
            calculate(getBank());
            //Calculation speed evaluation
            long endTime = System.currentTimeMillis() - startTime;
            log("Calculation speed: " + endTime + "ms");
            //Write to EvaluatedAccounts.txt & Switch to next account
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
        } else {
            if (getBank().open()) {
                new ConditionalSleep(3000, 1500) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getBank().isOpen();
                    }
                }.sleep();
            } else {
                getWalking().webWalk(Banks.EDGEVILLE, Banks.AL_KHARID, Banks.DUEL_ARENA, Banks.CAMELOT, Banks.GRAND_EXCHANGE, Banks.ARDOUGNE_NORTH, Banks.ARDOUGNE_SOUTH,
                        Banks.CASTLE_WARS, Banks.CATHERBY, Banks.VARROCK_EAST, Banks.VARROCK_WEST, Banks.YANILLE, Banks.TZHAAR, Banks.LUMBRIDGE_UPPER, Banks.PEST_CONTROL,
                        Banks.GNOME_STRONGHOLD, Banks.DRAYNOR);
            }
        }
        return 2000;
    }

    private String formatIntegers(int num) {
        return NumberFormat.getInstance().format(num);
    }

    private Map<String, ExchangeItem> getJsonPriceCache() {
        Map<String, ExchangeItem> cache = new HashMap<>();

        try {
            URL url = new URL("https://rsbuddy.com/exchange/summary.json");
            BufferedReader jsonFile = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonObject priceJSON = JsonObject.readFrom(jsonFile.readLine());

            for (JsonObject.Member aPriceJSON : priceJSON) {
                JsonObject itemJSON = priceJSON.get(aPriceJSON.getName()).asObject();
                cache.put(itemJSON.get("name").asString(), new ExchangeItem(itemJSON.get("name").asString(), itemJSON.get("id").asInt(), itemJSON.get("sell_average").asInt(),
                        itemJSON.get("overall_average").asInt(), itemJSON.get("buy_average").asInt()));
            }
        } catch (Exception e) {
            log("Failed to grab item price cache!");
        }
        return cache;
    }

    private void calculate(ItemContainer container) {
        for (Item item : container.getItems()) {
            if (item != null) {
                switch (item.getId()) {
                    case 955:
                        this.wealth += item.getAmount();
                        break;
                    case 13204:
                        this.wealth += item.getAmount() * 1000;
                        break;
                    default:
                        if (this.itemCacheData.get(item.getName()) != null)
                            this.wealth += itemCacheData.get(item.getName()).getSellAverage() * item.getAmount();
                        break;
                }
            }
        }
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