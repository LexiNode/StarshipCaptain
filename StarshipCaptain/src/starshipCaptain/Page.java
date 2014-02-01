package starshipCaptain;

import java.io.Serializable;

public class Page implements Serializable{
	
	/**
	 * serialization for file storage and retrieval maintaining version changes 
	 */
	private static final long serialVersionUID = 8446616433597840126L;
	
	String title = "";
	private String content;
	private int pageNumber;
	Choice[] choices = {};
	Ship[] encounter = {};

	// Default page
	public Page( String content, int number ){
		setContent(content);
		setPageNumber(number);
	}
	
	// Title page
	public Page( String title, String content, int number){
		this.title = title;
		this.content = content;
		this.pageNumber = number;
	}
	
	// Decision page
	public Page( String content, int number, Choice[] choices){
		this.content = content;
		this.pageNumber = number;
		this.choices = choices;
	}
	
	//Encounter page
	public Page( String content, int number, Ship[] encounter){
		this.content = content;
		this.pageNumber = number;
		this.encounter = encounter;
	}
	
	public void setTitle( String title ) {
		this.title = title;
	}
	
	public String getTitle( ) {
		return this.title;
	}
	
	public void setContent( String content ){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setPageNumber( int number ){
		this.pageNumber = number;
	}
	
	public int getPageNumber(){
		return this.pageNumber;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.title).append("\n\n").append(content).append("\n\n");
		if (this.encounter.length > 0) {
			
		}
		if ( this.choices.length > 0 ) {
			for ( Choice choice : this.choices ) {
				sb.append(choice.getCommand()).append(" > ").append(choice.getContent()).append(" - goto page ").append(choice.getTargetPage()).append("\n");
			}
		}

		
		sb.append("\t\t\t").append(this.pageNumber).append("\n");
		return sb.toString();
	}
}
