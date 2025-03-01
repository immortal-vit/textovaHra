import java.util.Scanner;

public class Move extends Command{

       private final WorldMap world;
       private final Scanner sc;

    public Move(Scanner sc, WorldMap world) {
        this.sc = sc;
        this.world = world;
    }

    @Override
    public String execute() {
        if (world.goToNewLocations(sc)){
            return "presunul si se do mistnosti";
        } else {
            return "tato mistnost neexistuje";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }

}
