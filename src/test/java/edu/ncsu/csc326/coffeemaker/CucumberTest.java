package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class CucumberTest{

    private CoffeeMaker coffeeMaker;
    private Integer selectedRecipe;
    private Integer amtPaid;
    private String inventoryReport;
    private Recipe secret;

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

    @Given("coffeemaker is ready")
    public void coffeemakerIsReady() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        Recipe recipe1 = createRecipe("Black coffee", "0","3","1","1","50");
        Recipe recipe2 = createRecipe("Ice chocolate", "8","0","2","3","35");
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
    }

    @When("Customer select recipe number {int}")
    public void customerSelectRecipeNumber(int recipeNumber) {
        selectedRecipe = recipeNumber - 1;
    }

    @When("Customer deposit {int} Bath")
    public void customerDepositBath(int amt) {
        amtPaid = amt;
    }

    @Then("After purchased coffeemaker return to customer {int} Bath")
    public void afterPurchasedCoffeemakerReturnToCustomerBath(int amtReturn) {
        int returnChange = coffeeMaker.makeCoffee(selectedRecipe,amtPaid);
        assertEquals(amtReturn, returnChange);
    }

    @When("Maintenance team select check inventory option")
    public void maintenanceTeamSelectCheckInventoryOption() {
        inventoryReport = coffeeMaker.checkInventory();
    }

    @Then("coffeemaker return {int}, {int}, {int}, {int} units for coffee, milk, sugar, chocolate respectively")
    public void coffeemakerReturnUnitsForCoffeeMilkChocolateSugarRespectively(int coffee, int milk, int sugar, int chocolate) {
        assertEquals("Coffee: " + coffee + "\n" + "Milk: " + milk + "\n" + "Sugar: " + sugar + "\n"
                + "Chocolate: " + chocolate + "\n", inventoryReport);
    }

    @When("Maintenance team select add inventory option with {int}, {int}, {int}, {int} units for coffee, milk, sugar, chocolate respectively")
    public void maintenanceTeamSelectAddInventoryOptionWithUnitsForCoffeeMilkChocolateSugarRespectively(int coffee, int milk, int sugar, int chocolate)
    throws InventoryException {
        coffeeMaker.addInventory(Integer.toString(coffee),Integer.toString(milk),Integer.toString(sugar),Integer.toString(chocolate));
    }

    @When("Create secret recipe named {word}")
    public void baristaAddRecipeCalledSecretToCoffeemaker(String recipeName) throws RecipeException {
        secret = createRecipe(recipeName, "2","2","1","5", "100");
    }

    @Then("Barista add secret recipe and coffeemaker return true")
    public void coffeemakerReturnTrue() {
        assertTrue(coffeeMaker.addRecipe(secret));
    }

    @Then("Barista add secret recipe and coffeemaker return false")
    public void coffeemakerReturnFalse() {
        assertFalse(coffeeMaker.addRecipe(secret));
    }

    @When("Barista set all inventories and price for recipe {int} to {int}")
    public void baristaSetUnitForCoffeeMilkChocolateSugarRespectivelyAndForPrice(int recipeToEdit, int numberToSet) throws RecipeException {
        Recipe temp = createRecipe("temp",Integer.toString(numberToSet),Integer.toString(numberToSet),Integer.toString(numberToSet),Integer.toString(numberToSet),Integer.toString(numberToSet));
        coffeeMaker.editRecipe(recipeToEdit - 1,temp);
    }

    @Then("recipe {int} wil return {int}, {int}, {int}, {int} unit for coffee, milk, chocolate, sugar respectively and {int} for price")
    public void recipeWilReturnUnitForCoffeeMilkChocolateSugarRespectivelyAndForPrice(int recipeNum, int coffee, int milk, int chocolate, int sugar, int price) {
        assertEquals(coffee, coffeeMaker.getRecipes()[recipeNum-1].getAmtCoffee());
        assertEquals(milk, coffeeMaker.getRecipes()[recipeNum-1].getAmtMilk());
        assertEquals(chocolate, coffeeMaker.getRecipes()[recipeNum-1].getAmtChocolate());
        assertEquals(sugar, coffeeMaker.getRecipes()[recipeNum-1].getAmtSugar());
        assertEquals(price, coffeeMaker.getRecipes()[recipeNum-1].getPrice());
    }
}
