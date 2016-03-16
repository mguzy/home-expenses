package home.expense.gui;

import home.expense.tables.Category;
import home.expense.sql.SqlExtractor;
import home.expense.sql.SqlInjector;
import home.expense.tables.Transaction;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class TransactionController implements Initializable {

	@FXML
	private Label amountLabel;

	@FXML
	private Label categoryLabel;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	@FXML
	private TextField amountField;

	@FXML
	private TextField infoArea;

	@FXML
	private DatePicker datePick;

	@FXML
	private ComboBox<Category> categoryComBox;

	private Transaction t = new Transaction();

	@FXML
	public void save() {

		String amount = amountField.getCharacters().toString().replace(',', '.');
		Category category = categoryComBox.getValue();
		if (!amount.isEmpty() && category != null) {
			t.setAmount(Double.parseDouble(amount));
			t.setDate(Date.valueOf(datePick.getValue()));
			t.setCategory(category);
			t.setInfo(infoArea.getCharacters().toString());

			SqlInjector.getInstance().addToDb(t);

			Stage stage = (Stage) saveButton.getScene().getWindow();
			stage.close();
		} else {
			if (amount.isEmpty())
				amountField.setPromptText("Wpisz kwotę!");
			if (category == null)
				categoryComBox.setPromptText("Wybierz kategorię!");
		}

	}

	@FXML
	public void close() {
		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Category> categories = SqlExtractor.getInstance().getCategoryList(false);

		if (categories.isEmpty())
			categoryComBox.setPromptText("Najpierw stwórz kategorie!");
		else {
			categoryComBox.setPromptText("Wybierz z listy");
			categoryComBox.getItems().addAll(categories);
		}

		datePick.setValue(LocalDate.now());

		UnaryOperator<TextFormatter.Change> filter;
		filter = new UnaryOperator<TextFormatter.Change>() {
			@Override
			public TextFormatter.Change apply(TextFormatter.Change change) {
				String text = change.getText();
				for (int i = 0; i < text.length(); i++)
					if (!(Character.isDigit(text.charAt(i)) || text.charAt(i) == ','))
						return null;
				return change;
			}
		};
		amountField.setTextFormatter(new TextFormatter<String>(filter));
	}

}
