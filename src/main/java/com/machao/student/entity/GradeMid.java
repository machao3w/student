package com.machao.student.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="grade_mid")
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
    private String chines;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.math
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String math;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.english
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String english;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.physics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String physics;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.chemistry
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String chemistry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.geography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String geography;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.politics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String politics;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.history
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String history;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_mid.biography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    private String biography;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.number
     *
     * @return the value of grade_mid.number
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getNumberM() {
        return numberM;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.number
     *
     * @param numberM the value for grade_mid.number
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setNumberM(String numberM) {
        this.numberM = numberM == null ? null : numberM.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.chines
     *
     * @return the value of grade_mid.chines
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getChines() {
        return chines;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.chines
     *
     * @param chines the value for grade_mid.chines
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setChines(String chines) {
        this.chines = chines == null ? null : chines.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.math
     *
     * @return the value of grade_mid.math
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getMath() {
        return math;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.math
     *
     * @param math the value for grade_mid.math
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setMath(String math) {
        this.math = math == null ? null : math.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.english
     *
     * @return the value of grade_mid.english
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getEnglish() {
        return english;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.english
     *
     * @param english the value for grade_mid.english
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setEnglish(String english) {
        this.english = english == null ? null : english.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.physics
     *
     * @return the value of grade_mid.physics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getPhysics() {
        return physics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.physics
     *
     * @param physics the value for grade_mid.physics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setPhysics(String physics) {
        this.physics = physics == null ? null : physics.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.chemistry
     *
     * @return the value of grade_mid.chemistry
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getChemistry() {
        return chemistry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.chemistry
     *
     * @param chemistry the value for grade_mid.chemistry
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setChemistry(String chemistry) {
        this.chemistry = chemistry == null ? null : chemistry.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.geography
     *
     * @return the value of grade_mid.geography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getGeography() {
        return geography;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.geography
     *
     * @param geography the value for grade_mid.geography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setGeography(String geography) {
        this.geography = geography == null ? null : geography.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.politics
     *
     * @return the value of grade_mid.politics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getPolitics() {
        return politics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.politics
     *
     * @param politics the value for grade_mid.politics
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setPolitics(String politics) {
        this.politics = politics == null ? null : politics.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.history
     *
     * @return the value of grade_mid.history
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getHistory() {
        return history;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.history
     *
     * @param history the value for grade_mid.history
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setHistory(String history) {
        this.history = history == null ? null : history.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_mid.biography
     *
     * @return the value of grade_mid.biography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public String getBiography() {
        return biography;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_mid.biography
     *
     * @param biography the value for grade_mid.biography
     *
     * @mbggenerated Thu Aug 15 14:48:54 CST 2019
     */
    public void setBiography(String biography) {
        this.biography = biography == null ? null : biography.trim();
    }
}