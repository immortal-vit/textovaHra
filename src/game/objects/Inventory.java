package game.objects;

import game.commands.Command;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;


    public boolean addItem(Item item) {
        items.add(item);
        return true;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
