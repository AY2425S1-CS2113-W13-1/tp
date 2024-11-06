package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.recipe.RecommendedRecipe;
import ymfc.recipe.SortByPercentMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to recommend the user a recipe from {@code RecipeList}.
 * Recipes with ingredients that matches what the user has in {@code IngredientList}
 * are recommended to the user via {@code Ui}.
 */
public class RecommendCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \trecommend
            """;

    public RecommendCommand() {
        super();

        logger.log(Level.FINEST, "Creating RecommendCommand");
    }

    /**
     * Executes the {@code RecommendCommand}.
     * Recipes from the {@code RecipeList} that has ingredients found in {@code IngredientList} are collated.
     * Data about the percentage match of the ingredients and missing ingredients are also ascertained.
     * These are then displayed to the user via {@code Ui}.
     *
     * @param recipes The {@code RecipeList} to choose the recipes from. Must not be {@code null}.
     * @param ingredients The {@code IngredientList} to ascertain user available ingredients.
     * @param ui The {@code Ui} to inform the user of the recommended recipes.
     * @param storage The {@code Storage}. Unused in this command.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing RecommendCommand");
        assert recipes.getCounter() > 0;


        ArrayList<Ingredient> ingredientsList = ingredients.getIngredients();
        ArrayList<RecommendedRecipe> recommendedList = new ArrayList<>();

        // Iterate through all recipes and find recipes with matching ingredients
        for (int i = 0; i < recipes.getCounter(); i++) {
            Recipe targetRecipe = recipes.getRecipe(i);

            // Find ingredients of recipe that matches ingredient list
            // Clone is used so that a new arraylist is created
            // and the retainAll method doesn't overwrite the original recipe
            ArrayList<Ingredient> matchIngredients = (ArrayList<Ingredient>) targetRecipe.getIngredients().clone();
            int recipeIngredientsCount = matchIngredients.size();
            matchIngredients.retainAll(ingredientsList);

            // Add recipe to list of recommended recipes if matching ingredients found
            if (!matchIngredients.isEmpty()) {
                // Calculate percentage of recipe's ingredient available to the user
                int percentMatch = 100 * matchIngredients.size() / recipeIngredientsCount;

                // Find list of missing ingredients
                ArrayList<Ingredient> missingIngredients = (ArrayList<Ingredient>) targetRecipe.
                        getIngredients().clone();
                missingIngredients.removeAll(matchIngredients);

                recommendedList.add(new RecommendedRecipe(targetRecipe, percentMatch, missingIngredients));
            }
        }

        // Sort the recommendedList by each recipe's percentMatch value in descending order
        Collections.sort(recommendedList, new SortByPercentMatch());

        ui.printRecommendedRecipes(recommendedList);
    }
}
