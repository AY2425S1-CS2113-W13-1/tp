package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents the command to list all the recipes in the {@code RecipeList}.
 * When executed, the {@code ListCommand} retrieves and displays the list of recipes
 * to the user via the {@code Ui}.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} to display the current list of recipes.
     */
    public ListCommand() {
        super();

        logger.log(Level.FINEST, "Creating ListCommand");
    }

    /**
     * Executes the {@code ListCommand}, which prints the entire list of recipes
     * along with the number of recipes in the list using the {@code Ui}.
     *
     * @param recipes The {@code RecipeList} containing all the recipes.
     * @param ui The {@code Ui} used to display the list of recipes.
     * @param storage The {@code Storage}, not used in this method.
     */
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing HelpCommand");

        assert recipes.getCounter() >= 0;
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
