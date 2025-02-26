import java.util.ArrayList;

public class Location {

    private int mark;
    private String name;
    private ArrayList<Integer> roomsToGo;

    public Location(int mark, String name, ArrayList<Integer> roomsToGo) {
        this.mark = mark;
        this.name = name;
        this.roomsToGo = new ArrayList<>();
    }
}
