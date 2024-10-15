package ymfc.commands;

import ymfc.exception.InvalidArgumentException;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to delete a recipe from the {@code RecipeList} by its name.
 * When executed, the {@code DeleteCommand} removes the recipe with the specified name
 * from the list, updates the storage, and informs the user of the deletion via the {@code Ui}.
 */
public class DeleteCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tdelete n/pasta
            """;

    private String recipeName;

    /**
     * Constructs a {@code DeleteCommand} with the specified recipe name.
     *
     * @param recipeName The name of the recipe to be deleted. Must not be {@code null}.
     */
    public DeleteCommand(String recipeName) {
        super();

        logger.log(Level.FINEST, "Creating DeleteCommand");
        assert recipeName != null;
        this.recipeName = recipeName;
    }

    /**
     * Executes the {@code DeleteCommand}, removing the recipe with the specified name
     * from the {@code RecipeList}. If the recipe is found and removed, the updated list is saved to storage
     * and a confirmation message is displayed.
     *
     * @param recipes The {@code RecipeList} to remove the recipe from. Must not be {@code null}.
     * @param ui The {@code Ui} to inform the user of the deletion or error.
     * @param storage The {@code Storage} to save the updated recipe list.
     * @throws InvalidArgumentException If the recipe with the specified name is not found in the list.
     */
    public void execute(RecipeList recipes, Ui ui, Storage storage) throws InvalidArgumentException {
        logger.log(Level.FINEST, "Executing DeleteCommand");
        assert recipes != null;

        boolean isRemoved = recipes.removeRecipeByName(recipeName);
        if (isRemoved) {
            ui.printDeletedTask(recipeName, recipes.getCounter());
            try {
                storage.saveRecipes(recipes);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new InvalidArgumentException("Recipe not found: " + recipeName);
        }
    }
}
