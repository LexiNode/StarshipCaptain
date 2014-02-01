package Dice;

public class Dice {

	Die[] dice;
	
	public Dice() {
		// TODO Auto-generated constructor stub
	}
	
	public char[] roll(){
		char[] rolls = new char[dice.length];
		for (int i=0; i<dice.length; i++){
			rolls[i] = dice[i].roll();
		}
		return rolls;
	}

}
