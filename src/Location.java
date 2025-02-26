import java.util.ArrayList;

public class Location {

    private int mark;
    private String name;
    private ArrayList<Integer> roomsToGo;

    public Location(int mark, String name) {
        this.mark = mark;
        this.name = name;
    }


    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getRoomsToGo() {
        return roomsToGo;
    }

    public void setRoomsToGo(ArrayList<Integer> roomsToGo) {
        this.roomsToGo = roomsToGo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "mark=" + mark +
                ", name='" + name + '\'' +
                ", roomsToGo=" + roomsToGo +
                '}';
    }
}
