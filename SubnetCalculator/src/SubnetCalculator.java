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
 * The purpose of this class is to performs all the necessary operations to the calculation of the following values:
 * Network ID;
 * Broadcast ID;
 * Wildcard mask;
 * IPv4 class;
 * CIDR Notation;
 * Short notation;
 * Range of available IPv4 Addresses;
 * Total number of hosts.
 */
public class SubnetCalculator {

    /**
     * This method performs the conversion from String value to an array of ints
     *
     * @param stringValue A String to be converted to an array of ints.
     * @return the final array.
     */
    public static int[] convertToIntArray(String stringValue) {
        // Create a new array with the value of the parameter passed, splitting the string in the dots and putting inside
        // the indexes of the array.
        String[] arrayValue = stringValue.split("\\.");
        int[] returnValue = new int[arrayValue.length];

        //Putting the values of the string inside the array and changing it from String to integer.
        for (int i = 0; i < arrayValue.length; i++) {
            returnValue[i] = Integer.parseInt(arrayValue[i]);
        }

        return returnValue;
    }

    /**
     * Method to pass the array of integers to an array of String containing the binary values.
     *
     * @param integerList An array of integers to be used in the copy moment.
     * @return an array of String containing the binary representation of the array of integers.
     */
    public String getFormattedBinary(int[] integerList) {
        String[] binaryArray = new String[integerList.length];

        for (int i = 0; i < integerList.length; i++) {
            binaryArray[i] = normalizeOctet((Integer.toBinaryString(integerList[i])));
        }
        //concatenating using a dot to make it similar to an iv4 or subnet mask value.
        return String.join(".", binaryArray);
    }

    /**
     * Method created to pass the int values to String ones.
     *
     * @param integerList an array of integers as a reference to be copied
     * @return an String containing the the same array but as a string
     */
    public String getFormatted(int[] integerList) {
        String[] valueArray = new String[integerList.length];

        for (int i = 0; i < integerList.length; i++) {
            valueArray[i] = integerList[i] + "";
        }
        return String.join(".", valueArray);
    }

    /**
     * Method created to Method created to input the zeros in the correct space for readability.
     *
     * @param binary A String containing the binary value.
     * @return the String containing the correct format for the subnet or ipv4 in binaries.
     */
    public static String normalizeOctet(String binary) {
        // each octet has 8 -spaces- , minus the number of indexes in the array give us the number of zeros that needs
        // to be insert.
        int numZeros = 8 - binary.length();

        for (int i = 0; i < numZeros; i++) {
            binary = "0" + binary;
        }
        return binary;
    }

    /**
     * The purpose of this method is to calculate the network id for the given Ip and subnet mask.
     *
     * @param ip   an array of integers used in the calculation
     * @param mask an array of integers used in the calculation
     * @return an array of integer containing the network id.
     */
    public int[] network(int[] ip, int[] mask) {
        // holds the network id values
        int[] network = new int[ip.length];

        //Compare the the values for the ipv4 and the subnet mask using an AND operator.
        for (int i = 0; i < network.length; i++) {
            network[i] = ip[i] & mask[i];
        }
        return network;
    }

    /**
     * The purpose of this method is to  calculate the broadcast id.
     *
     * @param network    an array of integers that holds the values that make reference to the network id
     * @param subnetMask an array of integer that holds the values that make reference to the subnet mask.
     * @return an array of integers containing the values that make reference to the broadcast id.
     */
    public int[] broadcast(int[] network, int[] subnetMask) {
        // holds the value that represents the broadcast id
        int[] broadcast = new int[network.length];

        for (int i = 0; i < network.length; i++) {
            //If the subnetMask index equals to 255, then the value that is put inside the broadcast id is the same
            //Otherwise, it will take the difference between 255 and the network index to  be summed up with the network
            // id.
            if (subnetMask[i] == 255) {
                broadcast[i] = network[i];
            } else {
                int diff = 255 - network[i];
                broadcast[i] = network[i] + diff;
            }
        }
        return broadcast;
    }

    /**
     * The purpose of this method is to calculate the wildcard mask
     *
     * @param subnetMask an array of integers to be used in the calculation.
     * @return an array of integers containing the value representing the wildcard mask.
     */
    public int[] wildcardMask(int[] subnetMask) {
        //Holds the wildcard mask values.
        int[] wildcard = new int[subnetMask.length];

        // performs the calculation using the 255 - the subnetMask index to be assign inside the wildcard array.
        for (int i = 0; i < subnetMask.length; i++) {
            wildcard[i] = 255 - subnetMask[i];
        }

        return wildcard;
    }

