package game.commands.list;

import game.commands.Command;

public class Help extends Command {
    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
