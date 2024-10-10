package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        String recipeName = recipes.getRecipe(index).getName();
        recipes.removeRecipe(index);
        ui.printDeletedTask(recipeName, recipes.getCounter());
    }
}
