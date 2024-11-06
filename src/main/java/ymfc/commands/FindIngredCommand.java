package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static ymfc.YMFC.logger;

public class FindIngredCommand extends Command {
    public static final String USAGE_EXAMPLE = """
            Use example:
            \t findI spaghetti // Default: find by name
            
            """;

    private String query;
    private int numMatches = 0;

    public FindIngredCommand(String query) {
        this.query = query;
    }

    public int getNumMatches() {
        return numMatches;
    }

    public void execute(RecipeList recipes, IngredientList ingredientList, Ui ui, Storage storage) throws Exception {
        logger.log(Level.FINEST, "Executing FindIngredCommand");
        ArrayList<Ingredient> results = ingredientList.getIngredients()
                .stream()
                .filter((Ingredient ingredient) -> isQueryFoundInName(ingredient)
                )
                .collect(Collectors.toCollection(ArrayList::new));
        numMatches = results.size();
        if (numMatches > 0) {
            ui.printFindIngred(results, numMatches);
        } else {
            ui.printEmptyFindIngred();
        }
        logger.log(Level.FINEST, "FindIngredCommand successfully executed");
    }

    private boolean isQueryFoundInName(Ingredient ingredient) {
        return ingredient.getName().toLowerCase().contains(query.toLowerCase());
    }
}
