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
	 * Generate a random int that can be from 0 to
	 * the number passed in as an argument.
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
	
	/**
	 * Simulates a round between the player and the npc, where
	 * the NPC is attacking first.
	 */
	public void playRound(int roundNum, Ship npc, Ship player) {
		System.out.println("[[ ROUND " + roundNum + " ]]");

		int randomNpcATK = randomInt(npc.atk);
		int randomPlayerATK = randomInt(player.atk);
		
		// NPC attacks
		System.out.println(npc.name + " attacks for " + randomNpcATK + " points!");
		player.hp -= randomNpcATK;
		System.out.println(player.name + " has " + player.hp + "hp left.");

		// Player attacks
		System.out.println(player.name + " attacks for " + randomPlayerATK + " points!");
		
		// Check for defense save
		if (randomPlayerATK < npc.def) {
			System.out.println("\t" + npc.name + " blocks the attack!");
		} else {
			npc.hp -= randomPlayerATK;
		}
		
		System.out.println(npc.name + " has " + npc.hp + "hp left.");

		System.out.println();
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
		Ship npc4 = new Ship();
	
		// Ask user for player name.
		String playerName;
		System.out.print("Enter your character's name: ");
		playerName = input.next();
/**		
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
		
		// Create a Player object
		Ship player = new Ship(playerName, playerHealth, playerAttack, playerDefense);
**/
		Ship player = new Ship(playerName, randomInt(30, 50), randomInt(30, 50), randomInt(30, 50), randomInt(30, 50));
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Welcome " + player.name + "!");
		m.pause(scanner);

		
		// Display the story
		System.out.println("Yesterday you were a mere midshipman, learning the art of command.");
		System.out.println("Today, you awoke to the sound of general quarters, only to find"); 
		System.out.println("half the crew is dead, and you don't know why.");
		System.out.println("None of your superiors are in sight. As you race to");
		System.out.println("the bridge you realize the entire officer corps is likely dead.");
		System.out.println("Amidst the corpses of the bridge crew,");
		System.out.println("you see a senior gunner's mate manning the tactical station.\n");

		m.pause(scanner);
		
		System.out.println("\"Sir, they're all dead! You've got to take command!\"");
		System.out.println("You aren't able to process what's going on, but training takes over. ");
		System.out.println("\"Status report!\" you shout with a surprising amount of authority.");
		System.out.println("\"I'm not sure sir! It looks like we got hit with some sort of energy");
		System.out.println("weapon that bypassed the shields.There's no damage to the ship, just...\"\n");

		m.pause(scanner);

		System.out.println("Suddenly the ship rocks. You're taking fire! You still don't know what's");
		System.out.println("going on, but you'll have to fight first and find the answers later.");
		System.out.println("You see three ships are within range...\n");

		m.pause(scanner);
		
		// Battle a few rounds against the NPCs. Limited to 20 rounds.
		int round = 1;
		while (round <= 20 && player.hp > 0 && npc1.hp > 0)
		{
			//m.playRound(round++, npc1, player);
			switch (StarshipCaptain.randomInt(2)){
			case 0:
				npc1.attack(player);
				break;
			case 1:
				npc1.raiseShields();
				break;
			case 2:
				npc1.maneuver(StarshipCaptain.randomInt(-25,25), StarshipCaptain.randomInt(-25,25), StarshipCaptain.randomInt(-25,25));
				break;
			}
			System.out.println("< attack, shield, evade >");
			switch (scanner.next()){
			case "attack":
				player.attack(npc1);
				break;
			case "shield":
				player.raiseShields();
				break;
			case "evade":
				player.evasiveManeuvers();
				break;
			}
			
			m.pause(scanner);
		}
		// Second NPC
		int secondRound = 1;
		while (secondRound <= 20 && player.hp > 0 && npc2.hp > 0)
		{
			m.playRound(secondRound++, npc2, player);
			m.pause(scanner);
		}
		// Third NPC
		int thirdRound = 1;
		while (thirdRound <= 20 && player.hp > 0 && npc3.hp > 0)
		{
			m.playRound(thirdRound++, npc3, player);
			m.pause(scanner);
		}

		// That's all she wrote, for now.
		if (player.hp > 0){
			System.out.println("You seem to have defeated the enemy ships.");
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