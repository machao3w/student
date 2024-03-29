package com.machao.student.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="grade_mid")
@Data
public class GradeMid {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.number
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    @Id
    private String numberM;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.chines
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String chineseMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.math
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String mathMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.english
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String englishMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.physics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String physicsMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.chemistry
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String chemistryMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.geography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String geographyMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.politics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String politicsMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.history
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String historyMid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.biography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String biographyMid;

}