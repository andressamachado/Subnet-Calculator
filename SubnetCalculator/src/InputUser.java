import java.util.Scanner;
import java.util.regex.Pattern;

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
 * The purpose of this class is to receive the user input and check for the validation of that input. So it can be
 * passed to the subnetCalculator class as a parameter in the SubnetCalculatorMain class which contains the main.
 */
public class InputUser extends SubnetCalculator {
    /**
     * Used to obtain the input of the primitive type int or String in this case.
     */
    private static Scanner input;
    /**
     * Holds the value for the subnet mask.
     */
    private String subnetMask;
    /**
     * Holds the value for the IPv4.
     */
    private String ipv4;
    /**
     * Array to hold the IPv4 in a way to separate the octets by the indexes.
     */
    private int[] arrayIPv4;
    /**
     * Array to hold the subnet mask in a way to separate the octets by the indexes.
     */
    private int[] arraySubnetMask;

    /**
     * Constructor to construct a input object and initialize the global variables.
     */
    public InputUser() {
        this.input = new Scanner(System.in);
        this.subnetMask = "";
        this.ipv4 = "";
        this.arrayIPv4 = new int[7];
        this.arraySubnetMask = new int[7];

        // message to be displayed every time a new user object is created.
        System.out.println("********** Welcome to the IP Subnet Calculator **********");
        System.out.println("This program returns a good range of values using your Internet Protocol version 4(Ipv4)");
        System.out.println();
    }

    /**
     * The purpose of this method is to prompt the user for the values to be entered. IPv4 and Subnet Mask.
     */
    protected void readFromUser() {

        // do/while loop for verification. Just goes ot of the loop when the user input a valid value.
        do {
            System.out.println("Enter your IPv4 address: ");
            System.out.println("* please, include dots between different sections");

            ipv4 = input.nextLine();
        } while (!validate(ipv4));

        arrayIPv4 = convertToIntArray(ipv4);

        do {
            System.out.println("Enter your Subnet Mask: ");
            System.out.println("* please, include dots between different sections");

            subnetMask = input.nextLine();
        } while (!validate(subnetMask));

        arraySubnetMask = convertToIntArray(subnetMask);
    }

    /**
     * The purpose of this method is to validate the user input.
     *
     * @param value a String that is passed to be checked using Regular Expression.
     * @return true if the format is correct, otherwise return false.
     */
    private boolean validate(String value) {
        //check if the user entered any value. Cannot input a null.
        if (value.equals("")) {
            System.out.println("Please enter a value");
            return false;
        }

        // Regex format: should be a number between 0 to 255 and a dot to separate the octets.
        if (!Pattern.matches("(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])", value)) {
            System.out.println("Please type a valid format IP");
            return false;
        }
        return true;
    }

    /**
     * The purpose of this method is to give access to the ipv4 value.
     *
     * @return string ipv4
     */
    public String getIpv4() {
        return ipv4;
    }

    /**
     * The purpose of this method is to give access to the subnetMask value.
     *
     * @return String subnetMask.
     */
    public String getSubnetMask() {
        return subnetMask;
    }
}