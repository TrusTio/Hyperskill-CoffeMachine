package machine;

import java.util.Scanner;

// Completed up to stage 2/7 of https://hyperskill.org/projects/33/stages/176/implement
public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCups = 0;

        System.out.println("Write how many cups of coffee you will need: ");
        numberOfCups = scanner.nextInt();
        Order order = new Order(numberOfCups);

        System.out.printf("""
                        For %d cups of coffee you will need:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans""",
                order.numberOfCups(), order.getTotalWater(), order.getTotalMilk(), order.getTotalBeans());

    }
}

/**
 * enum to hold the coffee ingredients, will be easier to add more types later on if necessary.
 */
enum Coffee {
    BASIC_COFFEE(200, 50, 15);

    public final int water;   // in ml
    public final int milk;    // in ml
    public final int beans;   // in grams

    Coffee(int water, int milk, int beans) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
    }
}

/**
 * Recording class for the orders, calculates totals.
 *
 * @param numberOfCups
 */
record Order(int numberOfCups) {

    public int getTotalWater() {
        return Coffee.BASIC_COFFEE.water * numberOfCups;
    }

    public int getTotalMilk() {
        return Coffee.BASIC_COFFEE.milk * numberOfCups;
    }

    public int getTotalBeans() {
        return Coffee.BASIC_COFFEE.beans * numberOfCups;
    }

}