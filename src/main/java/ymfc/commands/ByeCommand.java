package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to terminate the application.
 * When executed, the {@code ByeCommand} signals that the program should exit by setting the {@code isBye} flag,
 * and it displays a farewell message to the user through the {@code Ui}.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand}.
     * Sets the {@code isBye} flag, indicating that the user wants to exit the application.
     */
    public ByeCommand() {
        super();

        logger.log(Level.FINEST, "Creating ByeCommand");
        setBye();
    }

    /**
     * Executes the {@code ByeCommand}, which outputs a farewell message to the user.
     *
     * @param recipes The {@code RecipeList}, not used in this method.
     * @param ui The {@code Ui} to interact with the user. Displays the farewell message.
     * @param storage The {@code Storage}, not used in this method.
     */
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing ByeCommand");

        ui.bidFarewell();
    }
}
