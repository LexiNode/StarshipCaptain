/**
 * Generic Ship Class
 * used to create NPCs and Players
 * @author hookerjs
 */

public class Ship {
	
	String name;	// duh
	
	int atk;	// attack points
	int hp;		// hit points
	int def;	// defense points
	
	int shdMax;	// max shield points
	int shd;	// current shield points
	int shdChgRt;	// shield charge rate;
	
	//int seq;	// sequence points
	
	// Status indicators
	boolean attack;
	boolean evade;
	boolean shield;
	boolean charge;

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
		this.shdMax = this.shd;
		this.shdChgRt = this.shdMax / 10;
		
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
		shdMax = shd;
		this.shdChgRt = this.shdMax / 10;
		
		this.attack = this.evade = this.shield = false;
		
		// random location
		this.x = StarshipCaptain.randomInt(-25, 25);
		this.y = StarshipCaptain.randomInt(-25, 25);
		this.z = StarshipCaptain.randomInt(-25, 25);
	}
	
	// displays ship name and all stats for debugging
	public void reportStatus(){
		System.out.println(this.name);
		System.out.printf("atk\thp\tdef\tshd\t\tattack\tevade\tshield\tx\ty\tz\n");
		System.out.printf("%d\t%d\t%d\t%d\t%s\t%s\t%s\t%d\t%d\t%d\n", this.atk, this.hp, this.def, this.shd, this.attack?"true":"false", this.evade?"true":"false", this.shield?"true":"false", this.x, this.y, this.z );
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean attack(Ship target){
		int range = this.checkRange(target);
		int atk = StarshipCaptain.randomInt(this.atk);
		
		/** 
		 * too far
		 * to-do add return for different input
		 */
		System.out.printf("%s targets %s.\n", this.name, target.name );
		if ( !target.isAlive() ){
			System.out.printf("%s is already destroyed.\n", target.name);
			return false;
		}
		if ( range > 25 ){
			System.out.println(target.name + " is too far for " + this.name + " to attack");
			return false;  // return to input loop for different input
		}
		else { // in range lower shields and slow for attack
			this.removeStatus();
			this.atk += 5;
			this.attack = true;
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
				target.shd -= atk;
				System.out.printf("Shields hit! Shields at %d percent.\n", target.shieldPercent());
				return true;
			}
			else if (atk >= target.shd){
				System.out.println("Shields destroyed!");
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

	public boolean evasiveManeuvers(int x, int y, int z){
		this.removeStatus();
		this.def += 5;
		this.x = x;
		this.y = y;
		this.z = z;
		this.evade = true;
		System.out.printf("%s moved to <%d, %d, %d>\n",this.name, x ,y ,z);
		return true;
	}
	
	public void removeStatus(){
		if (this.attack){
			this.atk -= 5;
			this.attack = false;
		}
		if (this.shield){
			this.shield = false;
		}
		if (this.evade){
			this.def -= 5;
			this.evade = false;
		}
		if (this.charge){
			this.charge = false;
		}
	}
	
	public boolean raiseShields(){		
		if (this.shd <= 0){
			System.out.printf("%s has no shields to raise but tries anyway.\n", this.name);
			return false;  // to-do make it return or ask for different action
		}
		else{
			System.out.printf("%s has raised shields. %d percent remaining.\n", name, this.shieldPercent());
			this.removeStatus();
			this.shield = true;	
			return true;
		}
	}
	
	public int shieldPercent(){
		return (int)( 100 * ( (double)this.shd / (double)this.shdMax) );
	}
	
	public boolean chargeShields(){
		this.removeStatus();
		this.charge = true;
		if ( (this.shd += this.shdChgRt) > this.shdMax )
			this.shd = this.shdMax;
		System.out.printf("%s is charging shields. %d percent\n", this.name, this.shieldPercent());
		return true;
	}
	
	/**
	 * Return range to target 
	 * http://en.wikipedia.org/wiki/Euclidean_vector#Length
	 * @param target
	 * @return
	 */
	public int checkRange(Ship target){
		int out = (int) Math.sqrt(Math.pow( (this.x - target.x) , 2 ) + Math.pow( (this.y - target.y) , 2 ) + Math.pow( (this.z - target.z) , 2 ));
		return out;
	}
	
	public boolean isAlive() {  // its alive!! horrible semantics
		if ( this.hp > 0 )
			return true;
		else
			return false;
	}
}
