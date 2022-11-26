package LA3Q2;
import LA3Q1.*;

import java.util.Arrays;

import static LA3Q1.EthanDemoHashingWithLinearProbing.*;
public class EthanDemoHashingWithAllOpenAddressingTechniques {
    public static void main(String[] args) {
        header(3,2);
        //instantiate general variables
        System.out.println("\nLets Demonstrate our understanding on all the open addressing techniques...");
        System.out.print("How many data items: ");
        items = input.nextInt();
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();
        tableCapacity = (int)(items/lf);
        tableCapacity = checkPrime(tableCapacity);
        System.out.println("The minimum table capacity would be: " + tableCapacity);
        //temporary ValueEntry array
        EthanValueEntry[] temp = new EthanValueEntry[tableCapacity];
        for(int i=0; i<temp.length; i++) { //instantiating each index
            temp[i] = new EthanValueEntry();
        }
        hashTable = temp; //copying to hashTable
        //Integer[] arr = new Integer[]{7,14,-21,-28,35};//given array
        Integer[] arr = new Integer[]{-11,22,-33,-44,55};
        System.out.print("The given dataset is: ");
        printArray(arr);
        //adding with linear probing
        System.out.println("\n\nLet's enter each data item from the above to the hash table:\n");
        for(int i=0; i<arr.length; i++) {
            addValueLinearProbe(arr[i]);
        }
        System.out.println("Adding data - linear probing resolves collisions");
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        //emptying the table with null
        emptyHashTable();
        System.out.println("\n\nAdding data - quadratic probing resolves collisions");
        //adding with quadratic probing
        for(int i=0; i<arr.length; i++) {
            addValueQuadraticProbe(arr[i]);
        }
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        //emptying the table with null values
        emptyHashTable();
        //adding with double hashing
        for(int i=0; i<arr.length; i++) {
            addValueDoubleHashing(arr[i]);
        }
        System.out.println("\n\nAdding data - double probing resolves collisions");
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.println("\n");
        footer(3,2);
    }
    public static void addValueDoubleHashing(Integer key) {
        int q = thePrimeNumberForSecondHashFunction(tableCapacity-1); //finds the previous prime number
        int hash1 = key % tableCapacity; //hash Function 1 (Linear Method)
        int hash2 = q - (key % q); //hash Function 2 (uses the prev prime number)
        if(hash1 < 0) { //makes negative hash positive
            hash1 += tableCapacity;
        }
        while(hashTable[hash1].getKey() != -1 && hashTable[hash1].getKey() != key) { //checks if there is not a null value or a key in the hashIndex spot
            hash1 += hash2; //set the hash1 code to the hash2 function
            hash1 = hash1 % tableCapacity; //refind the index
        }
        hashTable[hash1].setKey(key);
    }
    public static void addValueQuadraticProbe(Integer key) {
        try { //run this code first
            int hash = key % tableCapacity; //find the hashIndex
            int i = 1; //set the counter to 1
            if (hash < 0) { //if hashIndex is less than zero add tableCapacity to make it positive
                hash += tableCapacity;
            }
            while (hashTable[hash].getKey() != -1) { //searching for non null index
                hash = 0; //reset the hash
                hash = hash + (i * i); //probe quadratically
                hash %= tableCapacity;
                i++; //increment the quadratic component
            }
            hashTable[hash].setKey(key);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Probing failed! Please use a load factor of 0.5 or less."); //catches out of bounds errors when load factor is too large
        }
    }
    public static void printArray(Integer[] array) {
        //print each element in the Integer array
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print("\b\b]");
    }
    public static void emptyHashTable() {
        //loop over each element and set the key to null (-1)
        for(int i=0; i<hashTable.length; i++) {
            hashTable[i].setKey(-1);
        }
    }
    public static int thePrimeNumberForSecondHashFunction(int n) {

        int m = n / 2;//we just need to check half of the n factors
        for (int i = 3; i <= m; i++) {
            if (n % i == 0) {//if n is not a prime number
                i = 2; //reset i to 2 so that it is incremented to 3 in the forheader
                //System.out.printf("i = %d\n",i);
                n--;//previous n value
                m = n / 2;//we just need to check half of the n factors
            }
        }
        return n;
    }
}
