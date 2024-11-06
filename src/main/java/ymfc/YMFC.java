package ymfc;

import java.util.logging.Logger;
import java.util.logging.Level;

import ymfc.list.IngredientList;
import ymfc.parser.Parser;
import ymfc.commands.Command;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

public class YMFC {
    public static Logger logger = Logger.getLogger(YMFC.class.getName());

    /**
     * Main entry-point for the java.ymfc.YMFC application.
     */
    public static void main(String[] args) {

        Ui ui = new Ui(System.in);
        RecipeList recipeList = new RecipeList();
        IngredientList ingredientList = new IngredientList();
        Storage storage = new Storage();
        boolean saidBye = false;

        logger.log(Level.FINE, "Starting YMFC");
        ui.greet();

        storage.loadRecipes(recipeList, ingredientList, ui, storage);
        storage.loadIngredients(recipeList, ingredientList, ui, storage);

        String userInput;
        while (!saidBye) {
            userInput = ui.readCommand();

            try {
                Command command = Parser.parseCommand(userInput, recipeList, ingredientList);
                command.execute(recipeList, ingredientList, ui, storage);

                if (command.isBye()) {
                    saidBye = true;
                    logger.log(Level.INFO, "User said bye");
                }
            } catch (Exception exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
        logger.log(Level.FINE, "Ending YMFC");
    }
}
