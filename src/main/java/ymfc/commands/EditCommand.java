package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.exception.InvalidArgumentException;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to edit a recipe from the {@code RecipeList} by its name.
 * When executed, the {@code EditCommand} edits the recipe with the specified name
 * from the list, updates the storage, and informs the user of what was edited via the {@code Ui}.
 */
public class EditCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tedit e/instant noodles i/magi mee i/water s1/boil water s2/eat magi mee s3/drink water
            """;

    private Recipe recipe;

    /**
     * Constructs a {@code EditCommand} with the specified recipe name.
     *
     * @param recipe The edit information of the recipe. Must not be {@code null}.
     */
    public EditCommand(Recipe recipe) {
        super();

        logger.log(Level.FINEST, "Creating EditCommand");
        assert recipe != null;
        this.recipe = recipe;
    }

    /**
     * Executes the {@code EditCommand}, finding a recipe matching the name inputted by the user
     * and then editing it based on the new parameters inputted. If the recipe of the specified name
     * is found and then editted, the {@code Storage} is updated and a confirmation message is displayed.
     *
     * @param recipes The {@code RecipeList} to edit the recipe from. Must not be {@code null}.
     * @param ingredients The {@code IngredientList}. Unused in this command.
     * @param ui The {@code Ui} to inform the user of the edited recipe or error.
     * @param storage The {@code Storage} to save the updated recipe list.
     * @throws InvalidArgumentException If the recipe with the specified name is not found in the list.
     */
    public void execute(RecipeList recipes, IngredientList ingredients,
                        Ui ui, Storage storage) throws InvalidArgumentException {
        logger.log(Level.FINEST, "Executing EditCommand");
        assert recipes != null;

        String recipeName = recipe.getName();
        boolean isEdited = recipes.editRecipe(recipeName, recipe);
        if (!isEdited) {
            throw new InvalidArgumentException("You want me to edit a non-existent recipe? How about no.");
        }
        try {
            storage.saveRecipes(recipes);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        ui.printEditedRecipe(recipeName, recipe);
    }
}
