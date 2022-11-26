package LA3Q1;

import java.sql.SQLOutput;
import java.time.OffsetDateTime;
import java.util.Scanner;

public class EthanDemoHashingWithLinearProbing {
    //initalize variables
    public static int items = 0; //items hold the value of the total number of data items to be added, tableCapacity stores value capacity
    public static Scanner input = new Scanner(System.in);
    public static double lf; //records load factor
    public static EthanValueEntry[] hashTable;
    static EthanValueEntry[] workingHashTable; //workingHashTable used to store the copy of the hashTable reference for rehashing
    public static int tableCapacity;
    public static void main(String[] args) {
        header(3, 1);
        //instantiate variables
        System.out.println("Decide on the initial capacity based on the load factor and dataset size");
        System.out.print("How many data items: ");
        items = input.nextInt();
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();
        tableCapacity = (int)(items/lf);
        tableCapacity = checkPrime(tableCapacity);
        System.out.println("The minimum table capacity would be: " + tableCapacity);
        //set the hashTable size to the tableCapacity
        hashTable = new EthanValueEntry[tableCapacity];
        //instantiate each index
        for(int i=0; i<hashTable.length; i++) {
            hashTable[i] = new EthanValueEntry();
        }
        //Populating the hash table
        for(int i=0; i<items; i++) {
            System.out.print("Enter item " + (i+1) + ":" );
            Integer num = input.nextInt();
            addValueLinearProbe(num);
        }
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.println("\n\nLets remove two values from the table and then add one...\n");
        System.out.print("Enter a value you want to remove: ");
        removeValueLinearProb(input.nextInt()); //removes user next imput
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.print("\nEnter a value you want to remove: ");
        removeValueLinearProb(input.nextInt());
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.print("\nEnter value to add to the table: ");
        int key = input.nextInt();
        addValueLinearProbe(key); //adds users next input
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.println("\n\nRehashing the table....");
        reHashingWithLinearProbe(); //rehashing the table
        System.out.println("The rehashed table capacity is " + tableCapacity);
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.println();
        footer(3,1);
    }
    public static void reHashingWithLinearProbe() {
        //double the tableCapacity size;
        int newSize = tableCapacity * 2;
        tableCapacity = checkPrime(newSize); //finds the next prime number
        workingHashTable = hashTable.clone(); //save the hashTable components to the workingHashTable
        EthanValueEntry[] temp = new EthanValueEntry[tableCapacity]; //temporary array to set to the size of the new tableCapacity
        hashTable = temp;// set hashTable to that size
        for(int i=0; i<hashTable.length; i++) { //re instantiate each element
            hashTable[i] = new EthanValueEntry();
        } //copy the elements from the workingHashTable back into the new hashTable
        for(int i=0; i<workingHashTable.length; i++) { //must re-evaluate their indexes
            if(workingHashTable[i].getKey() == -111) { //set any "available" to null
                hashTable[i].setKey(-1);
            } else {
                addValueLinearProbe(workingHashTable[i].getKey()); //rehash index
            }
        }
    }
    public static void removeValueLinearProb(Integer value) {
        boolean noKey = false; //key not found checker
        for(int i=0; i<hashTable.length; i++) { //loop over the entire table
            if(hashTable[i].getKey() == value) { //if the key == value set it to available
                hashTable[i].setKey(-111);
                noKey = false; //set the boolean checker to false
                System.out.print(value + " is Found and removed, ");
                break; //break the loop
            } else {
                noKey = true; //set the key to true
            }
        }
        if(noKey) { //if the key is true it means the value was not found
            System.out.print("Key not Found! ");
        }
    }
    public static void addValueLinearProbe(Integer key) {
        int index = key % (tableCapacity); //find hashIndex
        if(index < 0) { //make hashIndex positive if it is negative
            index += tableCapacity;
        }
        while(hashTable[index].getKey() != -1 && hashTable[index].getKey() != -111) { //checking for a null spot
            index++; //if it is a null spot increase the index by 1
            index = index % tableCapacity; //find the hashIndex again
        }
        if(hashTable[index].getKey() == -111) {
            hashTable[index].setKey(key);
        } else {
            hashTable[index].setKey(key);
        }
    }
    public static void printHashTable() {
        System.out.print("[");
        for(int i=0; i<hashTable.length; i++) { //loop over entire table
            if(hashTable[i].getKey() == -1) { //printing null when it encounters a -1
                System.out.print("null, ");
            } else if(hashTable[i].getKey() == -111) {
                System.out.print("available, "); //printing available when it encouters -111
            } else {
                System.out.print(hashTable[i].getKey() + ", "); //otherwise print the elements
            }
        }
        System.out.print("\b\b]");
    }
    public static int checkPrime(int n) {
        //code copied from professor
        int m = n / 2;//we just need to check half of the n factors
        for (int i = 3; i <= m; i++) {
            if (n % i == 0) {//if n is not a prime number
                i = 2; //reset i to 2 so that it is incremented to 3 in the forheader
                //System.out.printf("i = %d\n",i);
                n++;//next n value
                m = n / 2;//we just need to check half of the n factors
            }
        }
        return n;
    }
    public static void header(int n, int q) {
        System.out.println("Lab Assignment " + n + ", Q" + q);
        System.out.println("Prepared by: Ethan Wong");
        System.out.println("Student Number: 251205049");
        System.out.println("Goal: Demonstrate our understanding of hashing with open addressing specifically Linear Probing");
        System.out.println("=============================================");
    }
    public static void footer(int n, int q) {
        int lab = n;
        System.out.println("=============================================");
        System.out.println("Completion of Lab Assignment " + n + ", Q" + q + " is successful!");
        System.out.println("Signing off -Ethan ");
    }

}
