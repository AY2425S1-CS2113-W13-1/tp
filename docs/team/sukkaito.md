# Sukkaito - Project Portfolio Page

## Project: Your Mother's Favourite Cookbook (YMFC)

------------------------
### Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.

Given below are my contributions to the project.

---------
### Summary of Contributions

* **New Feature**: Backbone of `Parser` class and first few calling conditions for `add`, `delete`, `help` and `bye` commands.
    * What it does: This class is used to validate, then parse user's command for every action through CLI.
    * Justification: This feature is one of the backbone class of the whole program, in charge of interpreting user's requests, allows other component to execute perfectly.
    * Highlights: The hardest parts were checking for commands' pattern using regex, as it requires some knowledge about applying regex to a certain extent. It also helps as the path for the remaining commands afterwards
    * Credits: Idea of using regex comes from [addressbook-level2](https://github.com/se-edu/addressbook-level2)'s [`Parser` class](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java).

* **New Feature**: `YMFCException` class and some of its dependencies.
    * What it does: This class is used to handle exceptions specific to YMFC.
    * Justification: This feature is necessary since using built-in Java's `Exception` class can be confusing during development, and using custom exceptions would greatly enhance the readability of the source code.
    * Credits: Idea from implementing my [iP](https://github.com/Sukkaito/ip) custom exceptions.

* **New Feature**: `find` command and the `FindCommand` class
    * What it does: This class holds the arguments and options of the `find` command from user's input to find recipes based on provided options.
    * Justification: 
    * Highlights: The hardest part was filtering out invalid options, making sure that the command works for any valid subsets' permutations of "n", "i" and "s", as a small enhancement for users.
    * Credits: Idea from team discussions.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=Sukkaito&tabRepo=AY2425S1-CS2113-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
    * Bug fixing (Pull requests [\#116](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/116), [\#120](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/120))
    * Tests for `parseCommand()` function of `Parser` class (Pull request [\#53](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/53)), `find` command (Pull request [\#71](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/71))
    * Other minor additions and modifications and code cleanup (details in RepoSense link above)

* **Documentation**:
    * User Guide:
        * Added documentation for features `find`, table of content and some document formating [\#99](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/99)
    * Developer Guide:
        * Section `Parser` class (Pull request [\#97](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/97))
        * Section `Recipe` and `RecipeList` diagrams (Pull request [\#110](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/110))
        * Directory cleanup (Pull request [\#102](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/102))

* **Contributions to team-based tasks**:
    * Standardize issues, bug reports, commit messages, PRs format,...

* **Review/mentoring contributions**: 
    * List of PRs reviewed: [Link](https://github.com/AY2425S1-CS2113-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Asukkaito)
    * Created, managed issues: [Link](https://github.com/AY2425S1-CS2113-W13-1/tp/issues?q=is%3Aissue+involves%3Asukkaito+is%3Aclosed)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [[CS2113-W12-3] WheresMyMoney \#4](https://github.com/nus-cs2113-AY2425S1/tp/pull/4#pullrequestreview-2403656932)
    * Contributed to forum discussions ([1](https://github.com/nus-cs2113-AY2425S1/forum/issues/3#issuecomment-2306188636))

* **Tools**:
    * Integrated [JUnit Jupiter Params](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params) to the project to make use of [ParameterizedTest](https://junit.org/junit5/docs/current/api/org.junit.jupiter.params/org/junit/jupiter/params/ParameterizedTest.html) ([\#53](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/53))
