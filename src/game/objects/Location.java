package game.objects;

import java.util.ArrayList;

/**
 * class for location
 */
public class Location {

    private int id;
    private String name;
    private String description;
    private ArrayList<Integer> roomsToGo;
    private Npc npc;
    private Item item;

    public Location(int mark, String name, String description) {
        this.id = mark;
        this.name = name;
        this.description = description;
    }


    public int getID() {
        return id;
    }

    public void setID(int iD) {
        this.id = iD;
    }

    public String getName() {
        return name;
    }


    public ArrayList<Integer> getRoomsToGo() {
        return roomsToGo;
    }

    public void setRoomsToGo(ArrayList<Integer> roomsToGo) {
        this.roomsToGo = roomsToGo;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public Npc getNpc() {
        return npc;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    /**
     * collect item and remove it from a world map
     * @return item to the inventory
     */
    public Item collectItem(){
        Item itemToCollect = item;
        this.item = null;
        return itemToCollect;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
