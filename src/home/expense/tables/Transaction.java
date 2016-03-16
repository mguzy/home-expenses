package home.expense.tables;

import javax.persistence.*;

@Entity
@Table
public class Transaction implements Injectable {

	@Id
	@GeneratedValue
	@Column
	private long id;

	@Column(columnDefinition = "DECIMAL(10,2) NOT NULL")
	private double amount;

	@Column(columnDefinition = "DATE NOT NULL")
	private java.sql.Date date;

	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@Column(columnDefinition = "VARCHAR(255)")
	private String info;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
