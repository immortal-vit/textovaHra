package game.commands.list;

import game.WorldMap;
import game.commands.Command;
import game.objects.Inventory;
import game.objects.Npc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
                    return getEnding("src/game/files/goodEnding");
                } else {
                    return getEnding("src/game/files/badEnding");
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
    private String getEnding(String nameOfFile){
        String ending = "";
        String line;
        end = true;
        try(BufferedReader bf = new BufferedReader(new FileReader(nameOfFile))) {
            while ((line = bf.readLine()) != null){
                ending += line + "\n";
            }

        }catch (Exception e){
            System.out.println("neco se pokazilo pri nacitani filu");
        }
        return ending;
    }

}
