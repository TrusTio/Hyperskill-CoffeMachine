package machine;

import java.util.Scanner;

/* Completed up to stage 3/7 of https://hyperskill.org/projects/33/stages/177/implement
Write a program that does the following:

1. Requests the amounts of water, milk, and coffee beans available at the moment, and then asks for the number of cups of coffee a user needs.
2. If the coffee machine has enough supplies to make the specified amount of coffee, the program should print "Yes, I can make that amount of coffee".
3. If the coffee machine can make more than the requested amount, the program should output "Yes, I can make that amount of coffee (and even N more than that)",
where N is the number of additional cups of coffee that the coffee machine can make.
4. If the available resources are insufficient to make the requested amount of coffee, the program should output "No, I can make only N cup(s) of coffee".

*/
public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCups = 0, water = 0, milk = 0, beans = 0;

        System.out.println("Write how many ml of water the coffee machine has: ");
        water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        beans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        numberOfCups = scanner.nextInt();

        Order order = new Order(numberOfCups);
        calculateResult(order, water, milk, beans);

    }
    // Calculates the result and prints it
    public static void calculateResult(Order order, int water, int milk, int beans) {
        int possibleCupsWater = water / Coffee.BASIC_COFFEE.water;
        int possibleCupsMilk = milk / Coffee.BASIC_COFFEE.milk;
        int possibleCupsBean = beans / Coffee.BASIC_COFFEE.beans;

        int possibleNumberOfCups = possibleCupsWater < possibleCupsMilk
                ? Math.min(possibleCupsWater, possibleCupsBean) : Math.min(possibleCupsMilk, possibleCupsBean);

        if (possibleNumberOfCups < order.numberOfCups()) {
            System.out.printf("No, I can only make %d cup(s) of coffee", possibleNumberOfCups);
        } else if (possibleNumberOfCups == order.numberOfCups()) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", possibleNumberOfCups - order.numberOfCups());
        }
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