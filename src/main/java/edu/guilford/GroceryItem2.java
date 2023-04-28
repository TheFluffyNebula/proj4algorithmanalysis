package edu.guilford;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GroceryItem2 implements Comparable<GroceryItem2>{ //apparently "Comparable allows this to work"
    Random rand = new Random();
    //attributes
    private String name;
    private double price;
    private int quantity;

    //default constructor
    public GroceryItem2() {
        List<String> possibleItems = Arrays.asList(
    "apple", "banana", "orange", "grape", "milk", "bread", "eggs", 
        "chicken", "beef", "pork", "fish", "cheese", "yogurt", "cereal", "rice", 
        "beans", "pasta", "soda", "water", "juice", "beer", "wine", "chips", "cookies");
        this.name = possibleItems.get(rand.nextInt(possibleItems.size()));
        double nonRoundedPrice = rand.nextDouble() * 1000;
        this.price = nonRoundedPrice - (nonRoundedPrice % 0.01);
        this.quantity = rand.nextInt(10) + 1;
    }

    //value constructor
    public GroceryItem2(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    //toString method
    @Override
    public String toString() {
        return "Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity;
    }
    //compareTo method, python's __lt__ equivalent? Seems to be since this method must be named compareTo
    public int compareTo(GroceryItem2 other) {
		// sort by name
		return this.name.compareTo(other.name);
	}


}
