package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

/**
 * Represents a command to add a new ingredient to the IngredientList.
 * This command takes an Ingredient object and adds it to the ingredient list.
 * It also handles saving the updated ingredient list to storage and notifying the user.
 */
public class AddIngredientCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tnew n/carrot
            """;

    private Ingredient ingredient;

    /**
     * Constructs an AddIngredientCommand with the specified ingredient.
     *
     * @param ingredient The Ingredient to be added. Must not be null.
     */
    public AddIngredientCommand(Ingredient ingredient) {
        super();

        logger.log(Level.FINEST, "Creating AddIngredientCommand");
        this.ingredient = ingredient;
        assert ingredient != null;
    }

    /**
     * Executes the command by adding the ingredient to the IngredientList, saving the list using
     * Storage, and notifying the user via the Ui.
     *
     * @param recipes The RecipeList. Unused in this command.
     * @param ingredients The IngredientList to add the ingredient to. Must not be null.
     * @param ui The  Ui for user interaction, used to notify the user about the added ingredient.
     * @param storage The  Storage for saving the updated IngredientList to persistent storage.
     * @throws IOException If an error occurs while saving the ingredient list.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) throws IOException {
        logger.log(Level.FINEST, "Executing AddIngredientCommand");

        assert ingredients != null;
        addNewIngredient(ingredients, ingredient, ui, storage);
    }

    /**
     * Adds the new ingredient to the ingredient list, saves the updated list to storage, and informs the user.
     *
     * @param ingredients The IngredientList to which the ingredient will be added.
     * @param newIngredient The Ingredient to add to the list.
     * @param ui The Ui used to print the result of adding the ingredient.
     * @param storage The Storage for saving the updated ingredient list.
     * @throws IOException If an error occurs during saving the ingredient list.
     */
    public void addNewIngredient(IngredientList ingredients, Ingredient newIngredient,
                                 Ui ui, Storage storage) throws IOException {
        ingredients.addIngredient(newIngredient);

        storage.saveIngredients(ingredients);

        ui.printAddedIngredient(newIngredient.toString(), ingredients.getCounter());
    }

    /**
     * Adds an ingredient (from save file) to the ingredient list.
     * This method is used when ingredients are being loaded from storage.
     *
     * @param ingredients The IngredientList to which the ingredient will be added.
     */
    public void addLoadedIngredient(IngredientList ingredients) {
        ingredients.addIngredient(ingredient);
    }
}
