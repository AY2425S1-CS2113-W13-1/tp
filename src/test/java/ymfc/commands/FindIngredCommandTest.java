package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.recipe.Recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

class FindIngredCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private Ui ui;
    private FindIngredCommand findIngredCommand;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        recipeList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);

        // Add some sample recipes
        ArrayList<Ingredient> pastaIngredients = new ArrayList<>();
        pastaIngredients.add(new Ingredient("Pasta"));
        pastaIngredients.add(new Ingredient("Water"));
        pastaIngredients.add(new Ingredient("Salt"));
        ArrayList<String> pastaSteps = new ArrayList<>();
        pastaSteps.add("Boil water.");
        pastaSteps.add("Add pasta.");
        pastaSteps.add("Cook for 10 minutes.");

        Recipe pastaRecipe = new Recipe("Pasta", pastaIngredients, pastaSteps, 2);
        recipeList.addRecipe(pastaRecipe);

        // Add to ingredientList
        Ingredient pastaIngredientOne = new Ingredient("Pasta");
        Ingredient pastaIngredientTwo = new Ingredient("Salt");
        Ingredient pastaIngredientThree = new Ingredient("Water");
        Ingredient pastaIngredientFour = new Ingredient("Tomato sauce");
        ingredientList.addIngredient(pastaIngredientOne);
        ingredientList.addIngredient(pastaIngredientTwo);
        ingredientList.addIngredient(pastaIngredientThree);
        ingredientList.addIngredient(pastaIngredientFour);

    }
    @Test
    void isFoundInName_searchInList_returnTrue() {
        // Assertions to verify that recipe and ingredient lists was created
        assertEquals(1, recipeList.getCounter());
        assertEquals(4, ingredientList.getCounter());

        findIngredCommand = new FindIngredCommand("tomato");
        try {
            findIngredCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, findIngredCommand.getNumMatches());
    }

    @Test
    void isFoundInName_searchNotInList_returnFalse() {
        // Assertions to verify that recipe and ingredient lists was created
        assertEquals(1, recipeList.getCounter());
        assertEquals(4, ingredientList.getCounter());

        findIngredCommand = new FindIngredCommand("heavy cream");
        try {
            findIngredCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, findIngredCommand.getNumMatches());
    }
}
