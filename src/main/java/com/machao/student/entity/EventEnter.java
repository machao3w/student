package com.machao.student.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="register_form")
public class EventEnter {

    @Id
    private Integer formId;

    private String number;

    private String name;

    private Integer eventId;
}
