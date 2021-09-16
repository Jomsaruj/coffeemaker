Feature: Add Delete Edit recipe (user level = barista)
        Barista can edit delete and add recipes.
        Coffeemaker will return True if added recipe's name
        is not duplicate.

        Background: initialize coffeemaker
            Given coffeemaker is ready

        Scenario: Barista add secret recipe to coffeemaker
            When Create secret recipe named DarkRecipe
            Then Barista add secret recipe and coffeemaker return true

        Scenario: Barista add secret recipe to coffeemaker 2 times
          When Create secret recipe named DarkRecipe
          Then Barista add secret recipe and coffeemaker return true
          When Create secret recipe named DarkRecipe
          Then Barista add secret recipe and coffeemaker return false

        Scenario: Barista edit recipe 1
            When Barista set all inventories and price for recipe 1 to 1
            Then recipe 1 wil return 1, 1, 1, 1 unit for coffee, milk, chocolate, sugar respectively and 1 for price
            When Barista set all inventories and price for recipe 1 to 20
            Then recipe 1 wil return 20, 20, 20, 20 unit for coffee, milk, chocolate, sugar respectively and 20 for price

