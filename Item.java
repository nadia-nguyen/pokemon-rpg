public class Item {
	private String name;
	
	/**
	 * creates an item object
	 * @param itemName name of the new item	
	 * @param itemWeight weight of the new item
	 */
	public Item (String itemName) {
		name = itemName;
	}
	
	/**
	 * get only the name of the item
	 * @return name 
	 */
	public String getName () {
		return name;
	}
	
	
	/**
	 * properly prints details of the item
	 */
	public String toString () {
		return name;
	}
	
	

}

