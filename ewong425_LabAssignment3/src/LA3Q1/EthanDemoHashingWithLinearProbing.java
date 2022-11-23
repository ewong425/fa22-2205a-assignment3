package LA3Q1;

import java.util.Scanner;

public class EthanDemoHashingWithLinearProbing {
    //initalize variables
    int items, tableCapacity; //items hold the value of the total number of data items to be added, tableCapacity stores value capacity
    double lf; //records load factor
    Scanner input = new Scanner(System.in);
    EthanValueEntry[] hashTable, workingHashTable; //workingHashTable used to store the copy of the hashTable reference for rehashing
    public static void main(String[] args) {

    }
    public void reHashingWithLinearProbe() {

    }
    public void removeValueLinearProb(Integer val) {
        EthanValueEntry n = new EthanValueEntry(val);
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i].getKey() == n.getKey()) {
                hashTable[i].setKey(-111);
            } else {
                System.out.println("Key is not Found");
            }
        }

    }
    public void addValueLinearProbe(Integer val) {
        EthanValueEntry n = new EthanValueEntry(val);
        int index = n.getKey() % (tableCapacity - 1);
        while(hashTable[index].getKey() != -1) {
            index++;
            index %= (tableCapacity -1);
        }
        hashTable[index].setKey(n.getKey());
    }
    public void printHashTable() {
        System.out.println("[");
        for(int i=0; i<hashTable.length; i++) {
            if(hashTable[i].getKey() == -1) {
                System.out.print(" null");
            } else if(hashTable[i].getKey() == -111) {
                System.out.print(" available");
            }
        }
    }
    public void myHeader(int l, int a) {

    }
    public void myFooter(int l, int a) {

    }
    public int checkPrime(int n) {
        int m = n / 2; //need to check half of the n factors
        for(int i = 3; i <= m; i++) {
            if(n % i == 0) { //if n is not a prime
                i = 2; //reset i to 2 so that it its icremented to 3
                n++; //increase n
                m = n / 2; //divide in half again
            }
        }
        return n;
    }
}
