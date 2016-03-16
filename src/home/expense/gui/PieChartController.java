package home.expense.gui;

import home.expense.sql.SqlExtractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PieChartController implements Initializable, Refreshable {

	@FXML
	PieChart pieChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refresh(LocalDate.now().withDayOfMonth(1), LocalDate.now());
	}
	
	@Override
	public void refresh(LocalDate from, LocalDate to) {
		pieChart.setData(SqlExtractor.getInstance().getMergedTransactionList(from, to));
	}

}
