package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents the command to list all the recipes in the RecipeList.
 * When executed, the ListCommand retrieves and displays the list of recipes
 * to the user via the Ui.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand to display the current list of recipes.
     */
    public ListCommand() {
        super();

        logger.log(Level.FINEST, "Creating ListCommand");
    }

    /**
     * Executes the ListCommand, which prints the entire list of recipes
     * along with the number of recipes in the list using the Ui.
     *
     * @param recipes The RecipeList containing all the recipes.
     * @param ingredients The IngredientList. Unused in this command.
     * @param ui The Ui used to display the list of recipes.
     * @param storage The Storage, not used in this method.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing ListCommand");

        assert recipes.getCounter() >= 0;
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
