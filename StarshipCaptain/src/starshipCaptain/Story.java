package starshipCaptain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Story implements Serializable{
	
	/**
	 * serialization for file storage and retrieval maintaining version changes 
	 */
	private static final long serialVersionUID = -7970236073389277618L;
	
	private String title;
	private String author;
	ArrayList<Page> pages;
	
	private Ship reader;
	
	private File file;
	private boolean changed;

	public Story() {
		// TODO Auto-generated constructor stub
		this.setReader(new Ship());
	}
	
	public Story( String title, String author ){
		this();
		setTitle(title);
		setAuthor(author);
		this.pages = new ArrayList<Page>();
	}
	
	public void read(){
		Scanner input = new Scanner(System.in);
		String in;
		while (((in = input.next().toUpperCase()) != "Q") & (in != "QUIT")) {
			
		}
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle( String title ){
		this.title = title;
	}
	
	public String getAuthor(){
		return author;
	}
	public void setAuthor( String s ){
		this.author = s;
	}
	
	public Page getPage( int pageNumber ){
		return pages.get(pageNumber);
	}
	
	public ArrayList<Page> getPages(){
		return pages;
	}
	
	public void addPage( Page page ){
		pages.add(page);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public Ship getReader() {
		return reader;
	}

	public void setReader(Ship reader) {
		this.reader = reader;
	}
}
