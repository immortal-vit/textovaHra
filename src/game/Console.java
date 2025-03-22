package game;

import game.commands.*;
import game.commands.list.*;
import game.objects.Inventory;

import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private Scanner scanner;
    private boolean exit = false;
    private HashMap<String, Command> commands;
    private WorldMap worldMap;
    private Inventory inventory;

    private void initialization(){
        commands = new HashMap<>();
        worldMap = new WorldMap();
        inventory = new Inventory();
        scanner = new Scanner(System.in);
        commands.put("jdi", new Move(scanner, worldMap));
        commands.put("prohledat", new Explore(worldMap, inventory));
        commands.put("promluvit", new Talk(worldMap));
        commands.put("obvinit", new Accuse(worldMap, scanner, inventory));
        commands.put("konec", new Exit());
        commands.put("pomoc",new Help(commands));
        commands.put("inventar", new OpenInventory(worldMap, scanner, inventory));


    }


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
    private String writeIntroduction(){
        // tato metoda bude dokoncena pozdeji az si rozmyslim co do ni napsat
        return "uvod do hry + mensi tutorial jak se hra bude hrat + napiste 'pomoc' pro zobrazeni commandu";
    }

    public void play(){
        initialization();
        System.out.println(writeIntroduction());
        do {
            doCommand();

        } while (!exit);
    }
}
