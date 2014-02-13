package starshipCaptain;

public class Choice {

	private String content;
	private int targetPage;
	
	public Choice() {
		// TODO Auto-generated constructor stub
	}
	
	public Choice(String content, String command, int targetPage) {
		this.content = content;
		this.targetPage = targetPage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

}
