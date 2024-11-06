package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents the command to display help information to the user.
 * When executed, the HelpCommand provides a list of available commands and instructions
 * on how to use them, which is displayed via the Ui.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a HelpCommand that provides the user with instructions and help.
     */
    public HelpCommand() {
        super();

        logger.log(Level.FINEST, "Creating HelpCommand");

    }

    /**
     * Executes the HelpCommand, which displays help information to the user.
     *
     * @param recipes The RecipeList, not used in this method.
     * @param ingredients The IngredientList. Unused in this command.
     * @param ui The Ui used to display the help information to the user.
     * @param storage The Storage, not used in this method.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing HelpCommand");

        ui.printHelp();
    }
}
