package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    private String amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id",referencedColumnName = "id")
    private List<Transaction> transaction;

}
