package game.commands.list;

import game.commands.Command;

public class Exit extends Command {

    @Override
    public String execute() {
        return "rozhodl si se odejit z vysetrovani a vratit se pozdeji";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
