package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

public abstract class Command {
    protected static boolean isBye;

    public Command() {
        isBye = false;
    }

    public void setBye() {
        Command.isBye = true;
    }

    public boolean isBye() {
        return isBye;
    }

    public void execute(RecipeList recipes, Ui ui) {
    }
}
