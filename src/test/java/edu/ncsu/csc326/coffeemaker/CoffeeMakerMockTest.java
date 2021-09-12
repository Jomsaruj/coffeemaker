package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CoffeeMaker class with stub recipeBook.
 *
 * @author Saruj Sattayanurak
 */
public class CoffeeMakerMockTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;
    private Inventory inventory;
    private RecipeBook recipeBook;
    private Recipe [] recipeArray;

    /**
     * Number of recipes in coffee maker
     */
    private final int NUM_RECIPES = 4;

    /**
     * Sample recipes to use in testing.
     */
    private Recipe recipe1, recipe2;

    /**
     * Method to create Recipe instance
     * @param name name of recipe
     * @param chocolate amount of chocolate for recipe
     * @param coffee amount of coffee for recipe
     * @param milk amount of milk for the recipe
     * @param sugar amount of sugar for the recipe
     * @param price price of recipe
     * @return Recipe instance
     * @throws RecipeException
     */
    private Recipe createRecipe(String name, String chocolate, String coffee, String milk, String sugar, String price) throws RecipeException{
        Recipe r = Mockito.spy(new Recipe());
        r.setName(name);
        r.setAmtChocolate(chocolate);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setPrice(price);
        return r;
    }

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException{
        recipeBook = Mockito.mock(RecipeBook.class); // mock a concrete class
        inventory = Mockito.spy(new Inventory());
        coffeeMaker = new CoffeeMaker(recipeBook, inventory);
        recipeArray = new Recipe[NUM_RECIPES];

        //Set up for recipe1
        recipe1 = createRecipe("Coffee", "0","3","1","1","50");

        //Set up for recipe2
        recipe2 = createRecipe("Extra", "16","16","16","16","200");

        //Add recipes to array
        recipeArray[0] = recipe1;
        recipeArray[1] = recipe2;
        recipeArray[2] = null;
    }

    /**
     * Test Case ID: 61
     * Given a coffee maker with the default inventory
     * When we purchase beverage and select check inventory
     * Then we get list of all inventories with it quantity - used inventory
     *
     */
    @Test
    public void checkInventoryAfterPurchasedMock(){

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        coffeeMaker.makeCoffee(0,50);
        assertEquals("Coffee: " + "12" + "\n" + "Milk: " + "14" + "\n" + "Sugar: " + "14" + "\n"
                + "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());

        // Mock object
        // purchase successfully call method getRecipe() 4 times
        verify(recipeBook, times(4)).getRecipes();

        // Spy object
        // purchase successfully call method useIngredients 1 time only and other get() method twice
        verify(inventory, times(1)).useIngredients(any(Recipe.class));
        verify(recipe1, times(2)).getPrice();
        verify(recipe1, times(2)).getAmtChocolate();
        verify(recipe1, times(2)).getAmtCoffee();
        verify(recipe1, times(2)).getAmtMilk();
        verify(recipe1, times(2)).getAmtSugar();

        // order verification: check whether user paid enough money or not before start making a beverage
        InOrder inOrder = inOrder(recipe1, inventory);
        inOrder.verify(recipe1).getPrice(); // called first
        inOrder.verify(inventory).useIngredients(recipe1); // called second
    }

    /**
     * Test Case ID: 62
     * Given a coffee with 50 units price
     * When we purchase beverage with 60 units
     * Then we get return change at 10 units
     *
     */
    @Test
    public void returnChangeAfterPurchasedMock() {

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        assertEquals(10, coffeeMaker.makeCoffee(0,60));

        // Mock object
        // purchase successfully call method getRecipe() 4 times
        verify(recipeBook, times(4)).getRecipes();

        // Spy object
        // purchase successfully call method useIngredients 1 time only and other get() method twice
        verify(inventory, times(1)).useIngredients(any(Recipe.class));
        verify(recipe1, times(2)).getPrice();
        verify(recipe1, times(2)).getAmtChocolate();
        verify(recipe1, times(2)).getAmtCoffee();
        verify(recipe1, times(2)).getAmtMilk();
        verify(recipe1, times(2)).getAmtSugar();

        // order verification: check whether user paid enough money or not before start making a beverage
        // and getPrice() again to calculate return change
        InOrder inOrder = inOrder(recipe1, inventory);
        inOrder.verify(recipe1).getPrice(); // called first
        inOrder.verify(inventory).useIngredients(recipe1); // called second
        inOrder.verify(recipe1).getPrice(); // called third
    }

    /**
     * Test Case ID: 63
     * Given a coffee maker with the default inventory
     * When we purchase beverage and enter not enough money
     * Then we get list of all inventories that remain the same
     *
     */
    @Test
    public void checkInventoryAfterNotEnoughMoneyMock(){

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        coffeeMaker.makeCoffee(0,40);
        assertEquals("Coffee: " + "15" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
                + "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());

        // Mock object
        // purchase fail due to not enough money paid call method getRecipe() only 2 times
        verify(recipeBook, times(2)).getRecipes();

        // Spy object
        // purchase fail never call method useIngredients but only getPrice()
        verify(inventory, never()).useIngredients(any(Recipe.class));
        verify(recipe1, never()).getAmtChocolate();
        verify(recipe1, never()).getAmtCoffee();
        verify(recipe1, never()).getAmtMilk();
        verify(recipe1, never()).getAmtSugar();
        verify(recipe1, times(1)).getPrice();
    }

    /**
     * Test Case ID: 64
     * Given a coffee with 50 units price
     * When we purchase beverage with 40 units
     * Then we get return change 40 units
     *
     */
    @Test
    public void returnAfterNotEnoughMoneyMock() {

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        assertEquals(40, coffeeMaker.makeCoffee(0,40));

        // Mock object
        // purchase fail due to not enough money paid call method getRecipe() only 2 times
        verify(recipeBook, times(2)).getRecipes();

        // Spy object
        // purchase fail never call method useIngredients but only getPrice()
        verify(inventory, never()).useIngredients(any(Recipe.class));
        verify(recipe1, never()).getAmtChocolate();
        verify(recipe1, never()).getAmtCoffee();
        verify(recipe1, never()).getAmtMilk();
        verify(recipe1, never()).getAmtSugar();
        verify(recipe1, times(1)).getPrice();
    }

    /**
     * Test Case ID: 65
     * Given a coffee maker with the default inventory
     * When we purchase beverage and coffee maker not have enough inventory
     * Then we get list of all inventories that remain the same
     *
     */
    @Test
    public void checkInventoryAfterNotEnoughInventoryMock(){

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        coffeeMaker.makeCoffee(1,200);
        assertEquals("Coffee: " + "15" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
                + "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());

        // Mock object
        // purchase fail due to not enough inventory call method getRecipe() 3 times
        verify(recipeBook, times(3)).getRecipes();

        // Spy object
        // purchase fail due to not enough inventory call each method once
        verify(inventory, times(1)).useIngredients(any(Recipe.class));
        verify(recipe2, times(1)).getAmtChocolate();
        verify(recipe2, times(1)).getAmtCoffee();
        verify(recipe2, times(1)).getAmtMilk();
        verify(recipe2, times(1)).getAmtSugar();
        verify(recipe2, times(1)).getPrice();

        // order verification: check whether user paid enough money or not before check for inventory
        InOrder inOrder = inOrder(recipe2, inventory);
        inOrder.verify(recipe2).getPrice(); // called first
        inOrder.verify(inventory).useIngredients(recipe2); // called second

    }

    /**
     * Test Case ID: 66
     * Given a coffee maker with the default inventory
     * When we purchase beverage and coffee maker not have enough inventory
     * Then we get return change back
     *
     */
    @Test
    public void returnChangeAfterNotEnoughInventoryMock(){

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        assertEquals(300, coffeeMaker.makeCoffee(1,300));

        // Mock object
        // purchase fail due to not enough inventory call method getRecipe() 3 times
        verify(recipeBook, times(3)).getRecipes();

        // Spy object
        // purchase fail due to not enough inventory call each method once
        verify(inventory, times(1)).useIngredients(any(Recipe.class));
        verify(recipe2, times(1)).getAmtChocolate();
        verify(recipe2, times(1)).getAmtCoffee();
        verify(recipe2, times(1)).getAmtMilk();
        verify(recipe2, times(1)).getAmtSugar();
        verify(recipe2, times(1)).getPrice();

        // order verification: check whether user paid enough money or not before check for inventory
        InOrder inOrder = inOrder(recipe2, inventory);
        inOrder.verify(recipe2).getPrice(); // called first
        inOrder.verify(inventory).useIngredients(recipe2); // called second


    }

    /**
     * Test Case ID: 67
     * Given a coffee maker without any recipe in the system
     * When purchase un-exist recipe
     * Then purchase must fail and return full amount of money
     */
    @Test
    public void purchaseEmptyRecipeMock() {

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        assertEquals(300, coffeeMaker.makeCoffee(2,300));

        // Mock object
        // purchase null recipe call method getRecipe() only 1 time
        verify(recipeBook, times(1)).getRecipes();

        // Spy object
        // purchase null recipe never call useIngredients()
        verify(inventory, never()).useIngredients(any(Recipe.class));
    }

    /**
     * Test Case ID: 68
     * Given a coffee maker without default inventory
     * When purchase 2 cups of coffee
     * Then inventory must decrease correctly
     */
    @Test
    public void purchaseTwoBeverageMock() {

        // Test Stub
        when(recipeBook.getRecipes()).thenReturn(recipeArray);

        int i = 0;
        while (i < 2){
            coffeeMaker.makeCoffee(0, 50);
            i++;
        }
        assertEquals("Coffee: " + "9" + "\n" + "Milk: " + "13" + "\n" + "Sugar: " + "13" + "\n"
                + "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());

        // Mock object
        // purchase successfully call method getRecipe() 4 times per round
        verify(recipeBook, times(8)).getRecipes();

        // Spy object
        // purchase successfully call method useIngredients 1 time only and other get() method twice per round
        verify(inventory, times(2)).useIngredients(any(Recipe.class));
        verify(recipe1, times(4)).getPrice();
        verify(recipe1, times(4)).getAmtChocolate();
        verify(recipe1, times(4)).getAmtCoffee();
        verify(recipe1, times(4)).getAmtMilk();
        verify(recipe1, times(4)).getAmtSugar();

        // order verification: check whether user paid enough money or not before start making a beverage
        // ana call getPrice() again to calculate return change for each round
        InOrder inOrder = inOrder(recipe1, inventory);

        inOrder.verify(recipe1).getPrice(); // called first
        inOrder.verify(inventory).useIngredients(recipe1); // called second
        inOrder.verify(recipe1, times(2)).getPrice(); // called third
        inOrder.verify(inventory).useIngredients(recipe1); // called fourth
        inOrder.verify(recipe1).getPrice(); // called fifth

    }
}
