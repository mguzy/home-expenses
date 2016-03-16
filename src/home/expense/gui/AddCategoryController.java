package home.expense.gui;

import home.expense.tables.Category;
import home.expense.sql.SqlExtractor;
import home.expense.sql.SqlInjector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

	@FXML
	private TextField newCatField;
	
	@FXML
	private ListView<Category> categoryList;

	@FXML
	private Button addButton;

	@FXML
	private Button cancelButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refresh();
	}

	@FXML
	public void add() {
		Category c = new Category(newCatField.getCharacters().toString());
		SqlInjector.getInstance().addToDb(c);
		refresh();
	}

	public void refresh() {
		categoryList.setItems(SqlExtractor.getInstance().getCategoryList());
	}

}
