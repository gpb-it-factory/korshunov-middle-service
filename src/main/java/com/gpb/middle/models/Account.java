package com.gpb.middle.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column
    private Long userId;

    @NotBlank
    @Column
    private String accountId;

    @NotBlank
    @Column
    private String accountName;

    @NotNull
    @Column
    private BigDecimal amount;
}
