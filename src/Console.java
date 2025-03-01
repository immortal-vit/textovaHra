import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> commands;
    private WorldMap worldMap;

    private void initialization(){
        commands = new HashMap<>();
        commands.put("jdi", new Move());
        commands.put("konec", new Exit());
        worldMap = new WorldMap();
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
