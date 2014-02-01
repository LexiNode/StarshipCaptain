package starshipCaptain;

import java.util.Scanner;

import Dice.Die;

public class Encounter {

	private Die die;
	public Encounter() {
		// TODO Auto-generated constructor stub
	}
	
	public void start(){
		
	}
	
	public void encounter( Ship player, Ship[] npcs ){
		die = new Die();
		
		boolean inputAccepted;
		boolean encounterOver = false;
		int liveNpcs;
		Scanner input = new Scanner(System.in);	// Should possibly pass input scanner to method instead of creating new scanner?
		
		do {
			
			for ( Ship npc : npcs ){
				//npc.reportStatus();
				if (npc.isAlive()) {
					switch ( die.nextInt(0,8) ){
					case 7:
						npc.raiseShields();
						break;
					case 8:
						npc.evasiveManeuvers( die.nextInt(-25,25), die.nextInt(-25,25), die.nextInt(-25,25) );
						break;
					default:	// npc bias towards attacking player is 7/9
						if ( !npc.attack(player) )	// if attack check fails then move closer instead
							npc.evasiveManeuvers( player.getX() + die.nextInt(-5,5), player.getY() + die.nextInt(-5,5), player.getZ() + die.nextInt(-5,5) );
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

}
