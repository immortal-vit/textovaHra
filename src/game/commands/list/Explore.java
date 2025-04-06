package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;

/**
 * class for exploring each location
 */
public class Explore extends Command {

    private WorldMap worldMap;
    private Inventory inventory;

    public Explore(WorldMap worldMap, Inventory inventory) {
        this.worldMap = worldMap;
        this.inventory = inventory;
    }

    /**
     * if the player found item he will get it to its inventory
     * @return text if the player found item or not
     * if the player got item that can not be picked up he will get just the text about the item
     */
    @Override
    public String execute() {

        if (worldMap.getCurrentLocation().getItem() == null) {
            return "nic si nenasel";
        } else if (worldMap.getCurrentLocation().getItem().isCollectable()) {
            inventory.addItem(worldMap.getCurrentLocation().getItem());
            return "nasel si " + worldMap.getCurrentLocation().collectItem().getName() + ", predmet sis ulozil do inventare";
        }else {
            return "nasel si " + worldMap.getCurrentLocation().getItem().getName() + ", " + worldMap.getCurrentLocation().getItem().getDescription();
        }


    }

    @Override
    public boolean exit() {
        return false;
    }
}
