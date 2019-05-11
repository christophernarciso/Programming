package util;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Chris on 03/06/2018.
 */
public class MuleSettings {

    private final Properties prop;
    private File file;
    private String name, filePath;
    private int world;
    private Position tile;

    public MuleSettings(final Script bot, final String fileName) {
        this.prop = new Properties();
        this.filePath = bot.getDirectoryData() + fileName;
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    bot.log("Creating new file. file did not exist. params: null, 0, (0,0,0)");
                    saveSettings("null", 0, new Position(0, 0, 0));
                }
            } else bot.log("Mule File: " + fileName + " exists.");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @param name -   RSPlayer.getName();
     * @param world -  Worlds.getCurrentWorld();
     * @param tile -   Position.getPosition();
     * @Description -
     *              1) Clears the prop cache
     *              2) Saves parameter data to file
     */
    public void saveSettings(final String name, final int world, final Position tile) {
        if (!prop.isEmpty())
            prop.clear();

        String coords = "" + tile.getX() + "," + tile.getY() + "," + tile.getZ();
        prop.put("name", name);
        prop.put("world", Integer.toString(world));
        prop.put("tile", coords);

        try {
            prop.store(new FileOutputStream(file), "Mule Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description -
     *              1) Clears the prop cache
     *              2) Reads and loads the file from C/Users/You/OSBot/Data
     *              3) Sets MuleSettings instance variables
     */
    public void loadSettings() {
        this.file = new File(filePath);

        if (!prop.isEmpty())
            prop.clear();

        try {
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] coords = prop.getProperty("tile").split(",");
        setName(prop.getProperty("name"));
        setWorld(Integer.parseInt(prop.getProperty("world")));
        setTile(new Position(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])));
    }

    @Override
    public String toString() {
        return "[MULE INITIATED]\nname="+this.name+",world="+this.world+",position="+this.tile;
    }

    /**
     * @Description -
     *              1) Call #loadSettings() *See method doc*
     *              2) Checks if instance variables meet the condition
     */
    public boolean canMule(){
        return this.name != null && !this.name.equals("null") && this.world != 0;
    }

    /**
     * @Description -
     *              1) Resets file to default parameters
     */
    public void reset() {
        saveSettings("null", 0, new Position(0, 0, 0));
    }


    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getWorld() {
        return world;
    }

    private void setWorld(int world) {
        this.world = world;
    }

    public Position getTile() {
        return tile;
    }

    private void setTile(Position tile) {
        this.tile = tile;
    }

}
