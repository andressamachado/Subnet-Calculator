/*
 * Name: Andressa Machado
 * Student ID: 040923007
 * Network Programming Basics
 * Course & Section: CST8108 012
 * Professor: Risvan Coskun
 * Bonus Activity - Subnet Calculator
 * Date: December 07, 2018
 */

/**
 * This class contains the main method.
 */
public class SubnetCalculatorMain {

    /**
     * Method main to run the project. It creates a new SubnetCalculator object and a new InputUser object.
     * Call the readFromUser method to prompt the user for values and the printResults method to show the respective results.
     *
     * @param args String array of arguments
     */
    public static void main(String[] args) {
        SubnetCalculator calculator = new SubnetCalculator();
        InputUser user = new InputUser();

        user.readFromUser();
        calculator.printResults(user.getIpv4(), user.getSubnetMask());
    }
}

