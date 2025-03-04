import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WorldMap {

    private HashMap<Integer, Location> map = new HashMap<>();
    private int currentLocation;

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

            for (Location l : map.values()) {
                if (l.getRoomsToGo().contains(loacationToGo)) {
                    currentLocation = loacationToGo;
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

        return map.get(currentLocation).getRoomsToGo();

    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public HashMap<Integer, Location> getMap() {
        return map;
    }
}
