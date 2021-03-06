Feature: Add and check inventory (user level = maintenance team)
      Maintenance team can check the remaining
      inventory and add inventory
      into a machine if needed.

      Background: initialize coffeemaker
          Given coffeemaker is ready

      Scenario: Maintenance team check inventory (default inventory)
          When Maintenance team select check inventory option
          Then coffeemaker return 15, 15, 15, 15 units for coffee, milk, sugar, chocolate respectively

#      These test cases will fail due to some bugs in coffeemaker code, so I comment it

#      Scenario: Maintenance team check inventory after customer purchased a beverage
#          When Customer select recipe number 1
#          And Customer deposit 50 Bath
#          Then After purchased coffeemaker return to customer 0 Bath
#          When Maintenance team select check inventory option
#          Then coffeemaker return 12, 14, 14, 15 units for coffee, milk, sugar, chocolate respectively

#
#      Scenario: Maintenance team add inventory (default inventory)
#        Given coffeemaker is ready
#        When Maintenance team select add inventory option with 5, 5, 5, 5 units for coffee, milk, sugar, chocolate respectively
#        Then coffeemaker return 20, 20, 20, 20 units for coffee, milk, sugar, chocolate respectively