package game.commands.list.tests;

import game.WorldMap;
import game.commands.list.Explore;
import game.objects.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExploreTest {

    private WorldMap worldMap;
    private Inventory inventory;
    private Explore explore;

    @BeforeEach
    void setUp() {
        worldMap = new WorldMap();
        inventory = new Inventory();
        explore = new Explore(worldMap, inventory);
    }

    @Test
    void testWithoutItem() {
        worldMap.goToNewLocations(1);

        String result = explore.execute();
        assertEquals("nic si nenasel", result);
        assertFalse(explore.exit());
    }

    @Test
    void testWithItem() {
        worldMap.goToNewLocations(0);

        String result = explore.execute();
        assertTrue(result.contains("predmet sis ulozil do inventare"), result);
        assertFalse(explore.exit());
    }
}