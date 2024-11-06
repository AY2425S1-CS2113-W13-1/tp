package ymfc.parser;

import ymfc.commands.Command;
import ymfc.commands.DeleteCommand;
import ymfc.commands.DeleteIngredientCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.AddIngredientCommand;
import ymfc.commands.SortCommand;
import ymfc.commands.ByeCommand;
import ymfc.commands.HelpCommand;
import ymfc.commands.EditCommand;
import ymfc.commands.FindCommand;
import ymfc.commands.AddRecipeCommand;
import ymfc.commands.ListIngredientsCommand;
import ymfc.commands.FindIngredCommand;
import ymfc.commands.RandomCommand;
import ymfc.commands.RecommendCommand;

import ymfc.exception.EmptyListException;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.HashSet;

/**
 * Parse user input commands
 */
public final class Parser {
    // First word <command> before the first white space, then all the arguments <args>
    static final Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)(\\s+(?<args>.*))?");

    /**
     * Parses user input commands and return a <code>Command</code> object
     * @param commandString Input command as <code>String</code>
     * @return <code>Command</code> object based on input command
     * @throws InvalidCommandException If command cannot be parsed
     * @throws InvalidArgumentException If command can be parsed but with invalid arguments
     */
    public static Command parseCommand(String commandString, RecipeList recipes, IngredientList ingredients)
            throws InvalidCommandException, InvalidArgumentException, EmptyListException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        if (!m.matches()) {
            throw new InvalidCommandException("Invalid command: " + commandString);
        }

        String command = m.group("command");
        String args = m.group("args") == null ? "" : m.group("args").trim();

        int numRecipes = recipes.getCounter();
        int numIngredients = ingredients.getCounter();

        switch (command) {
        case "add":
            return getAddRecipeCommand(args);
        case "delete":
            if (numRecipes <= 0) {
                throw new EmptyListException("You can't remove something from nothing!");
            }
            return getDeleteCommand(args);
        case "deleteI":
            if (numIngredients <= 0) {
                throw new EmptyListException("You can't remove something from nothing!");
            }
            return getDeleteIngredientCommand(args);
        case "listR":
            if (numRecipes <= 0) {
                throw new EmptyListException("Your recipe list is empty!");
            }
            return new ListCommand();
        case "listI":
            if (numIngredients <= 0) {
                throw new EmptyListException("Your ingredient list is empty!");
            }
            return new ListIngredientsCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "sort":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to sort!");
            }
            return getSortCommand(args);
        case "new":
            return getAddIngredientCommand(args);
        case "edit":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to edit!");
            }
            return getEditCommand(args);
        case "find":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to find!");
            }
            return getFindCommand(args);
        case "findI":
            if (numIngredients <= 0) {
                throw new EmptyListException("There are no ingredients to find!");
            }
            return getFindIngredCommand(args);
        case "random":
            if (numRecipes <= 0) {
                throw new EmptyListException("A random recipe from a pool of nothing, is nothing.");
            }
            return new RandomCommand();
        case "recommend":
            if (numRecipes <= 0) {
                throw new EmptyListException("I don't have any recipes, what do you want me to recommend?");
            }
            return new RecommendCommand();
        default:
            throw new InvalidCommandException("Invalid command: " + command + "\ntype \"help\" for assistance");
        }
    }

    private static AddIngredientCommand getAddIngredientCommand(String args) throws InvalidArgumentException {
        final Pattern addIngredientCommandFormat = Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = addIngredientCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input
                    + "\n" + AddIngredientCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String trimmedName = name.trim();
        return new AddIngredientCommand(new Ingredient(trimmedName));
    }

    /**
     * Parser for {@link AddRecipeCommand <code>AddRecipeCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>AddRecipeCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static AddRecipeCommand getAddRecipeCommand(String args) throws InvalidArgumentException {

        final Pattern addRecipeCommandFormat =
                // <n or N>/<String without forward slash>
                Pattern.compile("(?<name>[nN]/[^/]+)"
                        // Match ingredients: at least one i/ or I/ followed by any characters except '/'
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // Match steps: at least one sX/ or SX/ (X is a number) followed by any characters except '/'
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)"
                        // Match optional cuisine: c/ or C/ followed by any characters except '/'
                        + "(\\s+(?<cuisine>[cC]/[^/]+))?"
                        // Match optional time taken: t/ or T/ followed by digits
                        + "(\\s+(?<time>[tT]/\\s*[0-9]+))?");

        String input = args.trim();
        Matcher m = addRecipeCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + AddRecipeCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String trimmedName = name.trim();
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");
        ArrayList<Ingredient> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Ingredient::new)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        // Extract step identifiers (s1, s2, ...) and validate for duplicates or missing numbers
        List<String> stepIdentifiers = Arrays.stream(stepString.split("\\s+"))
                .filter(step -> step.matches("[sS][0-9]+/.*")) // Ensure the string matches the step format
                .map(step -> step.split("/")[0]) // Extracts "s1", "s2", etc.
                .toList();

        validateStepNumbers(stepIdentifiers); // Check for missing/duplicate numbers

        String cuisineInput = m.group("cuisine");
        String cuisine = null;
        if (cuisineInput != null) {
            cuisine = cuisineInput.trim().substring(2).trim();
        }
        Integer timeTaken = getTimeTakenInteger(m);

        return new AddRecipeCommand(new Recipe(trimmedName, ingreds, steps, cuisine, timeTaken));
    }

    private static Integer getTimeTakenInteger(Matcher m) throws InvalidArgumentException {
        String timeTakenString = m.group("time");

        Integer timeTaken = null;

        if (timeTakenString != null) {
            try {
                timeTaken = Integer.parseInt(timeTakenString.trim().substring(2).trim());
                if (timeTaken <= 0) {
                    throw new InvalidArgumentException("Invalid time: " + timeTakenString);
                }
            } catch (NumberFormatException exception) {
                throw new InvalidArgumentException("Invalid time: " + timeTakenString);
            }
        }
        return timeTaken;
    }

    /**
     * Parser for {@link DeleteCommand <code>DeleteCommand</code>}
     * @param args Name argument as <code>String</code>
     * @return <code>DeleteCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static DeleteCommand getDeleteCommand(String args) throws InvalidArgumentException {
        final Pattern deleteCommandFormat  =
                Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = deleteCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + DeleteCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);
        return new DeleteCommand(name);
    }

    private static DeleteIngredientCommand getDeleteIngredientCommand(String args) throws InvalidArgumentException {
        final Pattern deleteIngredientCommandFormat =
                Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = deleteIngredientCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n"
                    + DeleteIngredientCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);
        return new DeleteIngredientCommand(name);
    }

    private static SortCommand getSortCommand(String args) throws InvalidArgumentException {
        final Pattern sortCommandFormat  =
                Pattern.compile("(?<name>[sS]/[^/]+)");
        String input = args.trim();
        Matcher m = sortCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument: " + input + "\n" + SortCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);

        if (!name.equals("name") && !name.equals("time")) {
            throw new InvalidArgumentException("Invalid argument: " + input + "\n" + SortCommand.USAGE_EXAMPLE);
        }

        return new SortCommand(name);
    }

    /**
     * Parser for {@link EditCommand <code>EditCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>EditCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static EditCommand getEditCommand(String args) throws InvalidArgumentException {
        final Pattern editCommandFormat =
                // <e or E>/<String without forward slash>
                Pattern.compile("(?<name>[eE]/[^/]+)"
                        // Match ingredients: at least one i/ or I/ followed by any characters except '/'
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // Match steps: at least one sX/ or SX/ (X is a number) followed by any characters except '/'
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)"
                        // Match optional cuisine: c/ or C/ followed by any characters except '/'
                        + "(\\s+(?<cuisine>[cC]/[^/]+))?"
                        // Match optional time taken: t/ or T/ followed by digits
                        + "(\\s+(?<time>[tT]/\\s*[0-9]+))?");

        String input = args.trim();
        Matcher m = editCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + EditCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // e/ or E/ are 2 chars
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");
        ArrayList<Ingredient> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Ingredient::new)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        String cuisineInput = m.group("cuisine");
        String cuisine = null;
        if (cuisineInput != null) {
            cuisine = cuisineInput.trim().substring(2).trim();
        }
        Integer timeTaken = getTimeTakenInteger(m);

        return new EditCommand(new Recipe(name, ingreds, steps, cuisine, timeTaken));
    }

    private static Command getFindCommand(String args) throws InvalidArgumentException {
        final Pattern findCommandPattern =
                // Options to feed into findCommand, could be null
                // Length of 1 to 3, consist of only "nNiIsS", must ends with /
                Pattern.compile("(?<options>[nNiIsS]{1,3}/)?" +
                        // Query command
                        "(?<query>[^/]+)");
        String input = args.trim();
        Matcher m = findCommandPattern.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + FindCommand.USAGE_EXAMPLE);
        }

        String query = m.group("query");
        String options = m.group("options");
        if (options == null) {
            return new FindCommand(query); // Default case, no option provided
        }

        String trimmedOptions = options.trim();
        int nCount = countCharOccurrence(trimmedOptions, "[^nN]");
        int iCount = countCharOccurrence(trimmedOptions, "[^iI]");
        int sCount = countCharOccurrence(trimmedOptions, "[^sS]");
        if (nCount > 1 | iCount > 1 | sCount > 1) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" +
                    "At most ONE character for each option is allowed!" + "\n" +
                    FindCommand.USAGE_EXAMPLE);
        }

        return new FindCommand(query,
                nCount == 1, // Can only be 1 or 0
                iCount == 1,
                sCount == 1
        );
    }

    private static Command getFindIngredCommand(String args) throws InvalidArgumentException {
        final Pattern findCommandPattern = Pattern.compile("^(?<query>.+)$");

        String input = args.trim();
        Matcher m = findCommandPattern.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" +
                    FindIngredCommand.USAGE_EXAMPLE);
        }

        String query = m.group("query").trim();
        return new FindIngredCommand(query); // No options, only the query is needed
    }

    private static int countCharOccurrence(String options, String regex) {
        return options.replaceAll(regex, "").length();
    }

    public static void validateStepNumbers(List<String> stepStrings) throws InvalidArgumentException {
        Set<Integer> stepNumbers = new HashSet<>();
        int maxStepNumber = 0;

        for (String step : stepStrings) {
            // Extract the step number (e.g., "s1" -> 1)
            try {
                int stepNumber = Integer.parseInt(step.split("/")[0].substring(1));
                if (!stepNumbers.add(stepNumber)) {
                    throw new InvalidArgumentException("Duplicate step number found: s" + stepNumber
                            + "\nLearn to count!");
                }
                maxStepNumber = Math.max(maxStepNumber, stepNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                throw new InvalidArgumentException("Invalid step format. Expected format: s1, s2, ...");
            }
        }

        // Check for missing numbers in the range 1 to maxStepNumber
        for (int i = 1; i <= maxStepNumber; i++) {
            if (!stepNumbers.contains(i)) {
                throw new InvalidArgumentException("Missing step number: s" + i
                        + "\nLearn to count!");
            }
        }
    }

}
