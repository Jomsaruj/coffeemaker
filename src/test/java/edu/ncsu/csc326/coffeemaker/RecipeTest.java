package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Unit tests for Recipe class.
 *
 * @author Saruj Sattayanurak
 */

public class RecipeTest {

    /**
     * The object under test.
     */
    private Recipe recipe;
    private Recipe recipeNullName;
    private Recipe recipeDuplicate1;
    private Recipe recipeNotDuplicate1;
    private Recipe recipeDuplicate2;
    private CoffeeMaker coffeemaker;

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
        Recipe r = new Recipe();
        r.setName(name);
        r.setAmtChocolate(chocolate);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setPrice(price);
        return r;
    }

    /**
     * Initializes some recipes to test with and the {@link Recipe}
     * object we wish to test.
     *
     */
    @Before
    public void setUp() throws RecipeException{

        recipe = new Recipe();
        coffeemaker= new CoffeeMaker();

        recipeDuplicate1 = createRecipe("Coffee", "0","3","1","1","50");
        recipeNotDuplicate1 = createRecipe("Special Coffee", "0","3","1","1","50");
        recipeDuplicate2 = createRecipe("Coffee", "0","3","1","1","50");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test Case ID: 42
     * Given a coffee maker with new recipes
     * When we create a new recipe with valid information
     * Then the recipe will be created and not throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputValidRecipe() throws RecipeException {
        recipe.setName("CPE Signature Coffee");
        recipe.setAmtChocolate("1");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("1");
        recipe.setPrice("50");
        assertEquals(recipe.getName(), "CPE Signature Coffee");
        assertEquals(recipe.getAmtChocolate(), 1);
        assertEquals(recipe.getAmtCoffee(), 3);
        assertEquals(recipe.getAmtSugar(), 1);
        assertEquals(recipe.getAmtMilk(), 2);
        assertEquals(recipe.getPrice(), 50);
    }

    /**
     * Test Case ID: 43
     * Given a coffee maker with new recipes
     * When we create a new recipe with invalid datatype for price
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputInvalidPrice() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Price must be a positive integer");
        recipe.setPrice("one");
    }

    /**
     * Test Case ID: 44
     * Given a coffee maker with new recipes
     * When we create a new recipe with negative price
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputNegativePrice() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Price must be a positive integer");
        recipe.setPrice("-1");
    }

    /**
     * Test Case ID: 45
     * Given a coffee maker with new recipes
     * When we create a new recipe with 0 price
     * Then the recipe will be saved and not throw RecipeException
     *
     */
    @Test
    public void testInputZeroPrice() throws RecipeException {
        // if not throw any exception, test will pass
        recipe.setPrice("0");
        assertEquals(0, recipe.getPrice());
    }

    /**
     * Test Case ID: 46
     * Given a coffee maker with new recipes
     * When we create a new recipe with invalid datatype for coffee
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputInvalidCoffee() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of coffee must be a positive integer");
        recipe.setAmtCoffee("one");
    }

    /**
     * Test Case ID: 47
     * Given a coffee maker with new recipes
     * When we create a new recipe with negative coffee
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputNegativeCoffee() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of coffee must be a positive integer");
        recipe.setAmtCoffee("-1");
    }

    /**
     * Test Case ID: 48
     * Given a coffee maker with new recipes
     * When we create a new recipe with invalid datatype for milk
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputInvalidMilk() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of milk must be a positive integer");
        recipe.setAmtMilk("one");
    }

    /**
     * Test Case ID: 49
     * Given a coffee maker with new recipes
     * When we create a new recipe with negative milk
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputNegativeMilk() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of milk must be a positive integer");
        recipe.setAmtMilk("-1");
    }

    /**
     * Test Case ID: 50
     * Given a coffee maker with new recipes
     * When we create a new recipe with invalid datatype for sugar
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputInvalidSugar() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of sugar must be a positive integer");
        recipe.setAmtSugar("one");
    }

    /**
     * Test Case ID: 51
     * Given a coffee maker with new recipes
     * When we create a new recipe with negative sugar
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputNegativeSugar() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of sugar must be a positive integer");
        recipe.setAmtSugar("-1");
    }

    /**
     * Test Case ID: 52
     * Given a coffee maker with new recipes
     * When we create a new recipe with invalid datatype for chocolate
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputInvalidChocolate() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of chocolate must be a positive integer");
        recipe.setAmtChocolate("one");
    }

    /**
     * Test Case ID: 53
     * Given a coffee maker with new recipes
     * When we create a new recipe with negative chocolate
     * Then the recipe will not be created and throw RecipeException
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Test
    public void testInputNegativeChocolate() throws RecipeException {
        expectedException.expect(RecipeException.class);
        expectedException.expectMessage("Units of chocolate must be a positive integer");
        recipe.setAmtChocolate("-1");
    }

    /**
     * Test Case ID: 54
     * Given 2 duplicate name recipe
     * When we state that 2 duplicate name recipe are equal
     * Then the method should return true
     *
     */
    @Test
    public void testEquals() {
        assertTrue(recipeDuplicate1.equals(recipeDuplicate2));
    }

    /**
     * Test Case ID: 55
     * Given 2 not duplicate name recipe
     * When we state that 2 not duplicate name recipe are equal
     * Then the method should return false
     *
     */
    @Test
    public void testNotEquals() {
        assertFalse(recipeDuplicate1.equals(recipeNotDuplicate1));
    }

    /**
     * Test Case ID: 58
     * Given 2 not duplicate recipe
     * When we state that 2 not duplicate recipe are equal
     * Then the method should return true
     *
     */
    @Test
    public void testCompareDuplicateObject() {
        assertTrue(recipeDuplicate1.equals(recipeDuplicate1));
    }

    /**
     * Test Case ID: 59
     * Given 1 Recipe's instance and CoffeeMaker's instance
     * When we state that 2 object are equal
     * Then the method should return false
     *
     */
    @Test
    public void testCompareDifferentObject() {
        assertFalse(recipeDuplicate1.equals(coffeemaker));
    }

    /**
     * Test Case ID: 60
     * Given 3 recipes
     * When we compare same recipe they should have same hasCode
     * Then if we compare 2 different recipe we should have different hasCode
     *
     */
    @Test
    public void testHasCode() {
        assertEquals(recipeDuplicate1.hashCode(),recipeDuplicate1.hashCode());
        assertNotEquals(recipeNotDuplicate1.hashCode(),recipeDuplicate2.hashCode());
    }
}
