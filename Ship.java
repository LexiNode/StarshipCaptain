/**
 * Generic Ship Class
 * used to create NPCs and Players
 * @author hookerjs
 *
 */
public class Ship {
	
	String name;	// duh
	
	int atk;	// attack points
	int hp;		// hit points
	int def;	// defense points
	int shd;	// shield points
	
	int seq;	// sequence points
	
	// Status indicators
	boolean attack;
	boolean evade;
	boolean shield;

	//location
	int x,y,z;
	

	/**
	 * Default constructor
	 * produces random stats for ship
	 */
	public Ship(){
		// TO-DO random name
		this.name = "RandomShip";
		this.hp = StarshipCaptain.randomInt(25, 35);
		this.atk = StarshipCaptain.randomInt(8, 12);
		this.def = StarshipCaptain.randomInt(4, 8);
		this.shd = StarshipCaptain.randomInt(10, 20);
		
		this.attack = this.evade = this.shield = false;
		
		// random location
		this.x = StarshipCaptain.randomInt(-25, 25);
		this.y = StarshipCaptain.randomInt(-25, 25);
		this.z = StarshipCaptain.randomInt(-25, 25);		
	}

	public Ship(String name, int p2, int p3, int p4, int p5) {
		this.name = name;
		hp = p2;
		atk = p3;
		def = p4;
		shd = p5;
		
		this.attack = this.evade = this.shield = false;
	}
	
	public boolean attack(Ship target){
		int range = this.checkRange(target);
		int atk = StarshipCaptain.randomInt(this.atk);
		
		this.attack = true;
		this.atk += 5;
		
		if ( range > 25 ){
			System.out.println(target.name + " is too far for " + this.name + " to attack");
			return false;
		}
		else {
			this.lowerShields();
			this.maneuver();
		}
		if (atk < target.def  && range > 5) {
			System.out.println("\t" + target.name + " dodges the attack!");
			return true;
		}
		else {
			System.out.printf("%s attacks %s at range of %d for %d points. ", this.name, target.name, range, atk);
		}		
		if ( range <= 5 ){  
			atk += 5;	// extra damage for close range
			System.out.printf("+ 5 for close range! ");
		}

		if (target.shield == true){
			
			if (atk < target.shd){
				System.out.printf("Shields absorb %d points leaving %d points left.\n", atk, target.shd -= atk);
				return true;
			}
			else if (atk >= target.shd){
				System.out.printf("Remaining %d points of shielding destroyed. ", target.shd);
				atk -= target.shd;
				target.shd = 0;
				target.shield = false;
			}
		}
		if (target.shield == false){
			if (atk >= target.hp){
				System.out.printf("Remaining %d hit points destroyed.\n", target.hp);
				target.hp = 0;
				return true;
			}
			else if(atk < target.hp){
				System.out.printf("%d points of damage caused remaining %d hp.\n", atk, target.hp-=atk);
			}
		}

			return true;
	}

	
	public void chargeAttack(){
		this.atk -=5;
		this.attack = false;
	}
	
	public void evasiveManeuvers(){

		this.def += 5;
		this.lowerShields();
		this.chargeAttack();
		this.evade = true;
	}
	
	public void maneuver(){
		this.def -= 5;
		this.evade = false;
	}
	public void maneuver(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		System.out.printf("moved to (%d, %d, %d)\n",x ,y ,z);
		this.maneuver();
	}
	
	public boolean raiseShields(){
		if (this.shd <= 0){
			System.out.printf("%s has no shields to raise but tries anyway.\n", this.name);
			return false;
		}
		else{
			this.maneuver();
			this.chargeAttack();
			this.shield = true;	
			return true;
		}
	}
	
	public void lowerShields(){
		this.shield = false;
	}

	public int checkRange(Ship target){
		int out = (int) Math.sqrt(Math.pow( (this.x - target.x) , 2 ) + Math.pow( (this.y - target.y) , 2 ) + Math.pow( (this.z - target.z) , 2 ));
		return out;
	}
}
