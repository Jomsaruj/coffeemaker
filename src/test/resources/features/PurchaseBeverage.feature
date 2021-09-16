Feature: Purchase a beverage (user level = customer)
	    Customer can select a recipe and deposit money
	    into Coffeemaker.If there are any extra money,
	    Coffeemaker will return it back to user.

        Coffeemaker Menu
        ===============================================
        recipe1: Black coffee = 50 Bath
        recipe2: Ice chocolate = 35 Bath
        ===============================================

        Scenario: Buy a beverage that cost 50 Bath from coffeemaker (exact change)
            Given coffeemaker is ready
            When Customer select recipe number 1
            And Customer deposit 50 Bath
            Then After purchased coffeemaker return to customer 0 Bath

        Scenario: Buy a beverage that cost 35 Bath from coffeemaker (overpay)
            Given coffeemaker is ready
            When Customer select recipe number 2
            And Customer deposit 50 Bath
            Then After purchased coffeemaker return to customer 15 Bath

        Scenario: Buy a beverage that cost 35 Bath from coffeemaker (not enough money paid)
            Given coffeemaker is ready
            When Customer select recipe number 2
            And Customer deposit 20 Bath
            Then After purchased coffeemaker return to customer 20 Bath

