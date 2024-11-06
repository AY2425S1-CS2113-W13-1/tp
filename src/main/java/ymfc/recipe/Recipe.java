package ymfc.recipe;

import ymfc.ingredient.Ingredient;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    // Optional attributes
    private String cuisine;
    private Integer timeTaken;

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = null;
        this.timeTaken = null;
    }

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> steps,
                  String cuisine, Integer timeTaken) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = cuisine;
        this.timeTaken = timeTaken;
    }

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> steps, String cuisine) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = cuisine;
        this.timeTaken = null;
    }

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> steps, Integer timeTaken) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = null;
        this.timeTaken = timeTaken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getTimeTaken() {
        if (timeTaken == null) {
            return 0;
        }
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    public boolean equals(Recipe recipeToCheck) {
        if (!name.equals(recipeToCheck.getName())) {
            return false;
        }

        ArrayList<Ingredient> otherIngredients = recipeToCheck.getIngredients();
        if (ingredients.size() != otherIngredients.size()) {
            return false;
        }

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            Ingredient otherIngredient = otherIngredients.get(i);

            if (!ingredient.equals(otherIngredient)) {
                return false;
            }
        }

        if (!steps.equals(recipeToCheck.getSteps())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder recipeDetails = new StringBuilder();
        recipeDetails.append("Recipe: ").append(name).append(System.lineSeparator());

        recipeDetails.append("\t" + "  Ingredients: ");
        for (Ingredient ingredient : ingredients) {
            recipeDetails.append(System.lineSeparator()).append("\t" + "    - ").append(ingredient);
        }

        recipeDetails.append(System.lineSeparator()).append("\t" + "  Steps: ");
        for (int i = 0; i < steps.size(); i++) {
            recipeDetails.append(System.lineSeparator()).append("\t" + "    ")
                    .append(i + 1).append(". ").append(steps.get(i));
        }

        if (cuisine != null) {
            recipeDetails.append(System.lineSeparator()).append("\t" + "  Cuisine: ").append(cuisine);
        }

        if (timeTaken != null) {
            recipeDetails.append(System.lineSeparator()).append("\t" + "  Time taken: ").append(timeTaken);
        }

        return recipeDetails.toString();
    }

    /**
     * Returns a string in the proper format to be written to the recipes.txt save file
     *
     * @return String to be written to the save file
     */
    public String toSaveString() {
        StringBuilder recipeDetails = new StringBuilder();
        recipeDetails.append("add n/").append(name).append(" ");

        for (Ingredient ingredient : ingredients) {
            recipeDetails.append("i/").append(ingredient).append(" ");
        }

        for (int i = 0; i < steps.size(); i++) {
            recipeDetails.append("s").append(i+1).append("/").append(steps.get(i)).append(" ");
        }

        if (cuisine != null) {
            recipeDetails.append("c/").append(cuisine).append(" ");
        }

        if (timeTaken != null) {
            recipeDetails.append("t/").append(timeTaken).append(" ");
        }

        return recipeDetails.toString();
    }
}
