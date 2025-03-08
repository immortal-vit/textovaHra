package game.objects;

public class Npc {
    private int id;
    private String name;
    private String dialog;
    private boolean isKidnapper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
