package home.expense.gui;

import home.expense.sql.SqlExtractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LineChartController implements Initializable, Refreshable {

	@FXML
	private NumberAxis amountAxis;

	@FXML
	private CategoryAxis dateAxis;

	@FXML
	private LineChart<String, Number> lineChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refresh(LocalDate.now().withDayOfMonth(1), LocalDate.now());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(LocalDate from, LocalDate to) {
		lineChart.getData().clear();
		lineChart.getData().addAll(SqlExtractor.getInstance().getMergedTransactionSeries(from, to));
	}

}
