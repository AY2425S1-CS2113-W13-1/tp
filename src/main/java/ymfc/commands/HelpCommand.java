package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents the command to display help information to the user.
 * When executed, the {@code HelpCommand} provides a list of available commands and instructions
 * on how to use them, which is displayed via the {@code Ui}.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a {@code HelpCommand} that provides the user with instructions and help.
     */
    public HelpCommand() {
        super();

        logger.log(Level.FINEST, "Creating HelpCommand");

    }

    /**
     * Executes the {@code HelpCommand}, which displays help information to the user.
     *
     * @param recipes The {@code RecipeList}, not used in this method.
     * @param ui The {@code Ui} used to display the help information to the user.
     * @param storage The {@code Storage}, not used in this method.
     */
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing HelpCommand");

        ui.printHelp();
    }
}
