package com.vvsTech.expense_tracker.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "txn_details")
public class TxnDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn  // to keep user in txn_details db
    private User user;

    @ManyToOne
    @JoinColumn
    private ExpenseTypes expenseTypes;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private LocalDate expenseDate;

    private String expenseNote;

    private Double expenditureAmount;


}