    /**
     * The purpose of this method is to check to which class the IPv4 belongs to.
     *
     * @param firstOctet receives an integer containing the first octet of the ipv4
     * @return a String containing the specific class.
     */
    public static String ipClass(int firstOctet) {
        if (firstOctet > 0 && firstOctet <= 126) {
            return "Class A";
        }
        if (firstOctet >= 128 && firstOctet <= 191) {
            return "Class B";
        }
        if (firstOctet >= 192 && firstOctet <= 223) {
            return "Class C";
        }
        if (firstOctet >= 224 && firstOctet <= 239) {
            return "Class D \n*Reserved for Multicasting";
        }
        if (firstOctet >= 240 && firstOctet <= 254) {
            return "Class E \n*Experimental; used for research";
        }
        return "Class unknown";
    }

    /**
     * The purpose of this method is to calculate the slash notation for the subnet mask
     *
     * @param subnetMask an array of integers containing the subnetMask value to be used as a reference.
     * @return a String containing a slash "/" plus the number of 1`s in the array.
     */
    public String cidrNotation(int[] subnetMask) {
        String subnet = getFormattedBinary(subnetMask);
        int counter = 0;

        for (int i = 0; i < subnet.length(); i++) {
            if (subnet.charAt(i) == '1') {
                counter += 1;
            }
        }

        return "/" + counter;
    }

    /**
     * The purpose of this class is to calculate the default subnet mask based on the ipv4 class
     *
     * @param ipClass a String containing the information to which class the ip belongs to.
     * @return an array of ints containing the default subnet mask for that given class.
     */
    public int[] getDefaultMaskByClass(String ipClass) {
        //If checking to verify
        if (ipClass.equals("Class A")) {
            return new int[]{255, 0, 0, 0};
        }

        if (ipClass.equals("Class B")) {
            return new int[]{255, 255, 0, 0};
        }
        if (ipClass.equals("Class C")) {
            return new int[]{255, 255, 255, 0};
        }
        return new int[]{0, 0, 0, 0};
    }

    /**
     * The purpose of this method is to get the last full octet that is contained in the ip address.
     *
     * @param ip an array containing the ipv4 value.
     * @return
     */
    public int lastFullOctetPos(int[] ip) {
        int lastFullOctet = 0;

        //for loop to get the index that the last full octet is located
        for (int i = 0; i < ip.length; i++) {
            if (ip[i] == 255) {
                lastFullOctet = i;
            }
        }

        return lastFullOctet;
    }

    /**
     * The purpose of this method is to calculate the number of bits borrowed
     *
     * @param subnetMask an array of integers containing the subnet mask value
     * @return return an integer containing the number of bits borrowed
     */
    public int borrowedBits(int[] ip, int[] subnetMask) {
        String ipClass = ipClass(ip[0]);
        int[] defaultMask = getDefaultMaskByClass(ipClass);
        int lastFullOctet = lastFullOctetPos(defaultMask);
        int borrowed = 0;

        for (int i = lastFullOctet + 1; i < subnetMask.length; i++) {
            String octectAsBinary = Integer.toBinaryString(subnetMask[i]);
            for (int j = 0; j < octectAsBinary.length(); j++) {
                if (octectAsBinary.charAt(j) == '1') {
                    borrowed++;
                }
            }
        }

        return borrowed;
    }

    /**
     * The purpose of this method is to calculate the subnet number to which this host belongs to.
     *
     * @param ip         an array of integer that contain the value for the ip address
     * @param subnetMask an array of integer that contain the value for the subnet mask value
     * @return an integer containing the index that the ip  belongs to.
     */
    public int subnetIndex(int[] ip, int[] subnetMask) {
        //getting the ip class by looking to the index 0.
        String ipClass = ipClass(ip[0]);
        //getting the default subnet mask for the IP class
        int[] defaultMask = getDefaultMaskByClass(ipClass);
        //getting the network id for the values entered by the user.
        int[] network = network(ip, subnetMask);
        //getting the last full octet in the ip
        int lastFullOctet = lastFullOctetPos(defaultMask);
        //getting the number of borrowed bits
        int borrowed = borrowedBits(ip, subnetMask);
        //passing the network id to binary form and concatenating them taking out the dots and putting the octets together.
        String networkAsBinary = getFormattedBinary(network).replace(".", "");
        //Defining the start position to start counting by adding a 1 to starting count after the full octet multiplied by
        //8 because it is 8 bits.
        int subnetIndexStartPos = (lastFullOctet + 1) * 8;
        //creating variable to hold the 0 and 1 that is part of the portion that is been analysed.
        String piece = "";

        //for loop to pass through the array and concatenate the values in the portion that is been analysed.
        for (int i = subnetIndexStartPos; i < subnetIndexStartPos + borrowed; i++) {
            //getting the position in the string and concatenating
            piece += networkAsBinary.charAt(i);
        }

        if (borrowed == 0) {
            return 0;
        }
        //return the string that represents that result, calling the parseInt to make it integers and passing 2 to let
        //java knows it is a binary value.
        return Integer.parseInt(piece, 2);
    }

    /**
     * This method is to return a short notation for the values passed.
     * IPv4 address / subnet mask short notation.
     *
     * @param ip         a String containing the ipv4
     * @param subnetMask an array of integers containing the subnetMask.
     * @return a String containing the ip value / short notation for the subnet
     */
    public String shortNotation(String ip, int[] subnetMask) {

        String shortSubnet = cidrNotation(subnetMask);

        return ip + shortSubnet;
    }

