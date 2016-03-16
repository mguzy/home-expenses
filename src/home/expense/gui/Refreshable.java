package home.expense.gui;

import java.time.LocalDate;

public interface Refreshable {
	public void refresh(LocalDate from, LocalDate to);
}
