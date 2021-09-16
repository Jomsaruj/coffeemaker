Feature: Add Delete Edit recipe (user level = barista)
  Barista can edit delete and add recipes.
  Coffeemaker will return True if added recipe's name
  is not duplicate.

  Scenario: Barista add secret recipe to coffeemaker
    Given coffeemaker is ready
    When Barista add recipe called secret to coffeemaker
    Then coffeemaker return true

  Scenario: Barista edit recipe 1
    Given coffeemaker is ready
    When Barista set all inventories and price for recipe 1 to 1
    Then recipe 1 wil return 1, 1, 1, 1 unit for coffee, milk, chocolate, sugar respectively and 1 for price

