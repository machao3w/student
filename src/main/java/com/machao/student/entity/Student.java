package com.machao.student.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="student_info")
public class Student {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_info.number
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    @Id
    private String number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_info.name
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_info.sex
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_info.grade
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private Integer grade;

    private GradeFinal gradeFinal;

    private GradeMid gradeMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_info.class
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private Integer classes;

    private String nameEnglish;

}