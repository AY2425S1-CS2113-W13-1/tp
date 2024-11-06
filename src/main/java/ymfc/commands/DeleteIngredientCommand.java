package ymfc.commands;

import ymfc.exception.InvalidArgumentException;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

public class DeleteIngredientCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tdeleteI n/onions
            """;

    private String ingredientName;

    public DeleteIngredientCommand(String ingredientName) {
        super();

        logger.log(Level.FINEST, "Creating DeleteIngredientCommmand");
        assert ingredientName != null;
        this.ingredientName = ingredientName;
    }

    public void execute(RecipeList recipes, IngredientList ingredients,
                        Ui ui, Storage storage) throws InvalidArgumentException {
        logger.log(Level.FINEST, "Executing DeleteIngredientCommand");
        assert ingredients != null;

        boolean isRemoved = ingredients.removeIngredientByName(ingredientName);
        if (isRemoved) {
            ui.printDeletedTask(ingredientName, recipes.getCounter());
            try {
                storage.saveIngredients(ingredients);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            throw new InvalidArgumentException("Ingredient not found: " + ingredientName);
        }
    }
}
