package Dice;

import java.util.Random;

public class Die{

	private static final char[] D6_PIP = {'⚀','⚁','⚂','⚃','⚄','⚅'};
	//private static final char[] CROWN_N_ANCHOR = {'♠','♣','⚓︎','♡','♢','♔'︎︎︎}︎;
	private Random r;
	
	char[] faces;
	int numfaces;
	
	public char[] getFaces() {
		return faces;
	}

	public void setFaces(char[] faces) {
		this.faces = faces;
	}

	public int getNumfaces() {
		return numfaces;
	}

	/**
	 * Generate a random int that can be from min inclusive to max inclusive
	 */
	public int nextInt(int min, int max) {
		return ( r.nextInt( max - min + 1 ) + min );
	}
	// default die "d6"
	public Die() {
		this( D6_PIP );
		// TODO Auto-generated constructor stub
	}
	
	//plain die with @faces numbers on faces
	public Die( int faces ) {
		this.faces = new char[faces];
		for (int i=0; i<faces; i++)
			this.faces[i] = (char) (i+1);
		r = new Random();
	}
	
	public Die( char[] faces ) {
		r = new Random(89123);
		this.faces = faces;
	}
	
	public char roll(){
		return faces[r.nextInt(faces.length) +1];
	}
}
