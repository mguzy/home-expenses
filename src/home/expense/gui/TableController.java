package home.expense.gui;

import home.expense.sql.SqlExtractor;
import home.expense.tables.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TableController implements Initializable, Refreshable {

	@FXML
	TableView<Transaction> transactionTable;

	@FXML
	TableColumn<Transaction, java.sql.Date> dateCol;

	@FXML
	TableColumn<Transaction, Double> amountCol;

	@FXML
	TableColumn<Transaction, String> categoryCol;

	@FXML
	TableColumn<Transaction, String> infoCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		infoCol.setCellValueFactory(new PropertyValueFactory<>("info"));

		refresh(LocalDate.now().withDayOfMonth(1), LocalDate.now());
	}
	
	@Override
	public void refresh(LocalDate from, LocalDate to) {
		transactionTable.setItems(SqlExtractor.getInstance().getTransactionList(from, to));
	}

}
