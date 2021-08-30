/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 * modify by Saruj Sattayanurak
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe1Copy;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException{
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for r1Copy
		//r1Copy is a recipe that have same recipe's name as r1
		//but other properties are different
		recipe1Copy = new Recipe();
		recipe1Copy.setName("Coffee");
		recipe1Copy.setAmtChocolate("1");
		recipe1Copy.setAmtCoffee("1");
		recipe1Copy.setAmtMilk("2");
		recipe1Copy.setAmtSugar("2");
		recipe1Copy.setPrice("10");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

		//Set up for r5
		recipe5 = new Recipe();
		recipe5.setName("Extra");
		recipe5.setAmtChocolate("16");
		recipe5.setAmtCoffee("16");
		recipe5.setAmtMilk("16");
		recipe5.setAmtSugar("16");
		recipe5.setPrice("200");
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Given a coffee maker with new recipes
	 * When we add 1 valid recipe
	 * Then the recipe will be valid to added to the system
	 */
	@Test
	public void testAddSingleValidRecipe(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add 3 recipes
	 * Then all recipe will be added to the system
	 */
	@Test
	public void testAddThreeValidRecipe(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertTrue(coffeeMaker.addRecipe(recipe2));
		assertTrue(coffeeMaker.addRecipe(recipe3));
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add 4 recipes
	 * Then the fourth recipe will not be added to the system
	 */
	@Test
	public void testAddTooMuchValidRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add a new recipe with the name that already exists in the system
	 * Then the recipe will not be added to the system
	 */
	@Test
	public void testAddDuplicateNameRecipe(){
		coffeeMaker.addRecipe(recipe1);
		// recipe1Copy is a recipe that have same recipe's name as recipe1 ("Coffee")
		// but other properties are different
		assertFalse(coffeeMaker.addRecipe(recipe1Copy));
	}

	/**
	 * Given a coffee maker with recipe number to delete
	 * When we pick a valid recipe number
	 * Then the program should return name of deleted recipe
	 */
	@Test
	public void testDeleteValidRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals("Coffee", coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Given a coffee maker with recipe number to delete
	 * When we pick an empty slot number
	 * Then the program should return null
	 */
	@Test
	public void testDeleteEmptySlotRecipe(){
		assertEquals(null, coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Given a coffee maker with only 1 recipe
	 * When we delete the recipe that slot should be empty
	 * Then if we delete that slot again the program should return null
	 */
	@Test
	public void testDeleteDeletedRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		assertEquals(null, coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Given a coffee maker with recipe number to edit
	 * When we pick a valid recipe number
	 * Then the program should return recipe name
	 */
	@Test
	public void testEditValidRecipe() throws RecipeException{
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		Recipe newRecipe = new Recipe();
		newRecipe.setName("Latte");
		newRecipe.setAmtChocolate("20");
		newRecipe.setAmtCoffee("30");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("500");
		assertEquals(coffeeMaker.editRecipe(2, newRecipe ), "Latte");
	}

	/**
	 * Given a coffee maker with recipe number to edit
	 * When we pick an empty recipe number
	 * Then the program should return null
	 */
	@Test
	public void testEditEmptyRecipe() throws RecipeException{
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		Recipe newRecipe = new Recipe();
		newRecipe.setName("Latte");
		newRecipe.setAmtChocolate("20");
		newRecipe.setAmtCoffee("30");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("500");
		assertNull(coffeeMaker.editRecipe(2, newRecipe ));
	}

	/**
	 * Given a coffee maker with recipe number to edit
	 * When we pick a valid recipe number and change recipe name
	 * Then the program should not change it
	 */
	@Test
	public void testEditRecipeName() throws RecipeException{
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		Recipe newRecipe = new Recipe();
		newRecipe.setName("New Latte");
		newRecipe.setAmtChocolate("20");
		newRecipe.setAmtCoffee("30");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("500");
		coffeeMaker.editRecipe(2, newRecipe );
		assertEquals("Latte", coffeeMaker.getRecipes()[2].getName());
	}

	/**
	 * Given a coffee maker with 1 recipe
	 * When we delete the recipe
	 * Then we will not be able to edit it
	 */
	@Test
	public void testEditAfterDelete() throws RecipeException{
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		Recipe newRecipe = new Recipe();
		newRecipe.setName("New Latte");
		newRecipe.setAmtChocolate("20");
		newRecipe.setAmtCoffee("30");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("500");
		assertNull(coffeeMaker.editRecipe(0, newRecipe ));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add coffee with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddValidCoffeeAmt() throws InventoryException {
		coffeeMaker.addInventory("5","0","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add milk with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddValidMilkAmt() throws InventoryException {
		coffeeMaker.addInventory("0","5","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add sugar with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddValidSugarAmt() throws InventoryException {
		coffeeMaker.addInventory("0","0","5","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add chocolate with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddValidChocolateAmt() throws InventoryException {
		coffeeMaker.addInventory("0","0","0","5");
	}

	/**
	 * Given a coffee maker with the default inventory (all 15)
	 * When we add coffee by 5
	 * Then 5 will add to 15 make it 20
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testInventoryIsAddToCurrent() throws InventoryException {
		coffeeMaker.addInventory("5","0","0","0");
		assertEquals("Coffee: " + "20" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
		+ "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add coffee with decimal-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddDecimalCoffeeAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of coffee must be a positive integer");
		coffeeMaker.addInventory("5.5","0","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add milk with decimal-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddDecimalMilkAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of milk must be a positive integer");
		coffeeMaker.addInventory("0","5.5","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add sugar with decimal-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddDecimalSugarAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of sugar must be a positive integer");
		coffeeMaker.addInventory("0","0","5.5","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add chocolate with decimal-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddDecimalChocolateAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of chocolate must be a positive integer");
		coffeeMaker.addInventory("0","0","0","5.5");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add coffee with negative-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddNegativeCoffeeAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of coffee must be a positive integer");
		coffeeMaker.addInventory("-5","0","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add milk with negative-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddNegativeMilkAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of milk must be a positive integer");
		coffeeMaker.addInventory("0","-5","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add sugar with negative-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddNegativeSugarAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of sugar must be a positive integer");
		coffeeMaker.addInventory("0","0","-5","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add chocolate with negative-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddNegativeChocolateAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of chocolate must be a positive integer");
		coffeeMaker.addInventory("0","0","0","-5");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add coffee with alphabetic character-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddAlphabeticCoffeeAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of coffee must be a positive integer");
		coffeeMaker.addInventory("five","0","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add milk with alphabetic character-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddAlphabeticMilkAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of milk must be a positive integer");
		coffeeMaker.addInventory("0","five","0","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add sugar with alphabetic character-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddAlphabeticSugarAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of sugar must be a positive integer");
		coffeeMaker.addInventory("0","0","five","0");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add chocolate with alphabetic character-formed quantities
	 * Then we do get an InventoryException
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddAlphabeticChocolateAmt() throws InventoryException {
		expectedException.expect(InventoryException.class);
		expectedException.expectMessage("Units of chocolate must be a positive integer");
		coffeeMaker.addInventory("0","0","0","five");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we select check inventory
	 * Then we get list of all inventories with it quantity
	 *
	 */
	@Test
	public void testCheckInventoryFormat() {
		assertEquals("Coffee: " + "15" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
				+ "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory by 5 and select check inventory
	 * Then we get list of all inventories with it default quantity + 5
	 *
	 */
	@Test
	public void testCheckInventoryAfterAdd() throws InventoryException {
		coffeeMaker.addInventory("5","5","5","5");
		assertEquals("Coffee: " + "20" + "\n" + "Milk: " + "20" + "\n" + "Sugar: " + "20" + "\n"
				+ "Chocolate: " + "20" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we purchase beverage and select check inventory
	 * Then we get list of all inventories with it quantity - used inventory
	 *
	 */
	@Test
	public void testCheckInventoryAfterPurchased(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0,50);
		assertEquals("Coffee: " + "12" + "\n" + "Milk: " + "14" + "\n" + "Sugar: " + "14" + "\n"
				+ "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee with 50 units price
	 * When we purchase beverage with 60 units
	 * Then we get return change at 10 units
	 *
	 */
	@Test
	public void testReturnChangeAfterPurchased() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(10, coffeeMaker.makeCoffee(0,60));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we purchase beverage and enter not enough money
	 * Then we get list of all inventories that remain the same
	 *
	 */
	@Test
	public void testCheckInventoryAfterNotEnoughMoney(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0,40);
		assertEquals("Coffee: " + "15" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
				+ "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee with 50 units price
	 * When we purchase beverage with 40 units
	 * Then we get return change 40 units
	 *
	 */
	@Test
	public void testReturnChangeAfterNotEnoughMoney() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(40, coffeeMaker.makeCoffee(0,40));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we purchase beverage and coffee maker not have enough inventory
	 * Then we get list of all inventories that remain the same
	 *
	 */
	@Test
	public void testCheckInventoryAfterNotEnoughInventory(){
		coffeeMaker.addRecipe(recipe5);
		coffeeMaker.makeCoffee(0,200);
		assertEquals("Coffee: " + "15" + "\n" + "Milk: " + "15" + "\n" + "Sugar: " + "15" + "\n"
				+ "Chocolate: " + "15" + "\n", coffeeMaker.checkInventory());
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we purchase beverage and coffee maker not have enough inventory
	 * Then we get return change back
	 *
	 */
	@Test
	public void testReturnChangeAfterNotEnoughInventory(){
		coffeeMaker.addRecipe(recipe5);
		assertEquals(300, coffeeMaker.makeCoffee(0,300));
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add 1 valid recipe
	 * Then the recipe will be saved in recipe book
	 */
	@Test
	public void testGetRecipeAfterAddSingleValidRecipe(){
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1.getName(), coffeeMaker.getRecipes()[0].getName());
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add 4 valid recipe
	 * Then the fourth recipe will not be saved in recipe book
	 */
	@Test
	public void testGetRecipeAfterAddTooMuchRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4);
		assertEquals(3, coffeeMaker.getRecipes().length);
	}

	/**
	 * Given a coffee maker with new recipes
	 * When we add recipe which have duplicate name
	 * Then the new recipe will not be added to recipe book
	 */
	@Test
	public void testGetRecipeAfterAddDuplicateRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe1Copy);
		assertEquals(null, coffeeMaker.getRecipes()[1]);
	}

	/**
	 * Given a coffee maker 1 recipe
	 * When we delete the recipe and call method getRecipe
	 * Then the program should return null
	 */
	@Test
	public void testGetRecipeAfterDeleteRecipe(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		assertEquals(null, coffeeMaker.getRecipes()[0]);
	}

	/**
	 * Given a coffee maker with recipe number to edit
	 * When we pick a valid recipe number
	 * Then the program should return recipe name
	 */
	@Test
	public void testGetRecipeAfterEditRecipe() throws RecipeException{
		coffeeMaker.addRecipe(recipe1);// name = "Coffee"
		Recipe newRecipe = new Recipe();
		newRecipe.setName("Latte");
		newRecipe.setAmtChocolate("20");
		newRecipe.setAmtCoffee("30");
		newRecipe.setAmtMilk("10");
		newRecipe.setAmtSugar("10");
		newRecipe.setPrice("500");
		coffeeMaker.editRecipe(0, newRecipe);
		// omit recipe name -> see test case id
		assertEquals(20, coffeeMaker.getRecipes()[0].getAmtChocolate());
		assertEquals(30, coffeeMaker.getRecipes()[0].getAmtCoffee());
		assertEquals(10, coffeeMaker.getRecipes()[0].getAmtMilk());
		assertEquals(10, coffeeMaker.getRecipes()[0].getAmtSugar());
		assertEquals(500, coffeeMaker.getRecipes()[0].getPrice());
	}
}
