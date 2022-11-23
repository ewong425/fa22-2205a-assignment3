package LA3Q1;

import java.sql.SQLOutput;
import java.util.Scanner;

public class EthanDemoHashingWithLinearProbing {
    //initalize variables
    static int items = 0; //items hold the value of the total number of data items to be added, tableCapacity stores value capacity
    static Scanner input = new Scanner(System.in);
    static double lf; //records load factor
    static EthanValueEntry[] hashTable;
    static EthanValueEntry[] workingHashTable; //workingHashTable used to store the copy of the hashTable reference for rehashing
    static int tableCapacity;
    public static void main(String[] args) {
        header(3, 1);
        System.out.println("Decide on the initial capacity based on the load factor and dataset size");
        System.out.println("How many data items: ");
        items = input.nextInt();
        System.out.println("What is the load factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();
        tableCapacity = checkPrime(items);
        System.out.println("The minimum table capacity would be: " + tableCapacity);

        EthanValueEntry[] temp = new EthanValueEntry[tableCapacity];
        for(int i=0; i<tableCapacity-1; i++) {
            temp[i] = new EthanValueEntry();
        }
        hashTable = temp;
        for(int i=0; i<tableCapacity-1; i++) {
            System.out.println("Enter item " + (i+1));
            addValueLinearProbe(input.nextInt());
        }
        System.out.println("The current Hash-Table: ");
        printHashTable();
        System.out.println("\nLets remove two values from the table and then add one...\n");
        System.out.println("Enter a value you want to remove: ");
        removeValueLinearProb(input.nextInt());
        System.out.println("The current hash table is");
        printHashTable();
        System.out.println("Enter a value you want to remove: ");
        removeValueLinearProb(input.nextInt());
        System.out.println("The current hash table is");
        printHashTable();

        System.out.println("Enter value to add to the table");
        addValueLinearProbe(input.nextInt());
        System.out.println("The current hash table is");
        printHashTable();

        System.out.println("Rehashing the table....");
        reHashingWithLinearProbe();
        System.out.println("The rehashed table capacity is " + tableCapacity);
        System.out.println("current hash table: ");
        printHashTable();
        footer(3,1);
    }
    public static void reHashingWithLinearProbe() {
        int newSize = tableCapacity * 2;
        checkPrime(newSize);
        workingHashTable = hashTable;
        EthanValueEntry[] hashTable = new EthanValueEntry[newSize];
        for(int i=0; i<tableCapacity-1; i++) {
            int k = workingHashTable[i].getKey();
            if(workingHashTable[i].getKey() == -111) {
                addValueLinearProbe(k);
            } else {
                hashTable[i].setKey(k);
            }
        }
        tableCapacity = newSize;
    }
    public static void removeValueLinearProb(Integer val) {
        EthanValueEntry n = new EthanValueEntry(val);
        for(int i=0; i<tableCapacity-1; i++) {
            if(hashTable[i].getKey() == n.getKey()) {
                hashTable[i].setKey(-111);
            } else {
                System.out.println("Key is not Found");
            }
        }
        items--;
    }
    public static void addValueLinearProbe(Integer val) {
        EthanValueEntry n = new EthanValueEntry(val);
        int index = n.getKey() % (tableCapacity - 1);
        while(hashTable[index].getKey() != -1) {
            index++;
            index %= (tableCapacity -1);
        }
        hashTable[index].setKey(n.getKey());
        items++;
    }
    public static void printHashTable() {
        System.out.println("[");
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i].getKey() == -1) {
                System.out.print(" null");
            } else if(hashTable[i].getKey() == -111) {
                System.out.print(" available");
            }
        }
    }
    public static int checkPrime(int n) {
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
