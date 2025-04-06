package game;

import game.objects.Item;
import game.objects.Location;
import game.objects.Npc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * class for world map
 * there methods for loading every location,item and npc
 */
public class WorldMap implements Serializable {

    private final HashMap<Integer, Location> map = new HashMap<>();
    private int currentLocationNumber;
    private Location currentLocation;
    private ArrayList<Npc> npcs;
    private Random rand;

    public WorldMap() {
        loadMap();
        loadCharacters();
        loadItems();
        rand = new Random();
    }

    /**
     * load locations from a file
     */
    private void loadMap(){

        try(BufferedReader br = new BufferedReader(new FileReader("src/game/files/map")) ) {
          String line;

          while((line = br.readLine()) != null) {

              String[] split = line.split(";");

              Location l = new Location(Integer.parseInt(split[0]),split[1],split[2]);

              ArrayList<Integer> array = new ArrayList<>();

              for (int i = 3; i < split.length ; i++) {
                  array.add(Integer.parseInt(split[i]));
              }
              l.setRoomsToGo(array);
              map.put(l.getID(),l);

          }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentLocationNumber = 0;
        currentLocation = map.get(currentLocationNumber);

    }

    /**
     * load characters from a file
     */
    private void loadCharacters(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/game/files/characters")) ) {
            String line;
            npcs = new ArrayList<>();

            while((line = br.readLine()) != null) {

                String[] split = line.split(";");

                Npc npc = new Npc();

                npc.setRoomId(Integer.parseInt(split[0]));
                npc.setName(split[1]);
                if (Integer.parseInt(split[2]) == 1){
                    npc.setKidnapper(true);
                } else npc.setKidnapper(false);
                npc.setKeyToTextFile(split[3]);
                npc.setMovable(Boolean.parseBoolean(split[4]));

                npcs.add(npc);

                for (Location loc : map.values()) {
                    if (loc.getID() == npc.getRoomId()){
                        loc.setNpc(npc);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * load items from a file
     */
    private void loadItems(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/game/files/items")) ) {
            String line;

            while((line = br.readLine()) != null) {

                String[] split = line.split(";");

                Item item = new Item();

                item.setRoomId(Integer.parseInt(split[0]));
                item.setName(split[1]);
                item.setDescription(split[2]);
                item.setItemId(Integer.parseInt(split[3]));
                item.setCollectable(Boolean.parseBoolean(split[4]));

                for (Location loc : map.values()) {
                    if (loc.getID() == item.getRoomId()){
                        loc.setItem(item);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * checks if player can go to a location
     * @param whereToGo Integer of location where will player go
     * @return true or false if player can go there
     */
    public boolean goToNewLocations(Integer whereToGo) {

            if (currentLocation.getRoomsToGo().contains(whereToGo)) {
                currentLocationNumber = whereToGo;
                currentLocation = map.get(whereToGo);
                return true;

        }

        return false;

    }
    public void moveNpc(){
        for (Npc npc : npcs) {
            if (npc.isMovable()){
                ArrayList<Integer> roomsWhereNpcCanGo = map.get(npc.getRoomId()).getRoomsToGo();
                if (rand.nextInt(4) == 1){
                    int whereToGo = roomsWhereNpcCanGo.get(rand.nextInt(roomsWhereNpcCanGo.size()));
                     for (Location loc : map.values()) {
                        if (loc.getID() == whereToGo && loc.getNpc() == null){
                            map.get(npc.getRoomId()).setNpc(null);
                            map.get(whereToGo).setNpc(npc);
                            npc.setRoomId(whereToGo);
                            System.out.println(npc.getName() +": cau detektive presunul jsem se do: " + loc.getName());

                        }
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getListOfPossibleLocations(){
        return currentLocation.getRoomsToGo();
    }

    public int getCurrentLocationNumber() {
        return currentLocationNumber;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public HashMap<Integer, Location> getMap() {
        return map;
    }

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }
}
