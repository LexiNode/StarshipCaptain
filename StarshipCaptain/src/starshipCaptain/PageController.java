package starshipCaptain;

public class PageController {

	private PageView pageView;
	private Page page;
	
	public PageController() {
		// TODO Auto-generated constructor stub
	}
	
	public PageController(PageView pageView, Page page) {
		this.pageView = pageView;
		this.page = page;
		this.pageView.setPageController(this);
	}

	public void updateView(){
		pageView.loadContent(page.getContent());
		pageView.loadChoices(page.getChoices());
	}
	
	
}
