package ymfc.ui;

import ymfc.recipe.Recipe;

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class representing the user interface for interacting with the user.
 */
public class Ui {
    private String line = "\t_____________________________________________________________________________";
    private String logo = "                 (\\\n" +
            "                  \\ \\\n" +
            "              __    \\/ ___,.-------..__        __\n" +
            "             //\\\\ _,-'\\\\               `'--._ //\\\\\n" +
            "             \\\\ ;'      \\\\                   `: //\n" +
            "              `(          \\\\                   )'\n" +
            "                :.          \\\\,----,         ,;\n" +
            "                 `.`--.___   (    /  ___.--','\n" +
            "                   `.     ``-----'-''     ,'\n" +
            "                      -.               ,-\n" +
            "                         `-._______.-'\n";


    private Scanner userInput;

    /**
     * Constructor for a <code>YMFC.Ui</code> object.
     * Overloads default constructor to initialise a scanner object reading user inputs.
     */
    public Ui(InputStream input) {
        this.userInput = new Scanner(input);
    }

    /**
     * Display a greeting message.
     */
    public void greet() {
        System.out.println(line);
        System.out.println(logo);
        System.out.println("\tGreetings, this is YMFC.");
        System.out.println("\tGot a recipe? Pass it to me.");
        System.out.println("\tNeed a recipe? Just ask me.");
        System.out.println("\tNeed your dishes washed? Get off your couch.");
        System.out.println(line);
    }

    /**
     * Display a goodbye message.
     */
    public void bidFarewell() {
        System.out.println(line);
        System.out.println(logo);
        System.out.println("\tBye bye, come again!");
        System.out.println(line);
    }

    /**
     * Return user input as a string when user hit enter key.
     *
     * @return String representing what user typed into the program
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Display a specified message.
     *
     * @param input String array representing the message, each element representing one line
     */
    public void printMessage(String[] input) {
        System.out.println(line);
        for (String line: input) {
            System.out.println("\t" + line);
        }
        System.out.println(line);
    }

    public void printErrorMessage(String error) {
        System.out.println(error);
    }

    /**
     * Display a newly added recipe.
     *
     * @param addedRecipe Recipe that has been added
     * @param listCount Number of recipes currently in the list
     */
    public void printAddedRecipe(String addedRecipe, int listCount) {
        System.out.println(line);
        System.out.println("\tOkie dokie, one more recipe for me:");
        System.out.println("\t " +  addedRecipe);
        // Conditional operator to pluralize "recipe" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount <= 1 ? " recipe" : " recipes") + " in your list.");
        System.out.println(line);
    }

    /**
     * Display the entire list of recipe.
     *
     * @param list ArrayList of recipes to be displayed
     * @param listCount Integer representing total number of recipes in <code>list</code>
     */
    public void printList(ArrayList<Recipe> list, int listCount) {
        System.out.println(line);
        System.out.println("\tHere's everything in my collection so far:");
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
            System.out.println(line);
        }
        System.out.println(line);
    }

    /**
     * Display a recipe that been deleted.
     *
     * @param deletedRecipe Recipe that has been deleted
     * @param listCount Integer representing number of recipes in the list currently
     */
    public void printDeletedTask(String deletedRecipe, int listCount) {
        System.out.println(line);
        System.out.println("\tAww, I shall begrudgingly let go of this recipe:");
        System.out.println("\t  " + deletedRecipe);
        // Conditional operator to pluralize "recipe" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount <= 1 ? " recipe" : " recipes") + " in your list.");
        System.out.println(line);
    }


    public void printHelp() {
        System.out.println(line);
        System.out.println("Commands: help, delete, add, list");
        System.out.println(line);
    }
}
