package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;
import game.objects.Item;

import java.util.Scanner;

/**
 * class for opening inventory
 */
public class OpenInventory extends Command {

    private WorldMap worldMap;
    private Scanner scanner;
    private Inventory inventory;

    public OpenInventory(WorldMap worldMap, Scanner scanner, Inventory inventory) {
        this.worldMap = worldMap;
        this.scanner = scanner;
        this.inventory = inventory;
    }

    /**
     * player will look into its inventory a will be able to write number of item to get info about it
     * @return text of the item or text that the player cant find it
     */
    @Override
    public String execute() {
        if (openInventory(inventory)){
            System.out.println(inventory.toString());
            try {
                System.out.println("napiste cislo, ktere se nachazi pred nazvem itemu o kterem si chcete neco precist");
                return chooseItem(scanner.nextInt());
            } catch (Exception _) {
                scanner.nextLine();
                return "zadal jsi neco spatne";
            }

        } else {
            return "v inventari zatim nemas zadne itemy";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }

    /**
     * will check if the player has some items in his inventory
     * @param inventory iventory from the player will be able to open
     * @return true or false it depends if the player has some items in his inventory
     */
    private boolean openInventory(Inventory inventory) {
        if (inventory.getItems().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * will get an item description
     * @param itemId number of item
     * @return item description
     */
    private String chooseItem(Integer itemId) {
        for (Item item : inventory.getItems()) {
            if (item.getItemId() == itemId) {
                return item.getDescription();
            }
        }
        return "tento item neexistuje";
    }
}
