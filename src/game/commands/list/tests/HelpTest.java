package game.commands.list.tests;

import game.commands.Command;
import game.commands.list.Help;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * this will test if the command help is working properly
 */
class HelpTest {

    private Help helpCommand;
    private HashMap<String, Command> commands;

    /**
     * this will set up everything that the test needs
     */
    @BeforeEach
    void setUp() {
        commands = new HashMap<>();
        commands.put("jdi", new testCommand());
        commands.put("prohledat", new testCommand());
        commands.put("konec", new testCommand());

        helpCommand = new Help(commands);
    }

    /**
     * this will test if the command is returning right text
     */
    @Test
    void testExecute() {
        String output = helpCommand.execute();
        assertNotNull(output);
        assertTrue(output.contains("jdi"));
        assertTrue(output.contains("prohledat"));
        assertTrue(output.contains("konec"));
        assertTrue(output.contains("commandy jsou:"));
    }

    /**
     * this will test if the exit is working properly
     */
    @Test
    void testExit() {
        assertFalse(helpCommand.exit());
    }

    /**
     * chatGPT generated me this
     * this is the static testCommand class for texting command
     * this will override the methods of current command to return different text when the command is done
     */
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
