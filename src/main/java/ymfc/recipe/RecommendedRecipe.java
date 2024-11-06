package ymfc.recipe;

import ymfc.ingredient.Ingredient;

import java.util.ArrayList;

/**
 * Represents the parameters associated with a recipe to recommend to the user.
 * Contains the {@code Recipe} itself, the percentage of the recipe's ingredients
 * that matches what the user has, and the missing ingredients the user doesn't have.
 *
 * Works in conjunction with {@code SortByPercentMatch} such that an arraylist of
 * {@code RecommendedRecipe} can be sorted by what is the most accessible to the user
 * (how easily they can make the recipe without having to obtain additional ingredients).
 */
public class RecommendedRecipe{

    private Recipe recipe;
    private int percentMatch;
    private ArrayList<Ingredient> missingIngredients;

    public RecommendedRecipe(Recipe recipe, int percentMatch, ArrayList<Ingredient> missingIngredients) {
        this.recipe = recipe;
        this.percentMatch = percentMatch;
        this.missingIngredients = missingIngredients;
    }

    public int getPercentMatch() {
        return percentMatch;
    }

    @Override
    public String toString() {
        StringBuilder recommendedRecipeDetails = new StringBuilder();
        // Show percentage of the recipe's ingredients that the user has
        recommendedRecipeDetails.append("\tFor this recipe, you have ").append(percentMatch)
                .append("% of the ingredients needed").append(System.lineSeparator());

        // Add list of missing ingredients if there exists missing ingredients
        if (!missingIngredients.isEmpty()) {
            recommendedRecipeDetails.append("\tYou are missing the following:").append(System.lineSeparator());
            for (Ingredient missingIngredient : missingIngredients) {
                recommendedRecipeDetails.append("\t  - ").append(missingIngredient)
                        .append(System.lineSeparator());
            }
        }

        // Show details on the recipe
        recommendedRecipeDetails.append("\t").append(recipe).append(System.lineSeparator());

        return recommendedRecipeDetails.toString();
    }
}
