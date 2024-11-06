# KSanjith's - Project Portfolio Page

## Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.


### Summary of Contributions

#### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ksanjith&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Features Implemented
- Created the base Command Class and RecipeList class (PR #39)
  - The Command Class acts as an abstract class for all other specific command classes to inehrit from.
  - The RecipeList class deals with Recipe objects created by the user, such as storing and modifying them.
- Created Exception classes (PR #51)
  - Wrote 3 different exception classes for different uses and 1 main parent exception class for them to inherit from 
- Created Storage Class (PR #56)
  - The Storage class deals with the storage of user created recipes and ingredients on a local file
  - Wrote code to allow YMFC to save and load recipes using a local .txt file.
- Created Sort Command (PR #67)
  - Wrote code to allow users to sort their recipes either alphabetically (by name) or numerically (by time taken).


#### Enhancements Implemented
- Created additional tests for existing classes and methods to increase overall coverage (PR #81)
  - Total Class Coverage improved from 58% to 91%
  - Total Method Coverage improved from 47% to 77%
  - Total Line Coverage improved from 46% to 71%
  - Total Branch Coverage improved from 40% to 54%
- Expanded on the Help Command's output to better explain all the available commands and their usage (PR #79)
- Started using EmptyListException to throw exceptions when user is trying to modify, search, sort or delete
from an empty recipe list (PR #81)


#### Contributions to the UG
- Wrote out the first skeleton draft of the UG that contained basic descriptions of all features within YMFC (PR #88)
- Wrote the Command Summary Section
- Contributed to the Usage section


#### Contributions to the DG
- Created a sequence diagram for Loading Saved Recipes (PR #79)
- Created a sequence diagram for Saving Recipes (PR #79)
- Created a class diagram containing all Command Classes (PR #98)
- Created a class diagram containing the Ingredient and IngredientList Classes (PR #108)
- Contributed to sections such as Non-Functional Requirements, User Stories and Glossary (PR #111)


#### Contributions to Team-Based Tasks
- Fixed various bugs in the code (PR #93)
  - Fixed bug where the incorrect use of the add ingredient command returns the wrong message to the user
  - Fixed bug where sort command hangs when recipes without a time taken parameter are in the list
- Enhanced code to reject user's input when they try to create a recipe with a name that has already been added (PR #98)
- Added JavaDoc headers to multiple methods
- Helped routinely add new issues to work on and close issues that have been resolved


#### Review/Mentoring Contributions
- Gave non-trivial review comments to PRs (PR #69, #89, #94)
- Helped groupmate set up saving and loading of ingredients based on my saving and loading 
implementation for the recipes
