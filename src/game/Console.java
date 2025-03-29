package game;

import game.commands.*;
import game.commands.list.*;
import game.objects.Inventory;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This is class for managing commands and what text will be displayed
 */

public class Console {

    private Scanner scanner;
    private boolean exit = false;
    private HashMap<String, Command> commands;
    private WorldMap worldMap;
    private Inventory inventory;

    /**
     * this will initialize every component
     */
    private void initialization(){
        commands = new HashMap<>();
        worldMap = new WorldMap();
        inventory = new Inventory();
        scanner = new Scanner(System.in);
        commands.put("jdi", new Move(scanner, worldMap));
        commands.put("prohledat", new Explore(worldMap, inventory));
        commands.put("promluvit", new Talk(worldMap));
        commands.put("obvinit", new Accuse(worldMap, scanner, inventory));
        commands.put("odejit", new Exit());
        commands.put("pomoc",new Help(commands));
        commands.put("inventar", new OpenInventory(worldMap, scanner, inventory));


    }


    /**
     * this will execute a command
     */
    private void doCommand(){
        System.out.print(">>");
        String command = scanner.next();
        if(commands.containsKey(command)){
            System.out.println(commands.get(command).execute());
            exit = commands.get(command).exit();
        } else {
            System.out.println("neplatny prikaz");
        }
    }

    /**
     * this method will return some small tutorial and some introduction
     * @return small tutorial and introduction
     */
    private String writeIntroduction(){
        // tato metoda bude dokoncena pozdeji az si rozmyslim co do ni napsat
        return "uvod do hry + mensi tutorial jak se hra bude hrat + napiste 'pomoc' pro zobrazeni commandu";
    }

    /**
     * will start a 'game'
     * there is do while cycle
     * will quit the game when the player execute a command to quit
     */
    public void play(){
        initialization();
        System.out.println(writeIntroduction());
        do {
            doCommand();

        } while (!exit);
    }
}
