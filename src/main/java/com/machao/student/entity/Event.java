package com.machao.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name="event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private Integer id;

    private String eventName;

    private Integer limitQuantity;
}
