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
	public int randomNumber(int maxNum) {
		return (int) (Math.random() * (maxNum + 1));
	}
	
	/**
	 * Simulates a round between the player and the npc, where
	 * the NPC is attacking first.
	 */
	public void playRound(int roundNum, NPC npc, Player player) {
		System.out.println("[[ ROUND " + roundNum + " ]]");

		int randomNpcATK = randomNumber(npc.atk);
		int randomPlayerATK = randomNumber(player.atk);
		
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
		NPC npc1 = new NPC("Enemy Light Frigate", 25, 8, 4);
		NPC npc2 = new NPC("Enemy Destroyer", 30, 10, 6);
		NPC npc3 = new NPC("Enemy Cruiser", 35, 12, 8);
		
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
		
		// Create a Player object
		Player player = new Player(playerName, playerHealth, playerAttack);


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
		m.playRound(round++, npc1, player);
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
		
	}


	
}