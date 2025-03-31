package game.commands.list.tests;

import game.WorldMap;
import game.commands.list.Accuse;
import game.objects.Inventory;
import game.objects.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AccuseTest {

    private Accuse accuse;
    private WorldMap worldMap;
    private Inventory inventory;
    private Scanner scanner;
    Item i1;
    Item i2;
    Item i3;
    Item i4;

    @BeforeEach
    void setUp() {
        worldMap = new WorldMap();
        inventory = new Inventory();

        i1 = new Item();
        i2 = new Item();
        i3 = new Item();
        i4 = new Item();
    }

    @Test
    void testAccuseWithoutEvidence() {
        scanner = new Scanner("colt");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertEquals("zatim nemate dostatek dukazu a nadrizeni by vam vase obvineni neuverili", result);
        assertFalse(accuse.exit());
    }

    @Test
    void testAccuseWrongName() {
        addItemsForTest();

        scanner = new Scanner("neexistuje");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertEquals("napsal jsi jmeno spatne, toto jmeno neexistuje", result);
        assertFalse(accuse.exit());
    }

    @Test
    void testAccuseCorrectlyInnocent() {
        addItemsForTest();

        scanner = new Scanner("piper\nano");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertTrue(result.contains("obvinil jsi nevinneho obcana"));
        assertTrue(accuse.exit());
    }

    @Test
    void testAccuseCorrectlyKidnapper() {
        addItemsForTest();

        scanner = new Scanner("colt\nano");
        accuse = new Accuse(worldMap, scanner, inventory);

        String result = accuse.execute();
        assertTrue(result.contains("spravne jsi obvinil unosce"));
        assertTrue(accuse.exit());
    }

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