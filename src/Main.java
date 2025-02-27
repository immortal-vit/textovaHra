public class Main {
    public static void main(String[] args) {

        WorldMap map = new WorldMap();
        map.loadMap();
        System.out.println(map.toString());
    }
}