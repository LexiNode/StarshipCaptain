package starshipCaptain;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
This is just a beginning.
 *
 * @author Rich Whybrew
 *
 */
 
 // Begin awesome program
 
public class StarshipCaptain implements ActionListener, MouseListener {
	
	private JFrame mainFrame = new JFrame("Starship Captain");
	private JPanel panel = new JPanel();
	private JButton newBtn = new JButton("New Story");
	private JButton selectBtn = new JButton("Select Story");
	private JButton beginBtn = new JButton("Begin Story");
	

	
	private static final Dimension btnDim = new Dimension(100,25);
	
	public enum MainMenuOption {
        NEW("New story"), SELECT("seleCt story"), BEGIN("Begin story"), QUIT("Quit");
        private String value;

        private MainMenuOption(String value) {
                this.value = value;
        }
	};
	

	
	public boolean[] mainMenuOptionActive = {true, true, false, true};
	
	/**
	 * This method displays a pause method and waits until the
	 * user presses the return key.
	 */
	public void pause(Scanner scanner) {
		System.out.println("--- Push <Enter> to continue ---");
		scanner.nextLine();
	}
	
	private void loadGUI(){
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(800,600));
		arrangeGUI(mainFrame);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void arrangeGUI(JFrame frame){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		newBtn.setPreferredSize(btnDim);
		selectBtn.setPreferredSize(btnDim);
		beginBtn.setPreferredSize(btnDim);
		newBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		beginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		newBtn.addMouseListener(this);
		panel.add(newBtn);
		panel.add(selectBtn);
		panel.add(beginBtn);
		frame.add(panel);
	}
	public static void main(String[] args) {
		
		StarshipCaptain captain = new StarshipCaptain();
		captain.loadGUI();
		
		
		


		
		captain.run();

	}
	
	public void run(){
		// Scanner for user input - used throughout program
		Scanner input = new Scanner(System.in);
		String in;
		
		Story story = null;

		System.out.println("Starship Captain\n");
		System.out.println("by lexiNODE\n\n");
		/*
		System.out.println("New story");
		System.out.println("seleCt story");
		//System.out.println("Begin story");
		System.out.println("Quit");
		*/
		
		for (MainMenuOption value: MainMenuOption.values()){
			if (this.mainMenuOptionActive[value.ordinal()]){
				System.out.println(value.name());
			}
		}
		while (((in = input.next().toUpperCase()) != "Q") & (in != "QUIT")) {
			if ((in == "N") | (in == "NEW")){
				story = new Story();
			}
			if ((in == "C") | (in == "SELECT")){
				File file = new File("starship.txt");
				story = null;
				try {
					story = FileSystem.readFile(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (((in == "B") | (in == "BEGIN")) && (mainMenuOptionActive[2])){
				story.read();
			}
		}
		
		

		

		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Welcome " + story.getReader().getName() + "!");
		this.pause(input);
		
		// NPC creation.
		Ship npc1 = new Ship("Enemy Light Frigate", 25, 8, 4, 10);
		Ship npc2 = new Ship("Enemy Destroyer", 30, 10, 6, 12);
		Ship npc3 = new Ship("Enemy Cruiser", 35, 12, 8, 14);
		
		/**
		 * Create Ship array for passing to encounter later
		 * To-do make fleet object
		 */
		Ship[] npcs = {npc1, npc2, npc3};
		
		/**
		 * Cycles through Page array printing out pages and pausing
		 */
		for ( Page page : story.getPages() ){
			System.out.print(page);
			
			this.pause(input);
		}
		//SaveStoryXML.writeXMLToFile( createXMLStory( story ) );
		//m.encounter(player, npcs);
		
		story.getReader().reportStatus();
		// That's all she wrote, for now.
		if (story.getReader().getHp() > 0){
			System.out.println("You seem to have defeated the enemy ships. For now...");
		}
		else {
			System.out.println("Your ship, and all hands, are lost to the vastness of space.");
		}
		System.out.println("Game Over");
		
		// Scanner closed
		if (input != null) {
			input.close();
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}