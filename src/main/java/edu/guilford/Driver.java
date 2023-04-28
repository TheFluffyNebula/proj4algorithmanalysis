package edu.guilford;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GroceryItem item1 = new GroceryItem();
        System.out.println(item1);
        System.out.println("How many items?");
        int numItems = scan.nextInt();
        GroceryItem[] items = new GroceryItem[numItems];
        for (int i = 0; i < numItems; i++) {
            items[i] = new GroceryItem();
        }
        //small dataset, recommended input size n < 20 to see all prices easier
        System.out.println("original prices: " + viewPrices(items));
        selectionSort(items);
        System.out.println("after selection sort: " + viewPrices(items));
        shuffle(items);
        System.out.println("after shuffle: " + viewPrices(items));
        quickSort(items, 0, items.length - 1);
        System.out.println("after quicksort: " + viewPrices(items));
        scan.close();

        //larger dataset to test time complexities
        final int MAX = 10000;
        GroceryItem[] items2 = new GroceryItem[MAX];
        for (int i = 0; i < MAX; i++) {
            items2[i] = new GroceryItem();
        }
        long startTime = System.nanoTime();
        selectionSort(items2);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("selection sort time: " + (duration/1e9) + "seconds");
        shuffle(items2);
        long startTime2 = System.nanoTime();
        quickSort(items2, 0, items2.length - 1);
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        System.out.println("quicksort time: " + (duration2/1e9) + "seconds");
        shuffle(items2); //shuffle again to make sure it's not sorted

        //searching
        long startTime3 = System.nanoTime();
        int index = linearSearch(items2, 0.52);
        long endTime3 = System.nanoTime();
        long duration3 = endTime3 - startTime3;
        System.out.println("element found at index " + index + " using linear search");
        System.out.println("linear search time: " + (duration3/1e9) + "seconds");
        quickSort(items2, 0, items2.length - 1); // sort before using binary search
        long startTime4 = System.nanoTime();
        int index2 = binarySearch(items2, 0.52);
        long endTime4 = System.nanoTime();
        long duration4 = endTime4 - startTime4;
        System.out.println("element found at index " + index2 + " using binary search");
        System.out.println("binary search time: " + (duration4/1e9) + "seconds");

    }

    // O(n^2) sorting algorithm: selection sort
    public static void selectionSort(GroceryItem[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].getPrice() < array[minIndex].getPrice()) {
                    minIndex = j;
                }
            }
            GroceryItem temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    // O(n log n) sorting algorithm: quicksort
    public static void quickSort(GroceryItem[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    public static int partition(GroceryItem[] array, int low, int high) {
        GroceryItem pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].getPrice() < pivot.getPrice()) {
                i++;
                GroceryItem temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        GroceryItem temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // linear search for a price
    public static int linearSearch(GroceryItem[] items, double price) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getPrice() == price) {
                return i;
            }
        }
        return -1;
    }

    // binary search for a price
    public static int binarySearch(GroceryItem[] items, double price) {
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (items[mid].getPrice() == price) {
                return mid;
            } else if (items[mid].getPrice() < price) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // not found
    }
    // check if it sorted correctly
    public static String viewPrices(GroceryItem[] items) {
        String output = "";
        for (int i = 0; i < items.length; i++) {
            output += items[i].getPrice() + " ";
        }
        return output;
    }

    // shuffle the array
    public static void shuffle(GroceryItem[] items) {
        for (int i = 0; i < items.length; i++) {
            int randomIndex = (int) (Math.random() * items.length);
            GroceryItem temp = items[i];
            items[i] = items[randomIndex];
            items[randomIndex] = temp;
        }
    }
}
