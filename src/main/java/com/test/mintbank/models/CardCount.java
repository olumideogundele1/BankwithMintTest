package com.test.mintbank.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cardcounts")
public class CardCount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bin;
    private int count = 0;
}
