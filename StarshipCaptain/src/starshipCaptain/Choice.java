package starshipCaptain;

public class Choice {

	String content;
	String command;
	int targetPage;
	
	public Choice() {
		// TODO Auto-generated constructor stub
	}
	
	public Choice(String content, String command, int targetPage) {
		this.content = content;
		this.command = command;
		this.targetPage = targetPage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

}
