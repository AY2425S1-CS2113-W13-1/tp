package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
