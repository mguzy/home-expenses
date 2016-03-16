package home.expense.main;

import home.expense.gui.Loader;
import home.expense.sql.BasicDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
//	private Loader<GridPane> loader = new Loader<>();
	
	public static void main(String[] args) {
		launch(args);
		BasicDao.close();
	}

	@Override
    public void start(Stage primaryStage) throws Exception{

		Scene scene;
		scene = new Scene(FXMLLoader.load(getClass().getResource("/home/expense/gui/MainWindowView.fxml")));
		primaryStage.setScene(scene);
        //primaryStage.setMinWidth(1200);
       // primaryStage.setMinHeight(600);
        primaryStage.show();
        
        
    }

}