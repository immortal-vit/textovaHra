package game.objects;

import java.io.*;

/**
 * class for Npc
 */
public class Npc implements Serializable {
    private int roomId;
    private String name;
    private boolean isKidnapper;
    private String keyToTextFile;
    private boolean isMovable;



    public Npc() {
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    public boolean isMovable() {
        return isMovable;
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

    public String getKeyToTextFile() {
        return keyToTextFile;
    }

    public void setKeyToTextFile(String keyToTextFile) {
        this.keyToTextFile = keyToTextFile;
    }

    public boolean isKidnapper() {
        return isKidnapper;
    }

    public void setKidnapper(boolean kidnapper) {
        isKidnapper = kidnapper;
    }

    /**
     *
     * @return dialogue from selected npc
     */
    public String readDialogue(){
        File file = new File(getKeyToTextFile());
        String text = "";
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(getKeyToTextFile()));

            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }


        } catch (Exception e) {
            System.out.println("nepodaril se nacist dialog");
        }

        return text;
    }
}
