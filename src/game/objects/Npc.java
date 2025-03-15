package game.objects;

public class Npc {
    private int roomId;
    private String name;
    private boolean isKidnapper;
    private String dialog;

    public Npc() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public boolean isKidnapper() {
        return isKidnapper;
    }

    public void setKidnapper(boolean kidnapper) {
        isKidnapper = kidnapper;
    }
}
