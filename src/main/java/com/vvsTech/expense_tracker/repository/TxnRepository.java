package com.vvsTech.expense_tracker.repository;

import com.vvsTech.expense_tracker.model.TxnDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TxnRepository extends JpaRepository<TxnDetails, Integer> {

    public List<TxnDetails> findByExpenseTypes_expenseType(String expenseType); // check


}
