package com.yxx.ticketing.model;

import java.util.Date;

public class Order {
    private Integer id;

    private String num;

    private String state;

    private Date ctime;

    private String contact;

    private String cphone;

    private Double tprice;

    private Integer userId;

    private Oppo[] oppos;

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
        this.num = num == null ? null : num.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone == null ? null : cphone.trim();
    }

    public Double getTprice() {
        return tprice;
    }

    public void setTprice(Double tprice) {
        this.tprice = tprice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Oppo[] getOppos() {
        return oppos;
    }

    public void setOppos(Oppo[] oppos) {
        this.oppos = oppos;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", state='" + state + '\'' +
                ", ctime=" + ctime +
                ", contact='" + contact + '\'' +
                ", cphone='" + cphone + '\'' +
                ", tprice=" + tprice +
                ", userId=" + userId +
                '}';
    }
}