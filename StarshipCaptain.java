import java.util.Random;
import java.util.Scanner;

/**
This is just a beginning.
 *
 * @author Rich Whybrew
 *
 */
 
 // Begin awesome program
 
public class StarshipCaptain {
	
	/**
	 * Create a seeded random number generator 
	 * (remove seed once testing is complete 
	 * having a seed will provide predictable numbers while testing)
	 */
	private static final Random randomNumbers = new Random();
	
	/**
	 * This method displays a pause method and waits until the
	 * user presses the return key.
	 */
	public void pause(Scanner scanner) {
		System.out.println("--- Push <Enter> to continue ---");
		scanner.nextLine();
	}
	
	/**
	 * Generate a random int that can be from 0 to max
	 */
	public static int randomInt(int max) {
		//return (int) (Math.random() * (maxNum + 1));
		return ( randomNumbers.nextInt( max + 1) );
	}
	
	/**
	 * Generate a random int that can be from min to max
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min, int max) {
		return ( min + randomNumbers.nextInt( max - min + 1 ) );
	}
	
	public void encounter( Ship player, Ship[] npcs ){
		boolean inputAccepted;
		boolean encounterOver = false;
		int liveNpcs;
		Scanner input = new Scanner(System.in);	// Should possibly pass input scanner to method instead of creating new scanner?
		
		do {
			
			for ( Ship npc : npcs ){
				//npc.reportStatus();
				if (npc.isAlive()) {
					switch ( randomInt(8) ){
					case 7:
						npc.raiseShields();
						break;
					case 8:
						npc.evasiveManeuvers( randomInt(-25,25), randomInt(-25,25), randomInt(-25,25) );
						break;
					default:	// npc bias towards attacking player is 7/9
						if ( !npc.attack(player) )	// if attack check fails then move closer instead
							npc.evasiveManeuvers( player.x + randomInt(-5,5), player.y + randomInt(-5,5), player.z + randomInt(-5,5) );
						break;
					}
				}
				//npc.reportStatus();
			}
			
			/**
			 * Check status of all npc ships and player ship
			 * if either is destroyed end encounter
			 */
			liveNpcs = 0;
			for ( Ship npc : npcs ){
				if ( npc.isAlive() )
					liveNpcs += 1;
			}
			if ( !player.isAlive() | liveNpcs == 0 ){
				encounterOver = true;
				continue;
			}
			
			/**
			 * Prompt player for action
			 * To-do add input parsing for multi part commands
			 * ex. "attack npc1" or "evade 1 5 3"
			 */
			inputAccepted = false;
			do {
				//player.reportStatus();
				System.out.println("< attack, shield, charge, evade >");
				switch ( input.next() ){
				case "attack":
					System.out.print("< ");
					int count=0;
					for (Ship npc : npcs){
						System.out.printf("%s ", npc.isAlive()?count:"");  // if npc is still alive print its number in the array
						count++;
					}
					System.out.print(">\n");
					if ( player.attack( npcs[ input.nextInt() ] ) ) // attack # ship in array (0-4)
						inputAccepted = true;
					break;
				case "shield":
					if (player.raiseShields() )
						inputAccepted = true;
					break;
				case "charge":
					if (player.chargeShields())
						inputAccepted = true;
					break;
				case "evade":  // accept input for specific location
					System.out.println("< x y z >");
					int[] loc = new int[3];
					for (int j = 0; j<3; j++){
						loc[j] = input.nextInt();
					}
					if ( player.evasiveManeuvers( loc[0], loc[1], loc[2] ) )
						inputAccepted = true;
					break;
				default:
					System.out.println("Input not accepted");
					break;
				}
			} while ( !inputAccepted );
			//player.reportStatus();
			

		} while ( !encounterOver );
		
		//close scanner... should maybe just pass main scanner instead?
		if (input != null) {
			input.close();
		}
	}
	
	public static void main(String[] args) {
		StarshipCaptain m = new StarshipCaptain();
		
		// Scanner for user input - used throughout program
		Scanner scanner = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		
		// NPC creation.
		Ship npc1 = new Ship("Enemy Light Frigate", 25, 8, 4, 10);
		Ship npc2 = new Ship("Enemy Destroyer", 30, 10, 6, 12);
		Ship npc3 = new Ship("Enemy Cruiser", 35, 12, 8, 14);
		Ship npc4 = new Ship();  // test random ship creation
		
		/**
		 * Create Ship array for passing to encounter later
		 * To-do make fleet object
		 */
		Ship[] npcs = {npc1, npc2, npc3, npc4};
	
		// Ask user for player name.
		String playerName;
		System.out.print("Enter your character's name: ");
		playerName = input.next();
		
		// Ask user for player health.
		int playerHealth;
		System.out.print("Enter your ship's starting health points (1-100): ");
		playerHealth = input.nextInt();
		
		// Ask user for player attack.
		int playerAttack;
		System.out.print("Enter your ship's attack points (1-100): ");
		playerAttack = input.nextInt();
		
		// Ask user for player defense.
		System.out.print("Enter your ship's defense points (1-100): ");
		int playerDefense = input.nextInt();
		
		// Ask user for player shield.
		System.out.print("Enter your ship's shield points (1-100): ");
		int playerShield = input.nextInt();
		
		// Create a Player object
		Ship player = new Ship(playerName, playerHealth, playerAttack, playerDefense, playerShield);
		
		// generate random ship for player stats between 30 and 50
		//Ship player = new Ship(playerName, randomInt(30, 50), randomInt(30, 50), randomInt(30, 50), randomInt(30, 50));
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Welcome " + player.name + "!");
		m.pause(scanner);
		
		
		
		int storyLength = 4;
		String[] pageText = new String[storyLength];
		
		// page 0 title page
		pageText[0] =
				"Starship Captain\n"
		+ "by Richard Whybrew\n";
		
		// page 1 text
		pageText[1] =
				"Yesterday you were a mere midshipman, learning the art of command.\n"
		+ "Today, you awoke to the sound of general quarters, only to find\n"
		+ "half the crew is dead, and you don't know why.\n"
		+ "None of your superiors are in sight. As you race to\n"
		+ "the bridge you realize the entire officer corps is likely dead.\n"
		+ "Amidst the corpses of the bridge crew,\n"
		+ "you see a senior gunner's mate manning the tactical station.\n";
		
		// page 2 text
		pageText[2] =
				"\"Sir, they're all dead! You've got to take command!\"\n"
		+ "You aren't able to process what's going on, but training takes over. \n"
		+ "\"Status report!\" you shout with a surprising amount of authority.\n"
		+ "\"I'm not sure sir! It looks like we got hit with some sort of energy\n"
		+ "weapon that bypassed the shields.There's no damage to the ship, just...\"\n";

		// page 3 text
		pageText[3] =
				"Suddenly the ship rocks. You're taking fire! You still don't know what's\n"
		+ "going on, but you'll have to fight first and find the answers later.\n"
		+ "You see three ships are within range...\n";

		/**
		 * Cycles through Page array printing out pages and pausing
		 */
		for ( String page : pageText ){
			System.out.print(page);
			m.pause(scanner);
		}
		
		m.encounter(player, npcs);
		
		player.reportStatus();
		// That's all she wrote, for now.
		if (player.hp > 0){
			System.out.println("You seem to have defeated the enemy ships. For now...");
		}
		else {
			System.out.println("Your ship, and all hands, are lost to the vastness of space.");
		}
		System.out.println("Game Over");
		
		// Scanners closed
		if (scanner != null) {
			scanner.close();
		}
		if (input != null) {
			input.close();
		}
		
	}


	
}