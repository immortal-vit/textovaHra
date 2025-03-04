import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WorldMap {

    private final HashMap<Integer, Location> map = new HashMap<>();
    private int currentLocationNumber;
    private Location currentLocation;

    public WorldMap() {
        loadMap();
    }

    private void loadMap(){

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
        currentLocationNumber = 0;
        currentLocation = map.get(currentLocationNumber);

    }

    public boolean goToNewLocations(Scanner scanner) {

        String listOfLocations = "";
        ArrayList<Integer> arrayOfLocations =  getListOfPossibleLocations();

        for (int i = 0; i < arrayOfLocations.size(); i++) {
            listOfLocations += arrayOfLocations.get(i) + " " + map.get(arrayOfLocations.get(i)).getName() + ", " ;
        }
        System.out.println(listOfLocations);
        System.out.println("napiste cislo lokace do ktere chcete jit");

        try {
            int loacationToGo = scanner.nextInt();

            for (Integer integer : currentLocation.getRoomsToGo()) {
                if (currentLocation.getRoomsToGo().contains(loacationToGo)) {
                    currentLocationNumber = loacationToGo;
                    currentLocation = map.get(loacationToGo);
                    return true;
                }
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return false;
        }

        return false;

    }
    public ArrayList<Integer> getListOfPossibleLocations(){

        return map.get(currentLocationNumber).getRoomsToGo();

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
}
