package game.commands.list.tests;

import game.WorldMap;
import game.commands.list.Accuse;
import game.objects.Inventory;
import game.objects.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this test class is testing accusing the most important method in the game
 */
class AccuseTest {

    private Accuse accuse;
    private WorldMap worldMap;
    private Inventory inventory;
    private Scanner scanner;
    Item i1;
    Item i2;
    Item i3;
    Item i4;

    /**
     * chatgpt helped me to generate this and I rewrite it
     * this will initialize the world map and inventory both are needed for accuse to work
     */
    @BeforeEach
    void setUp() {
        worldMap = new WorldMap();
        inventory = new Inventory();

        i1 = new Item();
        i2 = new Item();
        i3 = new Item();
        i4 = new Item();
    }

    /**
     * this will test accuse if player does not have enough evidence
     */
    @Test
    void testAccuseWithoutEvidence() {
        scanner = new Scanner("colt");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertEquals("zatim nemate dostatek dukazu a nadrizeni by vam vase obvineni neuverili", result);
        assertFalse(accuse.exit());
    }

    /**
     * this will test accuse method if the player write something wrong and make sure that the program does not throw error
     */
    @Test
    void testAccuseWrongName() {
        addItemsForTest();

        scanner = new Scanner("neexistuje");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertEquals("napsal jsi jmeno spatne, toto jmeno neexistuje", result);
        assertFalse(accuse.exit());
    }

    /**
     * this will test if the program return right text if player accuses wrong person
     */
    @Test
    void testAccuseCorrectlyInnocent() {
        addItemsForTest();

        scanner = new Scanner("piper\nano");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertTrue(result.contains("obvinil jsi nevinneho obcana"));
        assertTrue(accuse.exit());
    }

    /**
     * this will test if the program return right text if player accuses right person
     */
    @Test
    void testAccuseCorrectlyKidnapper() {
        addItemsForTest();

        scanner = new Scanner("colt\nano");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertTrue(result.contains("Spravne jsi obvinil unosce, zachranil medveda Bruce."));
        assertTrue(accuse.exit());
    }

    /**
     * this will test if the player write anything else than yes that he will return to searching the house without any error
     */
    @Test
    void testAccuseNotSure() {
        addItemsForTest();

        scanner = new Scanner("piper\nne");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertEquals("nenapsal jsi ano, takze jsi nejsi jisty a vratil si se k vysetrovani", result);
        assertFalse(accuse.exit());
    }
    private void addItemsForTest(){
        inventory.addItem(i1);
        inventory.addItem(i2);
        inventory.addItem(i3);
        inventory.addItem(i4);
    }
}