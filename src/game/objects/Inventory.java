package game.objects;

import game.commands.Command;

import java.util.ArrayList;

/**
 * class for inventory
 */
public class Inventory {

    private final ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        items.add(item);
        return true;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        String items = "";
        for (Item item : getItems()){
            items += " " + item.getItemId() + " " + item.getName() + ", " ;
        }
        return "v inventari se nachazi tyto itemy: " + items;
    }
}
