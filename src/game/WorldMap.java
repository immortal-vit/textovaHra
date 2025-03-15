package game;

import game.objects.Item;
import game.objects.Location;
import game.objects.Npc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    private final HashMap<Integer, Location> map = new HashMap<>();
    private int currentLocationNumber;
    private Location currentLocation;
    private ArrayList<Npc> npcs;

    public WorldMap() {
        loadMap();
        loadCharacters();
        loadItems();
    }

    private void loadMap(){

        try(BufferedReader br = new BufferedReader(new FileReader("src/game/files/map")) ) {
          String line;

          while((line = br.readLine()) != null) {

              String[] split = line.split(",");

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
                npc.setDialog(split[3]);

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


    public boolean goToNewLocations(Integer whereToGo) {

            if (currentLocation.getRoomsToGo().contains(whereToGo)) {
                currentLocationNumber = whereToGo;
                currentLocation = map.get(whereToGo);
                return true;

        }

        return false;

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
