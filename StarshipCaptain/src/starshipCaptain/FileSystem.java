package starshipCaptain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class FileSystem {

	public FileSystem() {
		// TODO Auto-generated constructor stub
	}
	
	public static Story readFile( File file ) throws FileNotFoundException{
		Story story = null;
		FileInputStream fis;
		ObjectInputStream ois;
		try {		
			if ( file.exists() & file.isFile() ) {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				story = (Story) ois.readObject();
				ois.close();
				fis.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return story;
	}
	
	public static void saveFile( Story story, File file ){
		FileOutputStream fos;
		ObjectOutputStream oos;
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(story);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
