package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

/**
 * Represents an abstract command in the application.
 * Commands are used to execute specific operations.
 * Each command has the ability to signal whether it is the exit command (bye).
 */
public abstract class Command {
    protected static boolean isBye;

    /**
     * Constructor for the {@code Command} class.
     * Initializes the {@code isBye} flag to {@code false}.
     */
    public Command() {
        isBye = false;
    }

    /**
     * Sets the {@code isBye} flag to {@code true}, indicating that
     * the "bye" command has been issued, and the program should terminate.
     */
    public void setBye() {
        Command.isBye = true;
    }

    /**
     * Returns whether the "bye" command has been issued.
     *
     * @return {@code true} if the "bye" command has been invoked, {@code false} otherwise.
     */
    public boolean isBye() {
        return isBye;
    }

    /**
     * Executes the specific command operation.
     * This method must be implemented by all subclasses to define
     * how the command interacts with the recipe list, user interface, and storage.
     *
     * @param recipes The {@code RecipeList} object to operate on.
     * @param ui The {@code Ui} object for user interactions.
     * @param storage The {@code Storage} object for handling data persistence.
     * @throws Exception if the command execution encounters an error.
     */
    public abstract void execute(RecipeList recipes, Ui ui, Storage storage) throws Exception;
}
