package com.bahadir.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private int id;
    private int userId;
    private double balance;
    private String accountNo;
    private Date transactionDate;
}
