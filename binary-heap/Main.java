package binary_heap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Test 1
        System.out.println("Test for Part 1!");
        SimpleEmergencyRoom test1 = new SimpleEmergencyRoom();
        test1.addPatient(1, 5);
        test1.addPatient(2, 4);
        test1.addPatient(3, 1);
        System.out.println(test1.dequeue().getValue());
        System.out.println(test1.dequeue().getValue());
        System.out.println(test1.dequeue().getValue());

       // Test 2
        System.out.println("Test for Part 2!");
        MinBinHeapER test2 = new MinBinHeapER<>();
        test2.enqueue(1, 5);
        test2.enqueue(2, 4);
        test2.enqueue(3, 1);
        test2.enqueue(4);
        System.out.println(test2.dequeue());
        System.out.println(test2.dequeue());
        System.out.println(test2.dequeue());
        System.out.println(test2.getMin());

        // Test 3
        System.out.println("Test for Part 3!");
        Prioritized<Integer, Integer>[] initialEntries = new Patient<Integer, Integer>[3];

        MinBinHeapER test3 = new MinBinHeapER<>(initialEntries);

        test3.enqueue(1, 5);
        test3.enqueue(2, 4);
        test3.enqueue(3, 1);
        test3.enqueue(4);
        System.out.println(test2.dequeue());
        System.out.println(test2.getMin());

        // Test 4
        System.out.println("Test for Part 4!");
        double[] comparison = compareRuntimes();
        for (int i = 0; i < comparison.length; i++) {
            System.out.println(comparison[i]);
        }
    }

    public static void fillER(MinBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        MinBinHeapER binHeap = new MinBinHeapER();
        fillER(binHeap);

        // Code for (1) Here
        double before1 = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            simplePQ.dequeue();
        }
        double after1 = System.nanoTime() ;
        results[0] = after1 - before1; // total time
        results[1] = results[0] / 100000; // avg time

        // Code for (2) Here
        double before2 = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            binHeap.dequeue();
        }
        double after2 = System.nanoTime() ;
        results[2] = after2 - before2; // total time
        results[3] = results[2] / 100000; // avg time
        return results;
    }
}


