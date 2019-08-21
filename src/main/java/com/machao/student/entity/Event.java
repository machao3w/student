package com.machao.student.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name="event")
public class Event {
    @Id
    private Integer id;

    private String eventName;

    private Integer limitQuantity;
}
