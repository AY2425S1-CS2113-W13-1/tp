package ymfc.commands;

import ymfc.exception.InvalidArgumentException;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private Ui ui;
    private DeleteCommand deleteCommand;
    private IngredientList ingredientList;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Add some sample recipes
        ArrayList<Ingredient> pastaIngredients = new ArrayList<>();
        pastaIngredients.add(new Ingredient("Pasta"));
        pastaIngredients.add(new Ingredient("Water"));
        pastaIngredients.add(new Ingredient("Salt"));

        ArrayList<String> pastaSteps = new ArrayList<>();
        pastaSteps.add("Boil water.");
        pastaSteps.add("Add pasta.");
        pastaSteps.add("Cook for 10 minutes.");

        Recipe pastaRecipe = new Recipe("Pasta", pastaIngredients, pastaSteps);

        ArrayList<Ingredient> saladIngredients = new ArrayList<>();
        saladIngredients.add(new Ingredient("Lettuce"));
        saladIngredients.add(new Ingredient("Tomatoes"));
        saladIngredients.add(new Ingredient("Cucumber"));

        ArrayList<String> saladSteps = new ArrayList<>();
        saladSteps.add("Chop veggies.");
        saladSteps.add("Add pasta.");
        saladSteps.add("Toss with dressing.");

        Recipe saladRecipe = new Recipe("Salad", saladIngredients, saladSteps);

        recipeList.addRecipe(pastaRecipe);
        recipeList.addRecipe(saladRecipe);

        deleteCommand = new DeleteCommand("Pasta");
    }

    @Test
    void testDeleteRecipe() throws InvalidArgumentException {
        // Assertions to verify that list was created as expected
        assertEquals(2, recipeList.getCounter());

        // Execute the DeleteCommand
        deleteCommand.execute(recipeList, ingredientList, ui, storage);

        // Assertions to verify the recipe was removed
        assertEquals(1, recipeList.getCounter());  // Ensure that a recipe was deleted
        assertEquals("Salad", recipeList.getRecipe(0).getName());  // Verify that the correct recipe remains
    }
}
