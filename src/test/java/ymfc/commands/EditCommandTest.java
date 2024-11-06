//@@author 3CCLY
package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {

    private Storage storage;
    private RecipeList emptyList;
    private IngredientList ingredientList;
    private Ui ui;
    private Recipe recipe;
    private Recipe edittedRecipe;
    private AddRecipeCommand addRecipeCommand;
    private EditCommand editCommand;

    @BeforeEach
    void setUp() {
        emptyList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Sample ingredients and steps
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("magi mee"));
        ingredients.add(new Ingredient("water"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("boil water");
        steps.add("add magi mee");
        steps.add("wait 5 min");

        recipe = new Recipe("instant noodles", ingredients, steps);
        addRecipeCommand = new AddRecipeCommand(recipe);

        // Sample ingredients and steps
        ArrayList<String> newSteps = new ArrayList<>();
        newSteps.add("boil water");
        newSteps.add("eat magi mee");
        newSteps.add("drink water");

        edittedRecipe = new Recipe("instant noodles", ingredients, newSteps);
        editCommand = new EditCommand(edittedRecipe);
    }

    @Test
    void testEditRecipe() throws IOException, InvalidArgumentException {
        addRecipeCommand.execute(emptyList, ingredientList, ui, storage);

        assertEquals(1, emptyList.getCounter());
        assertEquals(recipe, emptyList.getRecipe(0));

        editCommand.execute(emptyList, ingredientList, ui, storage);
        assertEquals(1, emptyList.getCounter());
        assertEquals(edittedRecipe, emptyList.getRecipe(0));
    }
}
