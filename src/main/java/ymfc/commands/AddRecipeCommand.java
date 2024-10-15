package ymfc.commands;

import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents a command to add a new recipe to the {@code RecipeList}.
 * This command takes a {@code Recipe} object and adds it to the provided recipe list.
 * The command also handles saving the updated recipe list to storage and notifying the user through the UI.
 */
public class AddRecipeCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tadd n/instant noodles i/magi mee i/water s1/boil water s2/add magi mee s3/wait 5 min
            """;

    private Recipe recipe;

    /**
     * Constructs an {@code AddRecipeCommand} with the specified recipe.
     *
     * @param recipe The {@code Recipe} to be added. Must not be {@code null}.
     */
    public AddRecipeCommand(Recipe recipe) {
        super();

        logger.log(Level.FINEST, "Creating AddRecipeCommand");
        this.recipe = recipe;
        assert recipe != null;
    }

    /**
     * Executes the command by adding the recipe to the {@code RecipeList}, saving the list using {@code Storage},
     * and notifying the user via the {@code Ui}.
     *
     * @param recipes The {@code RecipeList} to add the recipe to. Must not be {@code null}.
     * @param ui The {@code Ui} for user interaction. Used to notify the user about the added recipe.
     * @param storage The {@code Storage} for saving the updated {@code RecipeList} to persistent storage.
     * @throws IOException If an error occurs while saving the recipe list.
     */
    public void execute(RecipeList recipes, Ui ui, Storage storage) throws IOException {
        logger.log(Level.FINEST, "Executing AddRecipeCommand");

        assert recipes != null;
        addNewRecipe(recipes, recipe, ui, storage);
    }

    /**
     * Adds the new recipe to the recipe list, saves the updated list to storage, and informs the user.
     *
     * @param recipes The {@code RecipeList} to which the recipe will be added.
     * @param newRecipe The recipe to add to the list.
     * @param ui The {@code Ui} used to print the result of adding the recipe.
     * @param storage The {@code Storage} for saving the updated recipe list.
     * @throws IOException If an error occurs during saving the recipe list.
     */
    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui, Storage storage) throws IOException {
        recipes.addRecipe(newRecipe);

        storage.saveRecipes(recipes);

        ui.printAddedRecipe(newRecipe.toString(), recipes.getCounter());
    }

    /**
     * Adds a loaded recipe (from save file) to the recipe list.
     * This method is used when recipes are being loaded from storage.
     *
     * @param recipes The {@code RecipeList} to which the loaded recipe will be added.
     */
    public void addLoadedRecipe(RecipeList recipes) {
        recipes.addRecipe(recipe);
    }
}