    /**
     * This method is to calculate the ipv4 addresses available in the network.
     * This method does not contain the network nor the broadcast.
     *
     * @param network   an Array of integers containing the network id
     * @param broadcast an Array of integers containing the broadcast id
     * @return a String containing the addresses available (first and last hosts)
     */
    public String usableHostIPRange(int[] network, int[] broadcast) {
        //Holds the value for the fist host
        int[] firstHost = new int[network.length];
        //Holds the value for the last host
        int[] lastHost = new int[broadcast.length];

        //Copy the network array into the fistHost array
        System.arraycopy(network, 0, firstHost, 0, network.length);
        //Sum one to the last network index
        firstHost[network.length - 1] += 1;

        //Copy the broadcast array into the lastHost array
        System.arraycopy(broadcast, 0, lastHost, 0, broadcast.length);
        //Subtract one from the last broadcast index
        lastHost[broadcast.length - 1] -= 1;

        return getFormatted(firstHost) + " to " + getFormatted(lastHost);
    }

    /**
     * Method to calculate the total number of hosts counting with the network address and the
     * broadcast address.
     *
     * @param subnetMask An array of integers containing the subnet mask value.
     * @return an integer number containing the total number of hosts for that network.
     */
    public int totalNumHosts(int[] subnetMask) {
        String subnet = getFormattedBinary(subnetMask);
        //integer to count the number of zeros in the subnetMask array
        int zeroCounter = 0;

        //Counting the number of zeros in the array.
        for (int i = 0; i < subnet.length(); i++) {
            if (subnet.charAt(i) == '0') {
                zeroCounter += 1;
            }
        }

        return (int) (Math.pow(2, zeroCounter));
    }

    /**
     * Method to calculate the total number of hosts available in the network
     *
     * @param subnetMask An array of integers containing the subnet mask value.
     * @return the total of hosts calculated in the previously method minus 2. (network address and broadcast address)
     */
    public int usableNumHosts(int[] subnetMask) {

        return totalNumHosts(subnetMask) - 2;
    }

    /**
     * Method to call the methods to operate the calculations based in the values passed
     * and print in a table form all the results.
     *
     * @param ipv4String       a String containing the ipv4 value to be used in the calculations
     * @param subnetMaskString a String containing the subnet mask value to be used in the calculations
     */
    public void printResults(String ipv4String, String subnetMaskString) {
        // call the method convertToIntArray to pass that String to an array of integers
        int[] ipv4 = convertToIntArray(ipv4String);
        // call the method convertToIntArray to pass that String to an array of integers
        int[] subnetMask = convertToIntArray(subnetMaskString);

        //
        int[] network = network(ipv4, subnetMask);
        int[] broadcast = broadcast(network, subnetMask);

        // Creating Strings to be used to format in a table form
        String leftAlignFormat = "| %-29s | %-35s |%n";
        String separator = "+-------------------------------+-------------------------------------+%n";

        // Calling the methods and printing the results to the user
        System.out.println("You have entered the following value:");
        System.out.println("[ IPv4 Address ]: " + ipv4String);
        System.out.println("[ Subnet Mask ]: " + subnetMaskString);

        System.out.println();
        System.out.format(separator);
        System.out.format("| Information                   |                Result               |%n");
        System.out.format(separator);
        System.out.format(leftAlignFormat, "IPv4 Class", ipClass(ipv4[0]));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Binary IPv4 Address", getFormattedBinary(ipv4));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Binary Subnet Mask", getFormattedBinary(subnetMask));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "CIDR Notation", getFormatted(network) + cidrNotation(subnetMask));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Short Notation", shortNotation(ipv4String, subnetMask));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Number of bits borrowed", borrowedBits(ipv4, subnetMask));
        System.out.format(separator);
        int subnetIndex = subnetIndex(network, subnetMask);
        if (subnetIndex > 0) {
            System.out.format(leftAlignFormat, "Subnet Mask Index", subnetIndex(network, subnetMask));
            System.out.format(separator);
        } else {
            System.out.format(leftAlignFormat, "Subnet Mask Index", "No subnet index");
            System.out.format(separator);
        }
        System.out.format(leftAlignFormat, "Wildcard Mask", getFormatted(wildcardMask(subnetMask)));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Network ID", getFormatted(network));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Broadcast ID", getFormatted(broadcast));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Host IPv4 Address Range", getFormatted(network) + " - " + getFormatted(broadcast));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Total Number of Hosts", totalNumHosts(subnetMask));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Host IPv4 Addresses Available", usableHostIPRange(network, broadcast));
        System.out.format(separator);
        System.out.format(leftAlignFormat, "Total Number of usable Hosts", usableNumHosts(subnetMask));
        System.out.format(separator);
        System.out.println();
        System.out.println("**CIDR (Classless Inter-Domain Routing)");

    }

}
