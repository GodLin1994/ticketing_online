package com.yxx.ticketing.model;

import java.util.Date;

/**
 *
 *
 */
public class Flight {
    private Integer id;
    private String num;
    private String state;
    private Date sTime;
    private Date eTime;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", state='" + state + '\'' +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Flight(Integer id, String num, String state, Date sTime, Date eTime) {
        this.id = id;
        this.num = num;
        this.state = state;
        this.sTime = sTime;
        this.eTime = eTime;
    }

    public Flight() {
    }
}
