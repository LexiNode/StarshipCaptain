package Dice;

public class DieFactory {

	public DieFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Die newDie( String die ){
		String[] num;
		int faces;
		/* regex for d1 through d100
		 * \d shows as error in eclipse ide so used [0-9] instead
		 */
		if ( die.matches("[dD][1-9][0-9]{0,1}(?![0-9])|100") ){
			num = die.split("[dD]");
			if (num.length == 1){
				faces = Integer.parseInt(num[0]);
				System.out.println(faces);
				return (new Die(faces));
			}
		}
		return null;	// if String doesnt match the regex eg. d20, d100, D4, etc
	}
}
