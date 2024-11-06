package ymfc.recipe;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RecipeTest {

    @Test
    public void getName_validName_returnCorrectName() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        assertEquals("Pasta", recipe.getName());
    }

    @Test
    void setName_validName_nameUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        recipe.setName("Pizza");
        assertEquals("Pizza", recipe.getName());
    }

    @Test
    void getIngredients_validIngredients_returnCorrectIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomato"));
        ingredients.add(new Ingredient("Cheese"));
        Recipe recipe = new Recipe("Pasta", ingredients, new ArrayList<>());
        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    void setIngredients_validIngredients_ingredientsUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        ArrayList<Ingredient> newIngredients = new ArrayList<>();

        Ingredient flour = new Ingredient("Flour");
        Ingredient water = new Ingredient("Water");
        newIngredients.add(flour);
        newIngredients.add(water);

        recipe.setIngredients(newIngredients);
        assertEquals(newIngredients, recipe.getIngredients());
    }

    @Test
    void getSteps_validSteps_returnCorrectSteps() {
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Boil water");
        steps.add("Add pasta");
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), steps);
        assertEquals(steps, recipe.getSteps());
    }

    @Test
    void setSteps_validSteps_stepsUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        ArrayList<String> newSteps = new ArrayList<>();
        newSteps.add("Mix flour and water");
        newSteps.add("Knead dough");
        recipe.setSteps(newSteps);
        assertEquals(newSteps, recipe.getSteps());
    }

    @Test
    void toString_validIngredientsAndSteps_correctFormat() {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour"));
        ingredients.add(new Ingredient("Eggs"));
        ingredients.add(new Ingredient("Milk"));
        ingredients.add(new Ingredient("Sugar"));
        ingredients.add(new Ingredient("Baking powder"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Mix ingredients");
        steps.add("Heat the pan");
        steps.add("Pour batter");
        steps.add("Flip the pancake");
        steps.add("Serve hot");

        Recipe recipe = new Recipe("Pancakes", ingredients, steps);

        // Expected string representation
        String expected = "Recipe: Pancakes" + System.lineSeparator() +
                "\t" + "  Ingredients: " + System.lineSeparator() +
                "\t" + "    - Flour" + System.lineSeparator() +
                "\t" + "    - Eggs" + System.lineSeparator() +
                "\t" + "    - Milk" + System.lineSeparator() +
                "\t" + "    - Sugar" + System.lineSeparator() +
                "\t" + "    - Baking powder" + System.lineSeparator() +
                "\t" + "  Steps: " + System.lineSeparator() +
                "\t" + "    1. Mix ingredients" + System.lineSeparator() +
                "\t" + "    2. Heat the pan" + System.lineSeparator() +
                "\t" + "    3. Pour batter" + System.lineSeparator() +
                "\t" + "    4. Flip the pancake" + System.lineSeparator() +
                "\t" + "    5. Serve hot";

        // Assert the output of toString method matches expected
        assertEquals(expected, recipe.toString());
    }

    @Test
    public void getCuisine_validCuisine_returnCorrectString() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals("Italian", recipe.getCuisine());
    }

    @Test
    void setCuisine_validCuisine_stringUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals("Italian", recipe.getCuisine());
        recipe.setCuisine("New York");
        assertEquals("New York", recipe.getCuisine());
    }

    @Test
    void setTimeTaken_validTime_integerUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals(4, recipe.getTimeTaken());
        recipe.setTimeTaken(8);
        assertEquals(8, recipe.getTimeTaken());
    }

    @Test
    void equals_isSameRecipe_returnsTrue() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Bake");

        Recipe recipe1 = new Recipe("Pancakes", ingredients, steps, "American", 3);
        Recipe recipe2 = new Recipe("Pancakes", ingredients, steps, "American", 3);

        assertTrue(recipe1.equals(recipe2));
    }

    @Test
    void equals_isNotSameRecipe_returnsFalse() {
        ArrayList<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("Flour"));
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("Rice"));

        ArrayList<String> steps1 = new ArrayList<>();
        steps1.add("Bake");
        ArrayList<String> steps2 = new ArrayList<>();
        steps2.add("Cook");

        Recipe recipe1 = new Recipe("Pancakes", ingredients1, steps1, "American", 3);
        Recipe recipe2 = new Recipe("Pancakes", ingredients2, steps1, "American", 3);
        assertFalse(recipe1.equals(recipe2)); // Different ingredients

        Recipe recipe3 = new Recipe("Pancakes", ingredients1, steps2, "American", 3);
        assertFalse(recipe1.equals(recipe3)); // Different steps

        Recipe recipe4 = new Recipe("Ricecakes", ingredients1, steps1, "American", 3);
        assertFalse(recipe1.equals(recipe4)); // Different name
    }
}
