package home.expense.sql;

import home.expense.tables.Category;
import home.expense.tables.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SqlExtractor extends BasicDao {

	private static SqlExtractor instance = null;

	private SqlExtractor() {
	}

	public static SqlExtractor getInstance() {
		if (instance == null) {
			instance = new SqlExtractor();
		}
		return instance;
	}

	public ObservableList<Transaction> getTransactionList(LocalDate from, LocalDate to) {

		TypedQuery<Transaction> query = super.getEntityManager()
				.createQuery("SELECT t FROM Transaction t WHERE t.date BETWEEN :from AND :to", Transaction.class);

		query.setParameter("from", java.sql.Date.valueOf(from));
		query.setParameter("to", java.sql.Date.valueOf(to));

		return FXCollections.observableList(query.getResultList());
	}

	public ObservableList<PieChart.Data> getMergedTransactionList(LocalDate from, LocalDate to) {

		ArrayList<PieChart.Data> arrayList = new ArrayList<>();
		Map<String, Double> hashMap = new HashMap<>();

		ObservableList<Transaction> trans = getTransactionList(from, to);

		for (Transaction temp : trans)
			hashMap.merge(temp.getCategory().toString(), temp.getAmount(), Double::sum);

		for (Map.Entry<String, Double> entry : hashMap.entrySet())
			arrayList.add(new PieChart.Data(entry.getKey(), entry.getValue()));

		return FXCollections.observableArrayList(arrayList);
	}

	public ObservableList<Category> getCategoryList() {

		TypedQuery<Category> query = super.getEntityManager().createQuery("SELECT c FROM Category c",
				Category.class);

		return FXCollections.observableList(query.getResultList());
	}

	public XYChart.Series<String, Number> getMergedTransactionSeries(LocalDate from, LocalDate to) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		ArrayList<LineChart.Data<String, Number>> arrayList = new ArrayList<>();
		Map<String, Double> treeMap = new TreeMap<>();

		ObservableList<Transaction> trans = getTransactionList(from, to);

		for (Transaction temp : trans)
			treeMap.merge(temp.getDate().toString(), temp.getAmount(), Double::sum);

		for (Map.Entry<String, Double> entry : treeMap.entrySet())
			arrayList.add(new LineChart.Data<String, Number>(entry.getKey(), entry.getValue()));

		series.setName("Portfolio 3");
		series.getData().addAll(arrayList);

		return series;
	}
}
