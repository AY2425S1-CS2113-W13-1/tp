package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static ymfc.YMFC.logger;

public class FindCommand extends Command {
    public static final String USAGE_EXAMPLE = """
            Use example:
            \t find spaghetti // Default: find by name
            \t find i/tomato
            \t find ns/tomato
            \t find nis/tomato
            
            Note that any combination of "nis" stands for "Name, Ingredients and Steps".
            """;

    private String query;
    private boolean isByName = true;
    private boolean isByIngredient = false;
    private boolean isByStep = false;
    private int numMatches = 0;


    public FindCommand(String query) {
        this.query = query;
    }

    public FindCommand(String query, boolean isByName, boolean isByIngredient, boolean isByStep) {
        this.query = query;
        this.isByName = isByName;
        this.isByIngredient = isByIngredient;
        this.isByStep = isByStep;
    }

    public int getNumMatches() {
        return numMatches;
    }

    @Override
    public void execute(RecipeList recipes, IngredientList ingredientList, Ui ui, Storage storage) throws Exception {
        logger.log(Level.FINEST, "Executing FindCommand");
        ArrayList<Recipe> results = recipes.getRecipes()
                .stream()
                .filter((Recipe recipe) -> isQueryFoundInName(recipe) |
                        isFoundInIngredients(recipe) |
                        isFoundInSteps(recipe)
                )
                .collect(Collectors.toCollection(ArrayList::new));
        numMatches = results.size();
        if (numMatches > 0) {
            ui.printFind(results, numMatches);
        } else {
            ui.printEmptyFind();
        }
        logger.log(Level.FINEST, "FindCommand successfully executed");
    }

    private boolean isFoundInSteps(Recipe recipe) {
        return isByStep &
                recipe.getSteps().stream().anyMatch(s -> s.contains(query));
    }

    private boolean isFoundInIngredients(Recipe recipe) {
        return isByIngredient &
                recipe.getIngredients().stream()
                        .anyMatch(ingredient -> ingredient.getName().contains(query));
    }

    private boolean isQueryFoundInName(Recipe recipe) {
        return isByName & recipe.getName().contains(query);
    }
}
