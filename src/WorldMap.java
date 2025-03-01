import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    private HashMap<Integer, Location> map = new HashMap<>();
    private int currentLocation;

    public WorldMap() {
        loadMap();
    }

    private boolean loadMap(){

        try(BufferedReader br = new BufferedReader(new FileReader("src/map")) ) {
          String line;

          while((line = br.readLine()) != null) {

              String[] split = line.split(",");

              Location l = new Location(Integer.parseInt(split[0]),split[1]);
              ArrayList<Integer> array = new ArrayList<>();

              for (int i = 2; i < split.length ; i++) {
                  array.add(Integer.parseInt(split[i]));
              }
              l.setRoomsToGo(array);
              map.put(l.getMark(),l);

          }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCurrentLocation(0);

        return true;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public HashMap<Integer, Location> getMap() {
        return map;
    }
}
