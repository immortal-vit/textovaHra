package game;

import game.commands.*;
import game.commands.list.Exit;
import game.commands.list.Move;

import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private Scanner scanner;
    private boolean exit = false;
    private HashMap<String, Command> commands;
    private WorldMap worldMap;

    private void initialization(){
        commands = new HashMap<>();
        worldMap = new WorldMap();
        scanner = new Scanner(System.in);
        commands.put("jdi", new Move(scanner, worldMap));
        commands.put("konec", new Exit());

    }


    private void doCommand(){
        System.out.println(">>");
        String command = scanner.next();
        if(commands.containsKey(command)){
            exit = commands.get(command).exit();
            System.out.println(commands.get(command).execute());
        } else {
            System.out.println("neplatny prikaz");
        }
    }

    public void play(){
        initialization();
        do {
            doCommand();

        } while (!exit);
    }
}
