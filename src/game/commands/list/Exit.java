package game.commands.list;

import game.commands.Command;

/**
 * class for exiting
 */
public class Exit extends Command {

    /**
     *
     * @return player will get ending for closing the case early
     */
    @Override
    public String execute() {
        return "rozhodl si se odejit z vysetrovani, dostal si vyhazov a skoncil si na ulici";
    }

    /**
     *
     * @return end of the game
     */
    @Override
    public boolean exit() {
        return true;
    }
}
