package game.commands.list;

import game.*;
import game.commands.Command;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Move extends Command {

       private final WorldMap world;
       private final Scanner sc;

    public Move(Scanner sc, WorldMap world) {
        this.sc = sc;
        this.world = world;
    }

    @Override
    public String execute() {

        String listOfLocations = "";
        ArrayList<Integer> arrayOfLocations =  world.getListOfPossibleLocations();

        for (int i = 0; i < arrayOfLocations.size(); i++) {
            listOfLocations += arrayOfLocations.get(i) + " " + world.getMap().get(arrayOfLocations.get(i)).getName() + ", " ;
        }

        System.out.println(listOfLocations);
        System.out.println("napiste cislo lokace do ktere chcete jit");

        try {
            if (world.goToNewLocations(sc.nextInt())){
                return "presunul si se do: " + world.getCurrentLocation().getName();
            } else {
                return "tato mistnost neexistuje nebo neni v nabidce";
            }
        }catch (InputMismatchException e){
            sc.nextLine();
            return "tato mistnost neexistuje nebo neni v nabidce";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

}
