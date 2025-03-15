package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Npc;

import java.util.Scanner;

public class Accuse extends Command {

    private WorldMap worldMap;
    private Scanner scanner;
    private boolean end;

    public Accuse(WorldMap worldMap, Scanner scanner) {
        this.worldMap = worldMap;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        System.out.println(showListOfCharacters());
        System.out.println("napiste nazev postavy, kterou chcete obvinit");
        try {
            Npc npcToAccuse = chooseNpc(scanner.next());
            if (npcToAccuse == null) {
                end = false;
                return "napsal jsi jmeno spatne, toto jmeno neexistuje";
            }
            System.out.println("napiste 'ano' pokud jste si jisty a chcete ukoncit hru");
            if (isPlayerSure(scanner.next())) {
                if (npcToAccuse.isKidnapper()){
                    end = true;
                    return "spravne jsi obvinil unosce a vyhral jsi hru :)";
                } else {
                    end = true;
                    return "obvinil jsi nevinneho obcana a prohral jsi hru :(";
                }
            } else {
                end = false;
                return "nenapsal jsi ano, takze jsi nejsi jisty a vratil si se k vysetrovani";
            }
        } catch (Exception e) {
            end = false;
            scanner.nextLine();
            return "neco jsi napsal spatne zkus command zadat znovu a zadej vse spravne";
        }
    }

    @Override
    public boolean exit() {
        return end;
    }

    private String showListOfCharacters() {
        String list = "";
        for (Npc npc: worldMap.getNpcs()) {
            list += npc.getName() + ", ";
        }
        return list;
    }
    private Npc chooseNpc(String name) {
        Npc chosenNpc = null;
        for (Npc npc: worldMap.getNpcs()){
            if (npc.getName().equalsIgnoreCase(name)){
                chosenNpc = npc;
                return chosenNpc;
            }
        }
        return chosenNpc;
    }
    private boolean isPlayerSure(String answer){
        if (answer.equalsIgnoreCase("ano")){
            return true;
        } else {
            return false;
        }
    }

}
