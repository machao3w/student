package com.machao.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="register_form")
@AllArgsConstructor
@NoArgsConstructor
public class EventEnter {

    @Id
    private Integer formId;

    private String number;

    private String name;

    private Integer eventId;
}
