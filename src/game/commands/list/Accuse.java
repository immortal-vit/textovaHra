package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;
import game.objects.Npc;

import java.util.Scanner;

/**
 * class for accusing the characters
 *this class is crucial for game ending
 */
public class Accuse extends Command {

    private WorldMap worldMap;
    private Scanner scanner;
    private Inventory inventory;
    private boolean end;

    public Accuse(WorldMap worldMap, Scanner scanner, Inventory inventory) {
        this.worldMap = worldMap;
        this.scanner = scanner;
        this.inventory = inventory;
    }

    /**
     * the player will write name of the character
     * if he writes it wrong the game will continue normally
     * if he writes it right the game will ask him if he is sure
     * if the player is sure he will get the ending based of if the character is kidnapper or not
     * @return ending or the player will continue in the game
     */
    @Override
    public String execute() {
        if (inventory.getItems().size() < 3){
            return "zatim nemate dostatek dukazu a nadrizeni by vam vase obvineni neuverili";
        }
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
                    return "spravne jsi obvinil unosce, zachranil medveda Bruce.\n" +
                            "vyhral jsi hru :)";
                } else {
                    end = true;
                    return "obvinil jsi nevinneho obcana, po par tydnech se prokazala jeho nevinnost.\n" +
                            "Kdyz te odvadeli na popravu, videl jsi sveho partaka colta jak ma na sobe medvedi kozich.\n" +
                            "V tu chvili ti vsechno doslo, ale uz je pozde, protoze v tu chvili uz na tebe skace popravci ceta edgaru.\n" +
                            "Zemrel jsi a prohral jsi hru :(";
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

    /**
     * @return boolean if the game ended or no
     */
    @Override
    public boolean exit() {
        return end;
    }

    /**
     * @return list of characters
     */
    private String showListOfCharacters() {
        String list = "";
        for (Npc npc: worldMap.getNpcs()) {
            list += npc.getName() + ", ";
        }
        return list;
    }

    /**
     *
     * @param name of the npc the player wants to choose
     * @return npc for accusing
     */
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

    /**
     * if the player is sure the game will end otherwise the game will continue
     * @param answer yes or somethng else answer
     * @return ending
     */
    private boolean isPlayerSure(String answer){
        if (answer.equalsIgnoreCase("ano")){
            return true;
        } else {
            return false;
        }
    }

}
