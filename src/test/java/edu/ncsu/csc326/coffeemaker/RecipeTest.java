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
    private Recipe recipeDuplicate1;
    private Recipe recipeNotDuplicate1;
    private Recipe recipeDuplicate2;

    /**
     * Initializes some recipes to test with and the {@link Recipe}
     * object we wish to test.
     *
     */
    @Before
    public void setUp() throws RecipeException{

        recipe = new Recipe();

        recipeDuplicate1 = new Recipe();
        recipeDuplicate1.setName("Coffee");
        recipeDuplicate1.setAmtChocolate("0");
        recipeDuplicate1.setAmtCoffee("3");
        recipeDuplicate1.setAmtMilk("1");
        recipeDuplicate1.setAmtSugar("1");
        recipeDuplicate1.setPrice("50");

        recipeNotDuplicate1 = new Recipe();
        recipeNotDuplicate1.setName("Special Coffee");
        recipeNotDuplicate1.setAmtChocolate("0");
        recipeNotDuplicate1.setAmtCoffee("3");
        recipeNotDuplicate1.setAmtMilk("1");
        recipeNotDuplicate1.setAmtSugar("1");
        recipeNotDuplicate1.setPrice("50");

        recipeDuplicate2 = new Recipe();
        recipeDuplicate2.setName("Coffee");
        recipeDuplicate2.setAmtChocolate("0");
        recipeDuplicate2.setAmtCoffee("3");
        recipeDuplicate2.setAmtMilk("1");
        recipeDuplicate2.setAmtSugar("1");
        recipeDuplicate2.setPrice("50");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
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
     * Given 2 not duplicate name recipe
     * When we state that 2 not duplicate name recipe are equal
     * Then the method should return false
     *
     */
    @Test
    public void testNotEquals() {
        assertFalse(recipeDuplicate1.equals(recipeNotDuplicate1));
    }
}
