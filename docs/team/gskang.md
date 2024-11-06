# Sze Kang's - Project Portfolio Page

## Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.


### Summary of Contributions

#### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=gskang-22&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Features Implemented
- Created command classes, inheriting from the base Command class
  - addRecipeCommand, byeCommand, deleteCommand, listCommand, AddIngredientCommand, ListIngredientsCommand
  - Command classes deal with all the possible commands accepted by YMFC, with each command representing a specific 
recognised user input
- Created the Ingredient class, as well as its corresponding addIngredient and listIngredient logic
  - The Ingredient class represents the ingredients owned by the user. 
  - Wrote code to allow users to add new ingredients to their inventory list and obtain a list of all the ingredients
inside
  - Wrote code to allow YMFC to save and load Ingredients using a local .txt file.

#### Enhancements Implemented
- Created tests to ensure the proper functioning of command Classes
- Improved on the usage example messages given during incorrect user input


#### Contributions to the UG


#### Contributions to the DG
- Created a sequence diagram for the use of AddRecipeCommand
- Added description for the section on `Command` class and its subclasses


#### Contributions to Team-Based Tasks
- Helped routinely add new issues to work on and close issues that have been resolved
- Added JavaDoc comments to command classes and their methods
- Created and established the GitHub repository and organization for this project

#### Review/Mentoring Contributions
- Helped review and close teammates' PRs
- Gave non-trivial review comments to PRs (PR #69, #89, #94)
