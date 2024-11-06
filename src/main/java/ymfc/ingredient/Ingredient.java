package ymfc.ingredient;

public class Ingredient {
    private String name;
    //int quantity;

    public Ingredient(String name) {
        this.name = name;
        //this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Ingredient ingredientToCheck) {
        return name.equals(ingredientToCheck.getName());
    }

    /**
     * Override equals function, so that java methods like retainAll and removeAll
     * knows how to determine equality between two lists of ingredients.
     *
     * @param object The other ingredient object to be compared with
     * @return Boolean denoting whether the name of both ingredients are equal
     */
    @Override
    public boolean equals(Object object) {
        Ingredient otherIngredient = (Ingredient) object;
        return this.name.equals(otherIngredient.getName());
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a string in the proper format to be written to the ingredients.txt save file
     *
     * @return String to be written to the save file
     */
    public String toSaveString() {

        String ingredientDetails = "new n/" + name;

        return ingredientDetails;
    }
}
