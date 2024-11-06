package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to terminate the application.
 * When executed, the ByeCommand signals that the program should exit by setting the isBye flag,
 * and it displays a farewell message to the user through the Ui.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand.
     * Sets the isBye flag, indicating that the user wants to exit the application.
     */
    public ByeCommand() {
        super();

        logger.log(Level.FINEST, "Creating ByeCommand");
        setBye();
    }

    /**
     * Executes the {@code ByeCommand}, which outputs a farewell message to the user.
     *
     * @param recipes The RecipeList, not used in this method.
     * @param ingredients The IngredientList. Unused in this command.
     * @param ui The Ui to interact with the user. Displays the farewell message.
     * @param storage The Storage, not used in this method.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing ByeCommand");

        ui.bidFarewell();
    }
}
