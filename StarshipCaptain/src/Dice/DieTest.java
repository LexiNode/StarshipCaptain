package Dice;

public class DieTest {

	public DieTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DieFactory df = new DieFactory();
		Die d20 = df.newDie("d20");
		Die d4 = df.newDie("d4");
		System.out.print(d4);
		
		for (int i=0; i<100; i++){
			System.out.print( d4.roll() );
			System.out.println( d20.roll() );
		}
	}

}
