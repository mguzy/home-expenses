package home.expense.gui;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp extends Stage {
	
	public PopUp(Scene scene){
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
		this.setScene(scene);
	}
	
	public void pop(){;
        this.showAndWait();
	}
}
