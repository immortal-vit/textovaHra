package game;

import game.commands.*;
import game.commands.list.*;
import game.objects.Inventory;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
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
     * this will initialize map inventory and scanner
     */
    private void initialization(){
        worldMap = new WorldMap();
        inventory = new Inventory();
        scanner = new Scanner(System.in);

    }

    /**
     * initialize all commands
     */
    private void initializeCommands(){
        commands = new HashMap<>();

        commands.put("jdi", new Move(scanner, worldMap));
        commands.put("prohledat", new Explore(worldMap, inventory));
        commands.put("promluvit", new Talk(worldMap));
        commands.put("obvinit", new Accuse(worldMap, scanner, inventory));
        commands.put("odejit", new Exit());
        commands.put("pomoc",new Help(commands));
        commands.put("inventar", new OpenInventory(worldMap, scanner, inventory));
        commands.put("ulozit",new Save(inventory,worldMap));
        commands.put("nahrat",new Load());
    }


    /**
     * this will execute a command
     */
    private void doCommand(){
        System.out.print(">>");
        String command = scanner.nextLine();
        Command commandObj = commands.get(command);
        if (!commands.containsKey(command)) {
            System.out.println("neplatny prikaz");
            return;
        }

        if (command.equals("nahrat")) {
            System.out.println(commandObj.execute());
            this.worldMap = ((Load) commandObj).worldMap;
            this.inventory = ((Load) commandObj).inventory;
            initializeCommands();
            return;
        }

        System.out.println(commandObj.execute());
        exit = commandObj.exit();

        if (!command.equals("obvinit")) {
            worldMap.moveNpc();
        }
    }

    /**
     * this method will return some small tutorial and some introduction
     * @return small tutorial and introduction
     */
    private String writeIntroduction(){
        String text = "";
        String line;

        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/game/files/introduction"));
            while ((line = bf.readLine()) != null ){
                text += line + "\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    /**
     * will start a 'game'
     * there is do while cycle
     * will quit the game when the player execute a command to quit
     */
    public void play(){
        initialization();
        initializeCommands();
        System.out.println(writeIntroduction());
        do {
            doCommand();
            } while (!exit);



        scanner.close();
    }
}
