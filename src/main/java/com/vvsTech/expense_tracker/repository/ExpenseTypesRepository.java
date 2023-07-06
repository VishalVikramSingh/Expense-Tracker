package com.vvsTech.expense_tracker.repository;

import com.vvsTech.expense_tracker.model.ExpenseTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTypesRepository extends JpaRepository<ExpenseTypes, Integer> {

    public ExpenseTypes findByExpenseType(String expenseType);

}
