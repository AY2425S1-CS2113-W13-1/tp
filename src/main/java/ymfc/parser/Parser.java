package ymfc.parser;

import ymfc.commands.AddRecipeCommand;
import ymfc.commands.ByeCommand;
import ymfc.commands.Command;
import ymfc.commands.DeleteCommand;
import ymfc.commands.HelpCommand;
import ymfc.commands.ListCommand;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;
import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parse user input commands
 */
public final class Parser {
    // First word <command> before the first white space, then all the arguments <args>
    // static final Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)\\s+(?<args>.*)");
    static final Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)(\\s+(?<args>.*))?");

    // static final Pattern ARGUMENTS_FORMAT = Pattern.compile("(?<args>[a-zA-Z][0-9]?/.*)*");

    /**
     * Parses user input commands and return a <code>Command</code> object
     * @param commandString Input command as <code>String</code>
     * @return <code>Command</code> object based on input command
     * @throws InvalidCommandException If command cannot be parsed
     * @throws InvalidArgumentException If command can be parsed but with invalid arguments
     */
    public static Command parseCommand(String commandString) throws InvalidCommandException, InvalidArgumentException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        if (!m.matches()) {
            throw new InvalidCommandException("Invalid command: " + commandString);
        }

        String command = m.group("command");
        // String args = m.group("args");
        String args = m.group("args") == null ? "" : m.group("args").trim();
        switch (command) {
        case "add":
            return getAddRecipeCommand(args);
        case "delete":
            return getDeleteCommand(args);
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidCommandException("Invalid command: " + command + "\ntype \"help\" for assistance");
        }
    }

    public static AddRecipeCommand parseLoadedCommand(String commandString) throws InvalidArgumentException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        m.matches();

        //String command = m.group("command");
        String args = m.group("args") == null ? "" : m.group("args").trim();
        return getAddRecipeCommand(args);
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
                        // (<at least 1 whitespace><i or I>/<String without forward slash>) at least once
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // (<at least 1 whitespace><s or S><Number>/<String without forward slash>) at least once
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)");
        args = args.trim();
        Matcher m = addRecipeCommandFormat.matcher(args);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + args + "\n" + AddRecipeCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");
        ArrayList<String> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        return new AddRecipeCommand(new Recipe(name, ingreds, steps));
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
        args = args.trim();
        Matcher m = deleteCommandFormat .matcher(args);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + args + "\n" + DeleteCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);
        // return new DeleteCommand(0);
        return new DeleteCommand(name);
    }
}
