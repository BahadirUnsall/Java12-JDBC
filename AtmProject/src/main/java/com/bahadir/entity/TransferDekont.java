package com.bahadir.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class TransferDekont {
    private Date transactionDate;
    private double sendAmountTotal;
    private String senderName;
    private String receiverName;
}
