package home.expense.gui;

import home.expense.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Loader<T extends Parent> {
	
	    public Loader() {
	    }
	    
	    @SuppressWarnings("unchecked")
		public T getScene(String fxmlName){
	    	FXMLLoader loader = new FXMLLoader();
	        //loader.setLocation(Main.class.getResource(fxmlName));
			//try {
				loader.setLocation(getClass().getResource(fxmlName));
		//	} catch (IOException e) {
			//	e.printStackTrace();
			//}
			try {
				return (T)loader.load();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	    }
}
