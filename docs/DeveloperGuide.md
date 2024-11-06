# Developer Guide

## Acknowledgements

Certain aspects of YMFC's code was inspired from the following member(s) IPs:

[Sanjith](http://github.com/KSanjith/ip) : Referenced to develop the working mechanisms of YMFC's storage class
[Sze Kang](https://github.com/gskang-22/ip) : Referenced to develop the working mechanisms of YMFC's command classes
<div style="page-break-after: always;"></div>

## Design & implementation

### Architecture
![ArchitectureDiagram.png](img/ClassDiagrams/ArchitectureDiagram.png)

The **_Architecture Diagram_** given above explains the high-level design of the YMFC App.

Given below is a quick overview of main components and how they interact with each other.

<div style="page-break-after: always;"></div>

#### Main components of the architecture
`YMFC` (consisting of `YMFC` class) is in charge of app launch and shut down. 
- At app launch, it initialises the other components in the correct sequence, and connects them up with each other.

The bulk of the app's work is done by the following components:
- `UI`: The user interface of the App. It handles user interaction, namely reading user input and displaying messages.
- `Storage`: Reads data from, and writes data to, the hard disk. 
Responsible for loading and saving both recipes and ingredients.
- `Parser`: The command parser. It interprets user inputs,
turning them into `Command` objects that are executed by the app. 
- `Command`: Represents actions that the user can perform, such as adding, deleting or listing recipes and ingredients.
- `RecipeList` and `IngredientList`: These manage collections of recipes and ingredients, respectively. 

<div style="page-break-after: always;"></div>

### How the architecture components interact with each other
The sequence diagram below shows how the components interact with each other when the application is first launched.

![ApplicationStartUp.png](img/SequenceDiagrams/ApplicationStartUp.png)

(Note: The lifelines of objects cannot be hidden after the X in the PlantUML software used to make this class diagram)

<div style="page-break-after: always;"></div>

### Ui Class
The Ui class deals with taking in inputs from an input stream (in this case, the user input through `System.in`), 
and then printing messages read-able by the user (through `System.out`). 

### Recipe Class
The Recipe class represents a singular recipe added by the user or loaded from the storage database. 
A valid recipe object contains the following fields:
+ Name
+ List of Ingredients
+ List of Steps
+ Cuisine (optional)
+ Time taken (optional)

The formatting of how a recipe is displayed and saved is handled by this class.

### RecipeList Class
The RecipeList class represents a list of recipes. 
This class handles the addition, deletion and editing of recipes.

Shown below is a class diagram detailing the interaction between `RecipeList` and `Recipe`:
![RecipeClassDiagram.png](img/ClassDiagrams/recipesClassDiagram.png)

<div style="page-break-after: always;"></div>

### Ingredient Class
The 'Ingredient' class represents an individual ingredient added by the user or loaded from the storage database.

#### Implementation Details

1. Attributes:
   - String Name
     - Stores the name of the ingredient, which serves as its unique identifier
2. Constructor:
   - Ingredient(String name): 
     - Initializes an Ingredient instance with the specified name
3. Methods:
   - boolean equals(Ingredient ingredientToCheck):
     - Compares the current Ingredient with another Ingredient based on their names
   - @Override boolean equals(Object object):
     - An overridden method that supports Java collection operations like retainAll and removeAll, allowing ingredients 
to be compared based on name alone in collections
   - String toString():
     - Returns the ingredient's name
   - String toSaveString():
     - Returns a formatted string for saving ingredient data to a text file
     - Follows the format "new n/<ingredient_name>"

The formatting of how an ingredient is displayed and saved is handled by this class.

### IngredientList Class
The IngredientList class represents a list of ingredients.
This class handles the addition, deletion and editing of ingredients.

#### Implementation Details
1. Attributes: 
   - ArrayList<Ingredient> ingredients: Holds a list of Ingredient objects, representing the ingredients in the list
2. Constructor:
   - IngredientList():
   Initializes an empty list of ingredients.
3. Methods:
   - void addIngredient(Ingredient ingredient):
     - Adds an Ingredient to the list 
   - ArrayList<Ingredient> getIngredients():
     - Returns the current list of ingredients
   - Ingredient getIngredient(int id):
     - Retrieves an ingredient by its index in the list
   - boolean removeIngredientByName(String name):
     - Removes an Ingredient by name
     - Returns true if the ingredient was found and removed, otherwise false
   - int getCounter():
     - Returns the number of ingredients in the list
   - void sortAlphabetically():
     - Sorts the ingredients list alphabetically by ingredient name

Shown below is a class diagram detailing the interaction between `IngredientList` and `Ingredient`:
![ingredientClasses.png](img/ClassDiagrams/ingredientsClassDiagram.png)

<div style="page-break-after: always;"></div>

### Command Classes
The command class deals with all the possible commands accepted by YMFC, with each command representing a specific 
recognised user input (E.g. ListCommand representing the command to list all the recipes in the recipeList).

#### 1. The `Command` base class
- Serves as the abstract parent class for all specific commands
- Defines the basic structure and contract that each command class must follow
- Each command performs a unique operation and can be expanded to include more functionality as required by YMFC
- Standardises command behavior and establishes an inheritance hierarchy, allowing for the seamless addition and usage 
of command functionalities
- Core Elements:
  - `isBye`: A flag that tracks if an exit command has been issued, following which programme should be terminated
    - Set by calling `setBye() `
    - Checked with `isBye()`
  - `execute()`: An abstract method that must be implemented by all subclasses
    - Defines the primary action performed by the command (E.g. add new recipe to `recipeList` for `AddRecipeCommand`)

Shown below is a class diagram detailing the interaction between the parent `Command` class and it's child classes:
![commandClassesShort.png](img/ClassDiagrams/commandsShortClassDiagram.png)

<div style="page-break-after: always;"></div>

#### 2. Command Flow (Using `Command` Classes)
1. Initialisation: Instantiate a `Command` subclass by passing user input through the `parseCommand()` of `Parser`
2. Exit signal: If a command is meant to terminate the app (E.g. `ByeCommand`), set `isBye` to true
3. Execution: Call `execute()`, performing the desired action and interacting with YMFC's data and UI components

#### 3. Adding new `Command` Classes
- Create a `Command` class:
  1. Inherit from `Command`: Each new command class extends `Command`, inheriting its structure and providing a specific 
implementation of the `execute()` method
  2. Override `execute()`: Implement `execute()` to define how the command should interact with `RecipeList`,
`IngredientList`, `Ui`, and `Storage`
  3. Additional methods (Optional): You may add helper methods within the class if necessary
- Register the command
  1. Update the `parseCommand()` method in `Parser` to recognise and instantiate the new command

<div style="page-break-after: always;"></div>

#### `Command` Child Classes

1. `AddRecipeCommand` Class
- The AddRecipeCommand class adds a newly created Recipe object to the currentRecipeList object's ArrayList of Recipes
 
Shown below is a sequence diagram detailing how a new recipe is added using the AddRecipeCommand object,
starting from parsing the user input to the addition of the recipe to the recipeList object,
to finally printing the "recipe added" message to the User on the CLI:
![AddCommand.png](img/SequenceDiagrams/AddRecipesSequenceDiagram.png)

<div style="page-break-after: always;"></div>

2. `EditCommand` Class

- The EditCommand class finds an existing recipe in recipelist by name, and then replaces its parameter
with the new parameters that the user inputted.

Shown below is a sequence diagram detailing how an existing recipe is modified using the EditCommand object,
starting from parsing the user input to finding the exiting recipe in the RecipeList object,
to finally modifying and saving the recipe in the RecipeList:
![EditCommand.png](img/SequenceDiagrams/EditRecipesSequenceDiagram.png)

<div style="page-break-after: always;"></div>

3. `AddIngredientCommand` Class
- The `AddRecipeCommand` class is responsible for adding a new recipe to `RecipeList`
- When executed, it takes a `Recipe` object, adds it to the `RecipeList`, saves the updated list to the storage, and 
notifies the user of the addition through the `Ui` component.
4. `ByeCommand` Class
- The `ByeCommand` class represents the command to terminate the application
- When executed, it signals the application to exit by setting a flag (`isBye`) 
and displays a farewell message to the user
5. `DeleteCommand`
- The `DeleteCommand` class represents the command to delete a recipe by its name from `RecipeList` 
- When executed, this command searches `RecipeList` for the recipe, removes it if found, saves the updated list to the
storage, and informs the user of the successful deletion or an error if the recipe is not found
6. `DeleteIngredientCommand` Class
- The `DeleteIngredientCommand` class is responsible for deleting an `Ingredient` from the `IngredientList` based on 
its name
- Upon execution, it removes the specified `Ingredient`, updates the `Ingredientlist` in the storage, and notifies 
the user about the deletion
7. `FindCommand` Class
- 
8. `FindIngredCommand` Class
- 
9. `HelpCommand` Class
- The `HelpCommand` class is designed to provide the user with a list of available commands and detailed instructions 
on how to use them
- When executed, it calls the `printHelp()` method of the `Ui` to display help information
10. `ListCommand` Class
- The `ListCommand` class is designed to list all the recipes present in the `RecipeList`
- When executed, it retrieves the list of recipes and displays them to the user through the UI
11. `ListIngredientsCommand` Class
- The `ListIngredientsCommand` class is responsible for listing all the ingredients currently stored in the 
`IngredientList`
- When executed, it retrieves the list of ingredients and displays them to the user through the UI
12. `RandomCommand` Class
- 
13. `RecommendCommand` Class
- 
14. `SortCommand` Class
- 
15. `SortIngredientsCommand` Class
- ~~~~

### Parser Class
Self-explanatory, made for parsing user's input command. This class only consist of one public static method 
`parseCommand()` in order to process input commands.
The remaining private methods represent separated cases for different commands.

Shown below is a sequence diagram detailing how the Parser class interprets the user's CLI input
and returns the appropriate command object containing the user's input parameters.
![parseCommandShort.png](img/SequenceDiagrams/parseCommandShortSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### Storage Class
The Storage class 
- saves the User's added recipes to a .txt file
  - The following Sequence diagram shows how the saveRecipes() method in the Storage class
  saves all the created recipes into the .txt file

![saveRecipes.png](img/SequenceDiagrams/saveRecipesSequenceDiagram.png)

(Note: The lifelines of objects cannot be hidden after the X in the PlantUML software used to make this class diagram)

<div style="page-break-after: always;"></div>

- reads the .txt save file to load in past saved recipes when app is launched
  - The following Sequence diagram shows how the loadRecipes() method in the Storage class
    loads in past saved recipes from the .txt file when the app is first launched

![loadRecipes.png](img/SequenceDiagrams/loadRecipesSequenceDiagram.png)

(Note: The lifelines of objects cannot be hidden after the X in the PlantUML software used to make this class diagram)

The Storage class also saves and loads the list of user's available ingredients to another .txt save file
The mechanisms with which it does so is highly similar to the 2 sequence diagrams above.

<div style="page-break-after: always;"></div>

## Product scope
### Target user profile

Cooks who want to quickly search up recipes or get recipe suggestions based on their available ingredients

### Value proposition

Cooks these days have more recipes than they know how to handle, and our product will help them store, retrieve and
search through their recipes with ease. Prompts, tags and ingredients can be used to search a curated database,
and recommend random recipes that closely match the criteria.

## User Stories

| Version | As a ...      | I want to ...                                    | So that I can ...                                                 |
|---------|---------------|--------------------------------------------------|-------------------------------------------------------------------|
| v1.0    | new user      | see list of available commands                   | refer to them when I forget how to use the application            |
| v1.0    | constant user | see list of all my added recipes                 | glance at all my recipes in one go                                |
| v1.0    | picky user    | remove recipes from database                     | remove recipes that do not fit my changing tastebuds              |
| v1.0    | busy user     | store information about my recipes in a database | have a tool to organise my recipes                                |
| v2.0    | user          | sort my recipes by preparation time              | find a recipe that I can cook within my current time limit        |
| v2.0    | heavy user    | find specific recipes by name/ingredients/steps  | filter through my huge list of recipes easily to find what I want |


<div style="page-break-after: always;"></div>

## Non-Functional Requirements

1. Should work on any Windows, MacOS or Linux computers as long as Java 17 is installed
2. Should be easy to move data between different devices
   - Only 2x .txt files need to be moved over
3. Should be easy to search, find and sort recipes in the list
   - There is the extensive ability to search for matching recipes by name, ingredients or steps
   - There is also the ability to sort recipes by name or by time taken

## Glossary

* *Recipe* - A plan on cooking a meal that contains various attributes such as name, ingredients needed,
steps to take, cuisine and time taken to cook
* *RecipeList* - The collection of Recipes added by the user
* *Ingredient* - A specific cooking item that the user currently has in their inventory
* *IngredientList* - The collection of Ingredients available to the user

## Instructions for manual testing

Refer to the [UserGuide](https://ay2425s1-cs2113-w13-1.github.io/tp/UserGuide.html) 
for the list of available commands and their expected behaviour
