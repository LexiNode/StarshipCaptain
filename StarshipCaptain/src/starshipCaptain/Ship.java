package starshipCaptain;

import java.util.Scanner;

import Dice.*;

/**
 * Generic Ship Class
 * used to create NPCs and Players
 * @author hookerjs
 */

public class Ship {
	
	private String name;	// duh
	
	private int atk;	// attack points
	private int hp;		// hit points
	private int def;	// defense points
	
	private int shdMax;	// max shield points
	private int shd;	// current shield points
	private int shdChgRt;	// shield charge rate;
	
	//private int personel;
	
	//int seq;	// sequence points
	
	// Status indicators
	private boolean attack;
	private boolean evade;
	private boolean shield;
	private boolean charge;

	//location
	private int x,y,z;
	
	private Die die;

	public Ship(){
		Scanner input = new Scanner(System.in);
		
		// Ask user for player name.
		System.out.print("Enter your character's name: ");
		this.name = input.next();
		
		// Ask user for player health.
		System.out.print("Enter your ship's starting health points (1-100): ");
		this.hp = input.nextInt();
		
		// Ask user for player attack.
		System.out.print("Enter your ship's attack points (1-100): ");
		this.atk = input.nextInt();
		
		// Ask user for player defense.
		System.out.print("Enter your ship's defense points (1-100): ");
		this.def = input.nextInt();
		
		// Ask user for player shield.
		System.out.print("Enter your ship's shield points (1-100): ");
		this.shd = input.nextInt();
		
		if (input != null) {
			input.close();
		}
		
		this.shdMax = this.shd;
		this.shdChgRt = this.shdMax / 10;
		
		// default
		this.attack = this.evade = this.shield = false;
		
		this.x = 0;
		this.y = 0;
		this.z = 0;
		
		this.die = new Die();
		
	}
	
	public Ship(String name, int hp, int atk, int def, int shd) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.shd = shd;
		this.shdMax = this.shd;
		this.shdChgRt = this.shdMax / 10;
		
		// default
		this.attack = this.evade = this.shield = false;
		
		this.x = 0;
		this.y = 0;
		this.z = 0;
		
		this.die = new Die();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getShdMax() {
		return shdMax;
	}

	public void setShdMax(int shdMax) {
		this.shdMax = shdMax;
	}

	public int getShd() {
		return shd;
	}

	public void setShd(int shd) {
		this.shd = shd;
	}

	public int getShdChgRt() {
		return shdChgRt;
	}

	public void setShdChgRt(int shdChgRt) {
		this.shdChgRt = shdChgRt;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public boolean isEvade() {
		return evade;
	}

	public void setEvade(boolean evade) {
		this.evade = evade;
	}

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Die getDie() {
		return die;
	}

	public void setDie(Die die) {
		this.die = die;
	}

	// displays ship name and all stats for debugging
	public void reportStatus(){
		System.out.println(this.name);
		System.out.printf("atk\thp\tdef\tshd\t\tattack\tevade\tshield\tx\ty\tz\n");
		System.out.printf("%d\t%d\t%d\t%d\t\t%s\t%s\t%s\t%d\t%d\t%d\n", this.atk, this.hp, this.def, this.shd, this.attack?"true":"false", this.evade?"true":"false", this.shield?"true":"false", this.x, this.y, this.z );
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean attack(Ship target){
		int range = this.checkRange(target);
		int atk = die.nextInt(0,this.atk);
		
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
