package game.commands.list.tests;

import game.commands.Command;
import game.commands.list.Help;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelpTest {

    private Help helpCommand;
    private HashMap<String, Command> commands;

    @BeforeEach
    void setUp() {
        commands = new HashMap<>();
        commands.put("jdi", new testCommand());
        commands.put("prohledat", new testCommand());
        commands.put("konec", new testCommand());

        helpCommand = new Help(commands);
    }

    @Test
    void testExecute() {
        String output = helpCommand.execute();
        assertNotNull(output);
        assertTrue(output.contains("jdi"));
        assertTrue(output.contains("prohledat"));
        assertTrue(output.contains("konec"));
        assertTrue(output.contains("commandy jsou:"));
    }

    @Test
    void testExit() {
        assertFalse(helpCommand.exit());
    }

    static class testCommand extends Command {
        @Override
        public String execute() {
            return "Test command executed";
        }

        @Override
        public boolean exit() {
            return false;
        }
    }
}
