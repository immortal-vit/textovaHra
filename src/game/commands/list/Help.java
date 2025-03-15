package game.commands.list;
import game.commands.Command;
import java.util.HashMap;

public class Help extends Command {
    private HashMap<String, Command> commands;

    public Help(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public String execute() {

        String text = "";

        for (String key : commands.keySet()) {
            text +=  key + "  ";
        }


        return "commandy jsou: " + text + "  ," + "pro pouziti commandu napiste nazev commandu" ;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
