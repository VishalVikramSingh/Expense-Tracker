package com.vvsTech.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //spring boot will automatically populate this
    private Integer id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, unique = true, nullable = false)
    private String email;

    @Column(length = 15, unique = true)
    private String contact;

    @CreationTimestamp //spring boot will automatically populate this
    private Date createdAt;

    @UpdateTimestamp //spring boot will automatically populate this
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TxnDetails> txnDetailsList;


}
