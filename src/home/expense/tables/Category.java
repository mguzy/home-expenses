package home.expense.tables;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Category implements Injectable {
	
	@Id
	@Column(columnDefinition = "CHAR(20) NOT NULL")
	private String name;
	
	@OneToMany(mappedBy = "category", cascade=CascadeType.ALL)
	private List<Transaction> transactions;
	
	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	
}
