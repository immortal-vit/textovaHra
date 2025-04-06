package game.commands.list;
import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/**
 * command for saving a game
 */
public class Save extends Command {

    private Inventory inventory;
    private WorldMap worldMap;

    public Save(Inventory inventory, WorldMap worldMap) {
        this.inventory = inventory;
        this.worldMap = worldMap;
    }

    /**
     * will save game
     * @return text that game is saved
     */
    @Override
    public String execute() {
        if (saveInventory() && saveMap()){
            return "hra se ulozila";
        }
        return "hra se nepodarila ulozit";

    }

    @Override
    public boolean exit() {
        return false;
    }

    /**
     * will serialize an inventory
     */
    public boolean saveInventory(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/game/files/inventory.ser"))) {
            oos.writeObject(inventory);
            oos.close();
            return true;
        } catch (Exception e){
            System.out.println("nepodarilo se ulozit soubor");
            return false;
        }
    }

    /**
     * will serialize a map
     */
    public boolean saveMap(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/game/files/map.ser"))) {
            oos.writeObject(worldMap);
            oos.close();
            return true;
        } catch (Exception e){
            System.out.println("nepodarilo se ulozit soubor");
            return false;
        }
    }

}
