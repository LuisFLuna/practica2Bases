package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue
    Long id;

    List<PlantReservation> reservations;
    PlantInventoryEntry plant;

    LocalDate issueDate;
    LocalDate paymentSchedule;

    @Column(precision=8, scale=2)
    BigDecimal total;

    @Enumerated(EnumType.STRING)
    POStatus status;

    @Embedded
    BusinessPeriod rentalPeriod;
}
