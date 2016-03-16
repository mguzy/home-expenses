package home.expense.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

	@FXML
	private Parent embeddedTableView;
	@FXML
	private TableController embeddedTableViewController;

	@FXML
	private Parent embeddedPieChartView;
	@FXML
	private PieChartController embeddedPieChartViewController;

	@FXML
	private Parent embeddedLineChartView;
	@FXML
	private LineChartController embeddedLineChartViewController;

	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker fromDate;
	@FXML
	private DatePicker toDate;
	@FXML
	private CheckBox expenseCheck;
	@FXML
	private CheckBox incomeCheck;
	@FXML
	private Button incomeButton;
	@FXML
	private Button categoryButton;

	private Loader<GridPane> loader = new Loader<>();
	
	private final ArrayList<Refreshable> refreshable = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refreshable.add(embeddedTableViewController);
		refreshable.add(embeddedPieChartViewController);
		refreshable.add(embeddedLineChartViewController);
		fromDate.setValue(LocalDate.now().withDayOfMonth(1));
		toDate.setValue(LocalDate.now());
	}

	@FXML
	public void dateChange() {
		for(Refreshable r : refreshable)
			r.refresh(fromDate.getValue(), toDate.getValue());
	}

	@FXML
	public void transactionClicked() {
		Scene scene = new Scene(loader.getScene("TransactionView.fxml"));
		PopUp popUp = new PopUp(scene);
		popUp.pop();
		dateChange();
	}

	@FXML
	public void categoryClicked() {
		Scene scene = new Scene(loader.getScene("AddCategoryView.fxml"));
		PopUp popUp = new PopUp(scene);
		popUp.pop();
		dateChange();

	}

}
