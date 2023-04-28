package edu.guilford;

import java.util.Arrays;

public class Driver2 {
    public static void main(String[] args) {
        final int MAX = 10;
        GroceryItem2[] items = new GroceryItem2[MAX];
        for (int i = 0; i < MAX; i++) {
            items[i] = new GroceryItem2();
        }
        System.out.println("original: " + viewNames(items));
        Arrays.sort(items);
        System.out.println("sorted: " + viewNames(items));
        
        // larger set now
        final int MAX2 = 1000000;
        GroceryItem2[] items2 = new GroceryItem2[MAX2];
        for (int i = 0; i < MAX2; i++) {
            items2[i] = new GroceryItem2();
        }
        // timing
        long startTime = System.nanoTime();
        Arrays.sort(items2);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Builtin sort time for " + MAX2 + " items: " + (duration/1e9) + "seconds");
    }

    //view names method
    public static String viewNames(GroceryItem2[] items) {
        String names = "";
        for (int i = 0; i < items.length; i++) {
            names += items[i].getName() + ", ";
        }
        return names;
    }
    
}
