package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load extends Command {

    public Inventory inventory;
    public WorldMap worldMap;

    public Load() {
    }

    /**
     *
     * @return loaded inventory and map
     */
    @Override
    public String execute() {
        if (loadInventory() && loadMap()){
            return "hra byla nactena";
        }
        return "hru se nepodarilo nacist";

    }

    @Override
    public boolean exit() {
        return false;
    }

    /**
     * load inventory
     */
    private boolean loadInventory(){
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("src/game/files/inventory.ser"))) {
            inventory = (Inventory) oos.readObject();
            oos.close();
            return true;
        } catch (Exception e){
            System.out.println("nepodarilo se nacist soubor");
            return false;
        }
    }

    /**
     * load map
     */
    private boolean loadMap(){
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("src/game/files/map.ser"))) {
            worldMap = (WorldMap) oos.readObject();
            System.out.println("v teto ulozene pozici se nachazite v : " + worldMap.getCurrentLocation().getName());
            oos.close();
            return true;
        } catch (Exception e){
            System.out.println("nepodarilo se nacist soubor");
            return false;
        }
    }
}
