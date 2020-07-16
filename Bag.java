import java.util.*;
public class Bag {
	ArrayList<Item> items = new ArrayList <Item>();
	private int bagCapacity;
	private int totalItems = 0;
	
	/**
	 * creating the bag
	 * @param bagWeight max weight the bag can hold
	 */
	public Bag (int maxItems) {
		bagCapacity = maxItems;
	}
	/**
	 * adds the item to the bag
	 * @param i name of item to be added
	 */
	public void addItem (Item i) {
		items.add(i);
		System.out.println (i.getName()+" was successfully added to the bag.");
	}
	/**
	 * removes an item from the bag
	 * @param i name of item to be removed
	 */
	public void useItem (Item i) {
		items.remove(i);
		System.out.println(i.getName()+" was successfully removed from the bag. ");
	}
	
	/**
	 * checks if item can fit in bag 
	 * @param i Item name
	 */
	public void checkItem (Item i) {
		if (totalItems + 1>bagCapacity) {
			System.out.println(i.getName()+" can't fit in bag");
		}
		else {
			addItem (i);
		}
		
	}
	
	/**
	 * prints all items in the bag
	 */
	public void printItems() {
		int x = 1;
		System.out.println("CONTENTS OF BAG: ");
		for (Item i: items) {
			System.out.println("Item #"+x+": ");
			System.out.println(i.toString());
			x++;
		}
	}
	
	public ArrayList bagContents() {
		return items;
	}


	

}
