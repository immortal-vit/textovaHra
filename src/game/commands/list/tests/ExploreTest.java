package game.commands.list.tests;

import game.WorldMap;
import game.commands.list.Explore;
import game.objects.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class is testing exploring
 */
class ExploreTest {

    private WorldMap worldMap;
    private Inventory inventory;
    private Explore explore;

    /**
     * this will set up all things that we need for testing
     */
    @BeforeEach
    void setUp() {
        worldMap = new WorldMap();
        inventory = new Inventory();
        explore = new Explore(worldMap, inventory);
    }

    /**
     * this will test if the room does not contain any item
     */
    @Test
    void testWithoutItem() {
        worldMap.goToNewLocations(1);

        String result = explore.execute();
        assertEquals("nic si nenasel", result);
        assertFalse(explore.exit());
    }

    /**
     * this will test if the room does contain an item
     */
    @Test
    void testWithItem() {
        worldMap.goToNewLocations(0);

        String result = explore.execute();
        assertTrue(result.contains("predmet sis ulozil do inventare"), result);
        assertFalse(explore.exit());
    }
}