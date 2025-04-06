package game.commands.list;

import game.WorldMap;
import game.commands.Command;

/**
 * class for talking with a npc
 */
public class Talk extends Command {
    private WorldMap worldMap;

    public Talk(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    /**
     * will chgeck if the room has a npc if yes it will return dialogue if not it will return text that there is no npc
     * @return npc dialogue
     */
    @Override
    public String execute() {
        if (worldMap.getCurrentLocation().getNpc() == null){
            return "v teto mistnosti se nenachazi postava";
        } else {
            return worldMap.getCurrentLocation().getNpc().getName() + " rika: \n" + worldMap.getCurrentLocation().getNpc().readDialogue();
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
