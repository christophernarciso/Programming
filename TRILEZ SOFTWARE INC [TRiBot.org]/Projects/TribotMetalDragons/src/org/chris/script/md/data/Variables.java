package org.chris.script.md.data;

public class Variables {

    /**
     To manage variables, I use a Vars class. The Vars class will be a singleton. This is for thread safety when dealing with threads
     that are shared between classloaders such as the Event Dispatch Thread. To access a variable, you will call Vars.get().variableName
     **/

    private static final Variables instance = new Variables();

    public int eatAt;

    public boolean shouldDrinkAntifire, shouldDrinkRangingPotion, shouldLoot;

    public static Variables get(){
        return instance;
    }

    private Variables(){

    }
}
