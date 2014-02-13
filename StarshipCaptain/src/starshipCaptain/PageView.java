package starshipCaptain;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PageView {

	private static final Dimension pnlSize = new Dimension(800,600);
	private static final Dimension btnSize = new Dimension(300,150);

	public PageView() {
		// TODO Auto-generated constructor stub
	}
	private JPanel panel;
	private BoxLayout layout;
	private JTextArea content;
	private JPanel btnPanel;
	private GridLayout btnLayout;
	private JButton[] choiceBtn;
	
	private PageController pageController;
	
	public PageController getPageController() {
		return pageController;
	}
	public void setPageController(PageController pageController) {
		this.pageController = pageController;
	}
	public void loadContent(String content){
		this.content.setText(content);
	}
	public void loadChoices(Choice[] choices){
		choiceBtn = new JButton[choices.length];
		for (int i=0; i<choices.length; i++){
			choiceBtn[i] = new JButton(choices[i].getContent());
			choiceBtn[i].setAlignmentY(Component.BOTTOM_ALIGNMENT);
			choiceBtn[i].setPreferredSize(btnSize);
			btnPanel.add(choiceBtn[i]);
		}
	}
	public void initPanel(){
		panel.setPreferredSize(pnlSize);
		layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		btnLayout = new GridLayout(2,0);
		btnLayout.setHgap(5);
		btnLayout.setVgap(5);
		panel.setLayout(layout);
		panel.add(content);
		panel.add(btnPanel);
	}
}
