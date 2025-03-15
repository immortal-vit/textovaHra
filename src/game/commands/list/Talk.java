package game.commands.list;

import game.WorldMap;
import game.commands.Command;

public class Talk extends Command {
    private WorldMap worldMap;

    public Talk(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    @Override
    public String execute() {
        if (worldMap.getCurrentLocation().getNpc() == null){
            return "v teto mistnosti e nenachazi postava";
        } else {
            return worldMap.getCurrentLocation().getNpc().getName() + " rika: " + worldMap.getCurrentLocation().getNpc().getDialog();
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
