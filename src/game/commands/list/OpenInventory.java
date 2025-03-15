package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;
import game.objects.Item;

import java.util.Scanner;

public class OpenInventory extends Command {

    private WorldMap worldMap;
    private Scanner scanner;
    private Inventory inventory;

    public OpenInventory(WorldMap worldMap, Scanner scanner, Inventory inventory) {
        this.worldMap = worldMap;
        this.scanner = scanner;
        this.inventory = inventory;
    }

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

    private boolean openInventory(Inventory inventory) {
        if (inventory.getItems().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
    private String chooseItem(Integer itemId) {
        for (Item item : inventory.getItems()) {
            if (item.getItemId() == itemId) {
                return item.getDescription();
            }
        }
        return "tento item neexistuje";
    }
}
