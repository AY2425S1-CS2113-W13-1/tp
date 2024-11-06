package ymfc.recipe;

import java.util.Comparator;

/**
 * Implements the {@code Comparator} class to sort {@code RecommendedRecipes} objects
 * by their percentMatch field in descending order
 */
public class SortByPercentMatch implements Comparator<RecommendedRecipe> {
    public int compare(RecommendedRecipe recipe1, RecommendedRecipe recipe2) {
        // Sort in descending order
        return recipe2.getPercentMatch() - recipe1.getPercentMatch();
    }
}
