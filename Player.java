import java.util.ArrayList;
import java.io.Serializable; 
import java.util.*;
public class Player implements Serializable{
	ArrayList <Pet> pets = new ArrayList <Pet>();
	private String playerName;
	private int xp;
	private boolean caught;
	private boolean winFight = false;
	private int enemiesDefeated = 0;
	private int petsCaught = 0;
	/**
	 * construct a player object with a name and xp value
	 * @param s name of player
	 */
	public Player (String s) {
		playerName = s;
		xp = 0;
	}
	
	/**
	 * method where player attempts to catch the pet
	 * @param p pet player wishes to catch
	 * @return caught whether or not the pet has been caught 
	 */
	public boolean catchPet (Pet p) {
		int num = (int)(Math.random()*10 + 1);
		if (num%2 == 0) {
			pets.add(p);
			caught = true;
			petsCaught++;
			System.out.println("Congratulations! You caught "+ p.getName()+"! ");
		}
		else {
			System.out.println("Oh no! They got away :( ");
			caught = false;
		}
		return caught;
	}
	
	/**
	 * print total number of pets owned by player
	 */
	public void getTotalPets() {
		System.out.println("The total number of pets is: "+pets.size());
	}
	
	/**
	 * method to give player experience based on enemy defeated
	 * @param e enemy defeated
	 */
	public void gainExperience(Enemy e) {
		xp = xp+ e.getXPGive();
		System.out.println("You now have "+xp+" total XP!");
	}
	
	/**
	 * access xp of player
	 * @return xp total xp
	 */
	public int getXP() {
		return xp;
	}
	
	/**
	 * returns name of player
	 * @return playerName name of player
	 */
	public String getName() {
		return playerName;
	}
	
	/**
	 * fight sequence between pet and enemy
	 * @param p pet object chosen
	 * @param e enemy object encountered
	 * @return winFight 
	 */
	public boolean fightSequence (Pet p, Enemy e) {
		while ((p.getHP()>0) && (e.getHP()>0)) {
			//player.printPets();
			p.attack(e);
			System.out.println(e.toString()+"\n");
			e.attack(p);
			System.out.println(p.toString());
			
		}
		//if pet wins
		if (p.getHP()>e.getHP()) {
			winFight = true;
			enemiesDefeated++;
			System.out.println("\n"+p.getName()+" has defeated "+e.getName()+" ! ");
			//map.enemyDefeated();
			//player.gainExperience(e1);
			System.out.println("You have gained "+e.getXPGive()+" experience points!");
			//System.out.println("You now have "+player.getXP()+" total XP!");
		}
		//if both pet and enemy have health less than zero, it's a tie (counts as a win but also pet is removed from list because it's been defeated)
		else if ((e.getHP()<=0)&& (p.getHP()<=0)) {
			System.out.println("\nIt's a tie! Both "+p.getName()+" and "+e.getName()+" have been defeated.");
			System.out.println("You have gained "+e.getXPGive()+" experience points!");
			winFight = true;
			enemiesDefeated++;
			pets.remove(p);
		}
		//if enemy wins
		else {
			winFight = false;
			System.out.println("\n"+e.getName()+ " has defeated "+p.getName()+" ! :(");
			pets.remove(p);
		}
		return winFight;
	}
	
	/**
	 * prints all pets owned/caught by player
	 */
	public void printPets() {
		int x = 1;
		for (Pet p : pets) {
			System.out.println("\nPet #"+x+": ");
			System.out.println(p.toString());
			x++;
		}
	}
	
	public ArrayList <Pet> getList(){
		return pets;
	}
	
	/**
	 * to choose pet that will attack enemy
	 * @return petChoice
	 */
	public int choosePet () {
		Scanner sc = new Scanner (System.in);
		int petChoice;
		if (pets.size() > 1) {
			printPets();
			System.out.println("\nEnter number of pet you wish to use: ");
			petChoice = sc.nextInt();
			if ((petChoice<1)&&(petChoice>pets.size())) {
				System.out.println("Error. Please enter a number between 1 and "+pets.size()+": ");
				petChoice = sc.nextInt();
			}
		}
		else {
			petChoice = 1;
		}
		return petChoice;
	}
	
	public int getEnemiesDefeated() {
		return enemiesDefeated;
	}
	public int getPetsCaught() {
		return petsCaught;
	}

}

