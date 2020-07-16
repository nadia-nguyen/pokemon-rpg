import java.io.Serializable;
public class Map implements Serializable{
	private int MapArray [][];
	private int heroXpos = 0;
	private int heroYpos = 0;
	private int enemyXpos = 2;
	private int enemyYpos = 2;
	private int enemy2Xpos = 4;
	private int enemy2Ypos = 3;
	//private int itemXpos = 3;
	//private int itemYpos = 1;
	private int enemy3Xpos = 3;
	private int enemy3Ypos = 1;
	private int enemy4Xpos = 0;
	private int enemy4Ypos = 3;
	private int petXpos = 1;
	private int petYpos = 2;
	private int pet2Xpos = 0;
	private int pet2Ypos = 4;
	private int pet3Xpos = 1;
	private int pet3Ypos = 1;
	private boolean action = false;
	private String encounter;
	private boolean caught = false;
	private boolean caught2 = false;
	private boolean caught3 = false;
	private boolean defeated = false;
	private boolean defeated2 = false;
	private boolean defeated3 = false;
	private boolean defeated4 = false;
	//private boolean found = false;
	private boolean gameStatus = true;
	//private boolean useItem = false;
	
	
	/**
	 * creates map object
	 * @param xAxis length of x axis
	 * @param yAxis length of y axis
	 */
	public Map (int xAxis, int yAxis){
		MapArray = new int [xAxis][yAxis];
	}
	
	public void displayMap() {
		for (int x = 0;x<5;x++) {
			for (int y=0;y<5;y++) {
				if ((x==heroYpos)&&(y==heroXpos)){
					System.out.print("x");
				}
				else {
				System.out.print("-");
				}
			}
			System.out.println();
			
		}
	}
	
	
	/**
	 * moves player according to direction, checks to make sure they don't go out of bounds
	 * @param s input from user
	 */
	public void movePlayer(String s) {
		if (s.equalsIgnoreCase("right")){
			heroXpos++;
		}
		else if (s.equalsIgnoreCase("left")) {
			heroXpos--;
		}
		else if (s.equalsIgnoreCase ("up")) {
			heroYpos--;
		}
		else if (s.equalsIgnoreCase("down")) {
			heroYpos++;
		}
		else if (s.equalsIgnoreCase("bye")) {
			gameStatus = false;
		}
		else {
			System.out.println("Error. Please enter 'right', 'left', 'up', or 'right': ");
		}
		//if hero goes out of bounds, this is to fix map values so when printing, they will still be on the map
		//makes sure its not possible for player to go out of bounds
		if ((heroXpos>(MapArray.length-1))|| (heroYpos>MapArray.length-1)|| (heroXpos<0)|| (heroYpos<0)) {
			System.out.println("Error you are at the edge of the map.");
			if (heroXpos>MapArray.length-1) {
				heroXpos--;
			}
			else if (heroYpos>MapArray.length-1) {
				heroYpos--;
			}
			else if (heroXpos<0) {
				heroXpos++;
			}
			else {
				heroYpos++;
			}
		}
		displayMap();
	}
	
	/**
	 * method to determine if an action with an obstacle can occur
	 * stops movement
	 * @return action if player is able to interact with encountered obstacle
	 */
	public boolean getAction () {
		return action;
	}
	
	/**
	 * method to determine what type of encounter has occurred 
	 * @return encounter what type of object player has encountered
	 */
	public String getEncounter() {
		return encounter;
	}
	
	/**
	 * checks position of player
	 * tells them if they've encountered an enemy/item/pet
	 * outputs their current position if nothing is encountered 
	 */
	public void checkPosition() {
		/*if (((heroXpos==itemXpos)&& (heroYpos==itemYpos))&& (found == false)) {
			System.out.print("You've encountered an item! Press enter to continue.");
			action = true;
			encounter = "Item";
			//System.out.print("Do you want to pick up the item? y/n?");
		}
		*/
		if (((heroXpos==enemyXpos)&& (heroYpos==enemyYpos))&& (defeated == false)) {
			System.out.print("You've encountered an enemy! :o Press enter to continue.");
			action = true;
			encounter = "Enemy";
			
		}
		else if (((heroXpos==enemy2Xpos)&& (heroYpos==enemy2Ypos))&& (defeated2 == false)) {
			System.out.print("You've encountered an enemy! :o Press enter to continue.");
			action = true;
			encounter = "Enemy2";
		}
		else if (((heroXpos==enemy3Xpos)&& (heroYpos==enemy3Ypos))&& (defeated3 == false)) {
			System.out.print("You've encountered an enemy! :o Press enter to continue.");
			action = true;
			encounter = "Enemy3";
		}
		else if (((heroXpos==enemy4Xpos)&& (heroYpos==enemy4Ypos))&& (defeated4 == false)) {
			System.out.print("You've encountered an enemy! :o Press enter to continue.");
			action = true;
			encounter = "Enemy4";
		}
		else if (((heroXpos==petXpos)&& (heroYpos==petYpos))&&(caught == false)) {
			System.out.print("You've encountered a pet! Press enter to continue. ");
			action = true;
			encounter = "Pet";
		}
		else if (((heroXpos==pet2Xpos)&& (heroYpos==pet2Ypos))&&(caught2 == false)) {
			System.out.print("You've encountered a pet! Press enter to continue. ");
			action = true;
			encounter = "Pet2";
		}
		else if (((heroXpos==pet3Xpos)&& (heroYpos==pet3Ypos))&&(caught3 == false)) {
			System.out.print("You've encountered a pet! Press enter to continue. ");
			action = true;
			encounter = "Pet3";
		}
		else {
			//System.out.println ("X pos of player: "+(heroXpos+1)+"\nY pos of player: "+(heroYpos+1));
			System.out.print("Do you want to move left, right,up or down?");
			encounter = " ";
		}
	}
	
	/**
	 * if enemy has been defeated, player can no longer interact with it
	 */
	public void enemyDefeated() {
		defeated = true;
	}
	
	/**
	 * if second enemy has been defeated, player can no longer interact with it
	 */
	public void enemy2Defeated() {
		defeated2 = true;
	}
	
	public void enemy3Defeated() {
		defeated3 = true;
	}
	
	public void enemy4Defeated() {
		defeated4 = true;
	}
	
	/**
	 * if pet has been caught, player can no longer interact with it
	 */
	public void petCaught() {
		caught = true;
	}
	
	public void pet2Caught() {
		caught2=true;
	}
	
	public void pet3Caught() {
		caught3 = true; 
	}
	
	/**
	 * returns if a pet is caught or not
	 * @return caught
	 */
	public boolean isPetCaught() {
		return caught;
	}
	
	public boolean isPet2Caught() {
		return caught2;
	}
	
	public boolean isPet3Caught() {
		return caught3;
	}

	/**
	 * returns X position of player
	 * @return heroXpos x coordinate of player
	 */
	public int getHeroXPos() {
		return heroXpos;
	}
	
	/**
	 * returns Y position of player
	 * @return heroYpos y coordinate of the player
	 */
	public int getHeroYPos() {
		return heroYpos;
	}
	
	/**
	 * to end game
	 * used to change gameStatus
	 * @return gameStatus whether or not the game will continue 
	 */
	public boolean gameOver() {
		return gameStatus = false;
	}
	
	/**
	 * used when continuing from saved point
	 * to change the status back to true
	 * @return gameStatus 
	 */
	public boolean continueGame() {
		return gameStatus = true;
	}
	
	/**
	 * get game status
	 * used to be able to access variable in main class
	 * @return gameStatus whether or not game will continue
	 */
	public boolean getGameStatus() {
		return gameStatus;
	}


}
