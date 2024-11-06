package ymfc.storage;

import static ymfc.YMFC.logger;
import ymfc.commands.AddIngredientCommand;
import ymfc.commands.AddRecipeCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.ListIngredientsCommand;
import ymfc.exception.EmptyListException;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;
import ymfc.parser.Parser;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Storage {
    private static final String saveRecipeFilePath = "./data/recipes.txt";
    private static final String saveIngredientFilePath = "./data/ingredients.txt";

    public Storage() {
    }

    /**
     * Add the entire list of recipes to the recipes.txt save file.
     *
     * @param recipes Object containing the ArrayList of recipes to be saved.
     * @throws IOException If the save file cannot be properly accessed.
     */
    public void saveRecipes(RecipeList recipes) throws IOException {
        File dir = new File("./data");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter(saveRecipeFilePath);
        for (int i = 0; i < recipes.getCounter(); i++) {
            if (i == recipes.getCounter() - 1) {
                writer.write(recipes.getRecipe(i).toSaveString());
            } else {
                writer.write(recipes.getRecipe(i).toSaveString() + System.lineSeparator());
            }
        }
        writer.close();
    }

    /**
     * Add the entire list of ingredients to the ingredients.txt save file.
     *
     * @param ingredients Object containing the ArrayList of ingredients to be saved.
     * @throws IOException If the save file cannot be properly accessed.
     */
    public void saveIngredients(IngredientList ingredients) throws IOException {
        File dir = new File("./data");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter(saveIngredientFilePath);
        for (int i = 0; i < ingredients.getCounter(); i++) {
            if (i == ingredients.getCounter() - 1) {
                writer.write(ingredients.getIngredient(i).toSaveString());
            } else {
                writer.write(ingredients.getIngredient(i).toSaveString() + System.lineSeparator());
            }
        }
        writer.close();
    }

    /**
     * Load in all the previously saved recipes from the recipes.txt save file to the RecipeList object.
     *
     * @param recipes Object containing the ArrayList of recipes to be saved.
     * @param ingredients Object for user's ingredients.
     * @param ui Object to access the UI class.
     * @param storage Object to access the Storage class.
     */
    public void loadRecipes(RecipeList recipes, IngredientList ingredients,
                            Ui ui, Storage storage) {
        Scanner reader;
        try {
            File saveFile = new File(saveRecipeFilePath);
            reader = new Scanner(saveFile);
        } catch (FileNotFoundException exception) {
            logger.log(Level.INFO, "No recipes savefile found");
            return;
        }

        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            try {
                addRecipe(recipes, ingredients, line);
            } catch (InvalidArgumentException | InvalidCommandException | EmptyListException exception) {
                System.out.println(exception.getMessage());
            }
        }

        if (isEmpty) {
            return;
        }
        ListCommand lister = new ListCommand();
        lister.execute(recipes, ingredients, ui, storage);
    }

    /**
     * Load in all the previously saved ingredients from the ingredients.txt save file to the IngredientList object.
     *
     * @param recipes Object for user's recipes.
     * @param ingredients Object containing the ArrayList of ingredients to be saved.
     * @param ui Object to access the UI class.
     * @param storage Object to access the Storage class.
     */
    public void loadIngredients(RecipeList recipes, IngredientList ingredients,
                                Ui ui, Storage storage) {
        Scanner reader;
        try {
            File saveFile = new File(saveIngredientFilePath);
            reader = new Scanner(saveFile);
        } catch (FileNotFoundException exception) {
            logger.log(Level.INFO ,"No ingredients savefile found");
            return;
        }

        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            try {
                addIngredient(recipes, ingredients, line);
            } catch (InvalidArgumentException | InvalidCommandException | EmptyListException exception) {
                System.out.println(exception.getMessage());
            }
        }

        if (isEmpty) {
            return;
        }
        ListIngredientsCommand lister = new ListIngredientsCommand();
        lister.execute(recipes, ingredients, ui, storage);
    }

    private void addRecipe(RecipeList recipes, IngredientList ingredients, String line)
            throws InvalidArgumentException, InvalidCommandException, EmptyListException {
        AddRecipeCommand command = (AddRecipeCommand) Parser.parseCommand(line, recipes, ingredients);
        command.addLoadedRecipe(recipes);
    }

    private void addIngredient(RecipeList recipes, IngredientList ingredients, String line)
            throws InvalidArgumentException, InvalidCommandException, EmptyListException {
        AddIngredientCommand command = (AddIngredientCommand) Parser.parseCommand(line, recipes, ingredients);
        command.addLoadedIngredient(ingredients);
    }
}
