package game.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Npc {
    private int roomId;
    private String name;
    private boolean isKidnapper;
    private String keyToTextFile;
    private String text;

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
            throw new RuntimeException(e);
        }

        return text;
    }
}
