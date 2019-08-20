package com.machao.student.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="register_form")
public class EventEnter {

    private Integer formId;

    private String name;

    private Integer grade;

    private Integer classes;
}
