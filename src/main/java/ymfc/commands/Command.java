package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

/**
 * Represents an abstract command in YMFC.
 * Each command is designed to execute a specific operation and has the ability to indicate
 * if it is the exit command (bye).
 */
public abstract class Command {
    protected boolean isBye;

    /**
     * Constructor for the Command class.
     * Initializes the isBye flag to false.
     */
    public Command() {
        isBye = false;
    }

    /**
     * Sets the isBye flag to true, indicating that
     * the "bye" command has been issued, and the program should terminate.
     */
    public void setBye() {
        this.isBye = true;
    }

    /**
     * Returns whether the "bye" command has been issued.
     *
     * @return true if the "bye" command has been invoked, false otherwise.
     */
    public boolean isBye() {
        return isBye;
    }

    /**
     * Executes the specific command operation.
     * This method must be implemented by all subclasses to define how the command interacts with
     * the RecipeList, IngredientList, UI, and storage components.
     *
     * @param recipes The RecipeList object to operate on.
     * @param ingredients The IngredientList object to operate on.
     * @param ui The Ui object for user interactions.
     * @param storage The Storage object for handling data persistence.
     * @throws Exception if the command execution encounters an error.
     */
    public abstract void execute(RecipeList recipes, IngredientList ingredients,
                                 Ui ui, Storage storage) throws Exception;
}
