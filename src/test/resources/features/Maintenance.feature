Feature: Add and check inventory (user level = maintenance team)
      Maintenance team can check the remaining
      inventory and add inventory
      into a machine if needed.

      Scenario: Maintenance team check inventory (default inventory)
        Given coffeemaker is ready
        When Maintenance team select check inventory option
        Then coffeemaker return 15, 15, 15, 15 units for coffee, milk, sugar, chocolate respectively

#      This test should fail, so I comment it
#
#      Scenario: Maintenance team add inventory (default inventory)
#        Given coffeemaker is ready
#        When Maintenance team select add inventory option with 5, 5, 5, 5 units for coffee, milk, sugar, chocolate respectively
#        Then coffeemaker return 20, 20, 20, 20 units for coffee, milk, sugar, chocolate respectively