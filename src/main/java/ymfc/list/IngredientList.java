package ymfc.list;

import ymfc.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.Comparator;

public class IngredientList {
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public IngredientList() {
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredient(int id) {
        return ingredients.get(id);
    }

    public boolean removeIngredientByName(String name) {
        assert !ingredients.isEmpty() : "List should not be empty when deleting ingredient";
        assert name != null : "Ingredient name should not be null";
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equalsIgnoreCase(name)) {
                ingredients.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getCounter() {
        return ingredients.size();
    }

    public void sortAlphabetically() {
        ingredients.sort(Comparator.comparing(Ingredient::getName, String.CASE_INSENSITIVE_ORDER));
    }
}
