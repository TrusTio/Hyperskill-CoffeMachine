package machine;

import java.util.Scanner;

/* Completed up to stage 5/6 of https://hyperskill.org/projects/33/stages/178/implement
 */
public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(400, 540, 120, 9, 550);


        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "buy" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String input = scanner.nextLine();
                    if (!input.equals("back")) {
                        System.out.println(machine.buyCoffee(Integer.parseInt(input)));
                    }
                }
                case "fill" -> {
                    int water, milk, beans, cups;
                    System.out.println("Write how many ml of water you want to add:");
                    water = Integer.parseInt(scanner.nextLine()); // so I don't have to call nextLine() each time I use nextInt() due to the leftover newline
                    System.out.println("Write how many ml of milk you want to add:");
                    milk = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    beans = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write how many disposable cups you want to add:");
                    cups = Integer.parseInt(scanner.nextLine());

                    machine.fillMachine(water, milk, beans, cups);
                }
                case "take" -> System.out.println("I gave you $" + machine.takeMoney() + "\n");
                case "remaining" -> machine.printInfo();
                case "exit" -> {
                    scanner.close();
                    System.exit(0);
                }
            }
        } while (true);


    }


}

/**
 * enum to hold the coffee ingredients, will be easier to add more types later on if necessary.
 */
enum Coffee {
    ESPRESSO(250, 0, 16, 4), LATTE(350, 75, 20, 7), CAPPUCCINO(200, 100, 12, 6);

    public final int water;   // in ml
    public final int milk;    // in ml
    public final int beans;   // in grams
    public final int price;

    Coffee(int water, int milk, int beans, int price) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.price = price;
    }
}

class Machine {
    private int water, milk, beans, cups, money;

    public Machine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    /**
     * Prints the current ingredients and money present in the machine.
     */
    public void printInfo() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                
                """, this.water, this.milk, this.beans, this.cups, this.money);
    }

    public String canBuyCoffee(Coffee coffee) {
        StringBuilder response = new StringBuilder();
        if (coffee.water > this.water) {
            response.append("water");
        }
        if (coffee.milk > this.milk) {
            response.append("milk");
        }
        if (coffee.beans > this.beans) {
            response.append("beans");
        }
        if (this.cups < 1) {
            response.append("cups");
        }
        if (response.isEmpty()) {
            return "I have enough resources, making you a coffee!";
        } else {
            return "Sorry, not enough " + response;
        }
    }

    /**
     * Subtracts the ingredients for the coffee and adds money to the machine
     *
     * @param coffeeType the type of coffee bought
     */
    public String buyCoffee(int coffeeType) {
        switch (coffeeType) {
            case 1 -> {
                String canBuyCoffeeResponse = canBuyCoffee(Coffee.ESPRESSO);
                if (canBuyCoffeeResponse.contains("have enough")) {
                    water -= Coffee.ESPRESSO.water;
                    milk -= Coffee.ESPRESSO.milk;
                    beans -= Coffee.ESPRESSO.beans;
                    cups--;
                    money += Coffee.ESPRESSO.price;
                }
                return canBuyCoffeeResponse;
            }
            case 2 -> {
                String canBuyCoffeeResponse = canBuyCoffee(Coffee.LATTE);
                if (canBuyCoffeeResponse.contains("have enough")) {
                    water -= Coffee.LATTE.water;
                    milk -= Coffee.LATTE.milk;
                    beans -= Coffee.LATTE.beans;
                    cups--;
                    money += Coffee.LATTE.price;
                }
                return canBuyCoffeeResponse;

            }
            case 3 -> {
                String canBuyCoffeeResponse = canBuyCoffee(Coffee.CAPPUCCINO);
                if (canBuyCoffeeResponse.contains("have enough")) {
                    water -= Coffee.CAPPUCCINO.water;
                    milk -= Coffee.CAPPUCCINO.milk;
                    beans -= Coffee.CAPPUCCINO.beans;
                    cups--;
                    money += Coffee.CAPPUCCINO.price;
                }
                return canBuyCoffeeResponse;
            }
        }
        return "Wrong coffee type.";
    }

    /**
     * Adds the amount if water, milk, beans, cups
     *
     * @param water water to be added
     * @param milk  milk to be added
     * @param beans beans to be added
     * @param cups  cups to be added
     */
    public void fillMachine(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    /**
     * Withdraws all the money and returns the amount withdrawn
     *
     * @return amount withdrawn
     */
    public int takeMoney() {
        int withdrawnAmount = this.money;
        this.money = 0;
        return withdrawnAmount;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}