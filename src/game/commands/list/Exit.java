package game.commands.list;

import game.commands.Command;

public class Exit extends Command {

    @Override
    public String execute() {
        return "program se ukoncil";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
