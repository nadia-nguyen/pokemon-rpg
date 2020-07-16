import java.util.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Main {
	
	public static void main (String[]args) throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner (System.in);
		Scanner scEnemy = new Scanner(System.in);
		System.out.println("Welcome to the Game");
		System.out.println("What is your name?");
		String s = sc.nextLine();
		Player player = new Player (s);
		Map map = new Map (5,5);
		System.out.println("Would you like to load previous game? Enter 'y' or 'n'");
		String choice = sc.nextLine();
		int enemiesDefeated = 0;
		int petsCaught = 0;
		//to load previous game
		if (choice.equals("y")) {
			FileInputStream fin = new FileInputStream("save.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			player = (Player) ois.readObject();
			enemiesDefeated = player.getEnemiesDefeated();
			petsCaught = player.getPetsCaught();
			map = (Map) ois.readObject();
			map.continueGame();
			ois.close();
		}
		else {
			System.out.println("----Resetting Progress ----");
			
		}
		//set up objects
		int totalEnemies = 4;
		int totalPets = 3;
		Pet pet1 = new Pet ("Sally",20,4);
		Pet pet2 = new Pet ("Teddy",5,1);
		Pet pet3 = new Pet ("Kacheek",9,3);
		Enemy e1 = new Enemy ("Angry Bird",5,3,2);
		Enemy e2 = new Enemy ("Angry Pig",10,4,5);
		Enemy e3 = new Enemy ("Angry Chicken",6,2,4);
		Enemy e4 = new Enemy ("Angry Cow",8,4,4);
		Item i1 = new Item ("Food");
		Bag bag = new Bag (2);
		
		//game setup (welcome message, instructions how to close game and displaying of map)
		System.out.println("Welcome "+player.getName()+"! You are the 'x'");
		System.out.println("Catch all pets and defeat all enemies to win game!");
		System.out.println("Enter \"bye\" two seperate times to end game");
		map.displayMap();
		//how to navigate map (while an action with an object cannot be done, player can continue to move)
		System.out.println("Do you want to move left, right,up or down?");
		String movement = sc.nextLine();
		boolean action = map.getAction();
		
		//while the player has not tried to end game and interactions between pets and enemies can still be made
		while ((map.getGameStatus()==true)&&((enemiesDefeated<totalEnemies)||(petsCaught<totalPets))) {
			//if player has not come in contact with an object, they will continue to move around
			if (action!= true) {
				if (movement!="bye") {
					map.movePlayer(movement);
					map.checkPosition();
					movement = sc.nextLine();
					action = map.getAction();
				}
				else {
					map.gameOver();
				}
			}
			//if player has come into contact with an object
			//what happens next depends on what type of encounter it is (what object it has encountered)
			else {
				String encounter2 = map.getEncounter();
				//if object encountered is a pet
				if (encounter2.equals("Pet")) {	
					System.out.println("Do you want to try to catch "+pet1.getName()+" y/n ?");
					String choicePet = sc.nextLine();
					if (choicePet.equals("y")) {
						boolean interaction = player.catchPet(pet1);
						if (interaction == true) {
							map.petCaught();
							petsCaught++;
						}
						choicePet = " ";
						encounter2 = " ";
					}
					else {
						System.out.println("You chose to flee. ");
					}
					action = false;
					System.out.println("Do you want to move left, right,up or down?");
					movement = sc.nextLine();
				}
				//if the second pet is encountered
				else if (encounter2.equals("Pet2")) {	
					System.out.println("Do you want to try to catch "+pet2.getName()+" y/n ?");
					String choicePet = sc.nextLine();
					if (choicePet.equals("y")) {
						boolean interaction = player.catchPet(pet2);
						if (interaction == true) {
							map.pet2Caught();
							petsCaught++;
						}
						choicePet = " ";
						encounter2 = " ";
					}
					else {
						System.out.println("You chose to flee. ");
					}
					action = false;
					System.out.println("Do you want to move left, right,up or down?");
					movement = sc.nextLine();
				}
				//if the third pet is encountered
				else if (encounter2.equals("Pet3")) {	
					System.out.println("Do you want to try to catch "+pet3.getName()+" y/n ?");
					String choicePet = sc.nextLine();
					if (choicePet.equals("y")) {
						boolean interaction = player.catchPet(pet3);
						if (interaction == true) {
							map.pet3Caught();
							petsCaught++;
						}
						choicePet = " ";
						encounter2 = " ";
					}
					else {
						System.out.println("You chose to flee. ");
					}
					action = false;
					System.out.println("Do you want to move left, right,up or down?");
					movement = sc.nextLine();
				}
				
				//if object encountered is an enemy
				else if (encounter2.equals("Enemy")) {
					System.out.print("Enter 'f' to fight or 'r' to run: ");
					String choiceEnemy = scEnemy.nextLine();
					//if player chooses to fight, pets will continue attacking each other until one loses all HP
					if (choiceEnemy.equals("f")) {
						if (((map.isPetCaught() == true)|| (map.isPet2Caught() == true))||(map.isPet2Caught())) {
							ArrayList <Pet> petList = player.getList();
							int petChoice = player.choosePet();
							Pet chosenPet = petList.get(petChoice -1);
							boolean result = player.fightSequence(chosenPet, e1);
							if (result == true) {
								map.enemyDefeated();
								player.gainExperience(e1);
								enemiesDefeated++;
							}
						}
						else {
							System.out.println("You have no pet, you cannot fight.");
						}
					
					}
					//if player decides to run away
					else if (choiceEnemy.equals("r")) {
						System.out.println("You ran away.");
						encounter2 = " ";
					}
					//prints the map since no interaction can be made (enemy already defeated)
					else {
						map.movePlayer(movement);
					}
					
					System.out.println("\nDo you want to move left, right,up or down?");
					movement = sc.nextLine();
					action = false;
				}
				//if encounters second enemy
				else if (encounter2.equals("Enemy2")) {
					System.out.print("Enter 'f' to fight or 'r' to run: ");
					String choiceEnemy = scEnemy.nextLine();
					//if player chooses to fight, pets will continue attacking each other until one loses all HP
					if (choiceEnemy.equals("f")) {
						if (((map.isPetCaught() == true)|| (map.isPet2Caught() == true))|| (map.isPet3Caught())) {
							ArrayList <Pet> petList = player.getList();
							int petChoice = player.choosePet();
							Pet chosenPet = petList.get(petChoice -1);
							boolean result = player.fightSequence(chosenPet, e2);
							if (result == true) {
								map.enemy2Defeated();
								player.gainExperience(e2);
								enemiesDefeated++;
							}
						}
						//if player decides to run away
						else if (choiceEnemy.equals("r")) {
							System.out.println("You ran away.");
							encounter2 = " ";
							
						}
						else {
							System.out.println("You have no pet, you cannot fight.");
						}
					}
					System.out.println("\nDo you want to move left, right,up or down?");
					movement = sc.nextLine();
					action = false;
				}
				//if object encountered is the third enemy
				else if (encounter2.equals("Enemy3")) {
					System.out.print("Enter 'f' to fight or 'r' to run: ");
					String choiceEnemy = scEnemy.nextLine();
					//if player chooses to fight, pets will continue attacking each other until one loses all HP
					if (choiceEnemy.equals("f")) {
						if (((map.isPetCaught() == true)|| (map.isPet2Caught() == true)) || (map.isPet3Caught())) {
							ArrayList <Pet> petList = player.getList();
							int petChoice = player.choosePet();
							Pet chosenPet = petList.get(petChoice -1);
							boolean result = player.fightSequence(chosenPet, e3);
							if (result == true) {
								map.enemy3Defeated();
								player.gainExperience(e3);
								enemiesDefeated++;
							}
						}
						//if player decides to run away
						else if (choiceEnemy.equals("r")) {
							System.out.println("You ran away.");
							encounter2 = " ";
							
						}
						else {
							System.out.println("You have no pet, you cannot fight.");
						}
					}
					System.out.println("\nDo you want to move left, right,up or down?");
					movement = sc.nextLine();
					action = false;
				}
				//if fourth enemy is encountered
				else if (encounter2.equals("Enemy4")) {
					System.out.print("Enter 'f' to fight or 'r' to run: ");
					String choiceEnemy = scEnemy.nextLine();
					//if player chooses to fight, pets will continue attacking each other until one loses all HP
					if (choiceEnemy.equals("f")) {
						if (((map.isPetCaught() == true)|| (map.isPet2Caught() == true))|| (map.isPet3Caught())) {
							ArrayList <Pet> petList = player.getList();
							int petChoice = player.choosePet();
							Pet chosenPet = petList.get(petChoice -1);
							boolean result = player.fightSequence(chosenPet, e4);
							if (result == true) {
								map.enemy4Defeated();
								player.gainExperience(e4);
								enemiesDefeated++;
							}
						}
						//if player decides to run away
						else if (choiceEnemy.equals("r")) {
							System.out.println("You ran away.");
							encounter2 = " ";
							
						}
						else {
							System.out.println("You have no pet, you cannot fight.");
						}
					}
					System.out.println("\nDo you want to move left, right,up or down?");
					movement = sc.nextLine();
					action = false;
				}
				/*else if (encounter2.equals("UseItem")) {
					ArrayList <Item>items = bag.bagContents();
					System.out.println("Enter the number of the item you'd like to use: ");
					bag.printItems();
					int chooseItem = sc.nextInt();
					Item chosenItem = items.get(chooseItem - 1);
					if (chosenItem.getName().equals("Food")) {
						ArrayList <Pet> petList = player.getList();
						int petChoice = player.choosePet();
						Pet chosenPet = petList.get(petChoice-1);
						chosenPet.addHP(5);
						System.out.println("5 HP has been added to "+chosenPet.getName()+"'s health.");
					}
					bag.useItem(chosenItem);
					encounter2 = " ";
					System.out.println("\nDo you want to move left, right,up or down?");
					movement = sc.nextLine();
					action = false;
					
				}
				*/
				//resets encounter2 value so it can return to the movement loop 
				else {
					encounter2 = " ";
					action = false;
				}

			}
			
		}
		//saves progress and ends game
		FileOutputStream fout = new FileOutputStream("save.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(player);
		oos.writeObject(map);
		oos.close();
		//if all possible interactions have been made, player has won game
		if ((enemiesDefeated==totalEnemies)&&(petsCaught==totalPets)) {
			System.out.println("Congratulations! You have defeated all "+totalEnemies+" enemies and caught all "+totalPets+" pets!");
			System.out.println("You have won the game!");
		}
		//if not, prints their progress in game
		else {
			System.out.println("You have defeated "+enemiesDefeated+" out of "+totalEnemies+" enemies and you have caught "+petsCaught+" out of "+totalPets+" pets.");
			System.out.println("Goodbye. Your progress has been saved. ");
		}

	}
}
