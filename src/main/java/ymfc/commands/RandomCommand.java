package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.Random;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to recommend the user a recipe from {@code RecipeList}.
 * A random recipe is chosen from {@code RecipeList} and displayed
 * to the user via {@code Ui}.
 */
public class RandomCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \trandom
            """;

    public RandomCommand() {
        super();

        logger.log(Level.FINEST, "Creating RandomCommand");
    }

    /**
     * Executes the {@code RandomCommand}, choosing a random recipe from {@code RecipeList}
     * and displaying that to the user.
     *
     * @param recipes The {@code RecipeList} to choose the recipe from. Must not be {@code null}.
     * @param ingredients The {@code IngredientList}. Unused in this command.
     * @param ui The {@code Ui} to inform the user of the randomly chosen recipe.
     * @param storage The {@code Storage}. Unused in this command
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing RandomCommand");
        assert recipes.getCounter() > 0;

        // Get ID of a random recipe within recipeList
        Random rand = new Random();
        int chosenID = rand.nextInt(recipes.getCounter());

        // Get recipe with the random ID and display it
        Recipe chosenRecipe = recipes.getRecipe(chosenID);
        ui.printRandomRecipe(chosenRecipe);
    }
}
