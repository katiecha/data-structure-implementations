package java_intro;

import java.util.Scanner;

public class JavaWarmUp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numOfTrans = Integer.parseInt(s.nextLine()); // Reads in user input (number of transactions) as integer.

        Transaction[] database = new Transaction[numOfTrans]; // Creates Transaction[] database.

        for (int i = 0; i < numOfTrans; i++) {
            String input = s.nextLine(); // Reads in user input (transaction info) as a string.
            String[] transInfo = new String[numOfTrans]; // Creates String[] transInfo.
            transInfo = input.split(" "); // Splits up user input based on " " and places into String[] transInfo.
            Transaction trans = new Transaction(transInfo[0], transInfo[1], transInfo[2], Float.parseFloat(transInfo[3]), Integer.parseInt(transInfo[4]), Float.parseFloat(transInfo[5]), Integer.parseInt(transInfo[6])); // Creates Transaction object from String[] transInfo.
            database[i] = trans; // Sets index of Transaction[] database to new Transaction object
        }
        getMaxPriceIndex(database);
        getMinPriceIndex(database);
        getCategoryInfo(database, "book");
        getCategoryInfo(database, "jewelry");
        getCategoryInfo(database, "phone");
    }

    public static void getMaxPriceIndex(Transaction[] database) {
        float max = 0;
        int maxIndex = 0;
        for (int i = 0; i < database.length; i++){
            if (database[i].getPrice() >= max){
                max = database[i].getPrice();
                maxIndex = i;
            }
        }
        System.out.println("Highest per unit sale:");
        System.out.println("\tWhen: " + database[maxIndex].getDate() + " " + database[maxIndex].getTime());
        System.out.println("\tCategory: " + database[maxIndex].getCategory());
        System.out.println("\tPrice: " + String.format("%.2f", max));
        System.out.println("\tRating: " + database[maxIndex].getRating());
    }

    public static void getMinPriceIndex(Transaction[] database) {
        float max = 0;
        for (int i = 0; i < database.length; i++){
            if (database[i].getPrice() >= max){
                max = database[i].getPrice();
            }
        }
        float min = max;
        int minIndex = 0;
        for (int i = 0; i < database.length; i++){
            if (database[i].getPrice() <= min){
                min = database[i].getPrice();
                minIndex = i;
            }
        }

        System.out.println("Lowest per unit sale:");
        System.out.println("\tWhen: " + database[minIndex].getDate() + " " + database[minIndex].getTime());
        System.out.println("\tCategory: " + database[minIndex].getCategory());
        System.out.println("\tPrice: " + String.format("%.2f", min));
        System.out.println("\tRating: " + database[minIndex].getRating());
    }

    public static void getCategoryInfo(Transaction[] database, String category) {
        int counter = 0;
        int quantity = 0;
        double price = 0;
        double rating = 0;
        double duration = 0;

        for (int i = 0; i < database.length; i++){
            if (database[i].getCategory().equals(category)) {
                rating += database[i].getRating();
                duration += database[i].getDuration();
                quantity += database[i].getQuantity();
                price += database[i].getPrice() * database[i].getQuantity();
                counter++;
            }
        }

        price /= quantity;
        rating /= counter;
        duration /= counter;

        System.out.println("Averages by " + category);
        System.out.println("\tQuantity: " + quantity);
        System.out.println("\tPrice: " + String.format("%.2f", price));
        System.out.println("\tRating: " + String.format("%.2f", rating));
        System.out.println("\tDuration: " + String.format("%.2f", duration));
    }
}
