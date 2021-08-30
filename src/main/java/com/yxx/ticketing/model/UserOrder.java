package com.yxx.ticketing.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 */
public class UserOrder {
    private Integer id;
    private String num;//订单编号
    private String state;//状态
    private Date cTime;//创建实际
    private String contact;//联系人
    private String cPhone;//手机号
    private String tPrice;//价格
    private String userId;//用户id
    private String cStart;//搜索时间的开始
    private String cEnd;//搜索时间的结束
    private Set<SaleFlight> saleFlights = new HashSet<>();//航班集合

    public UserOrder() {
    }

    public UserOrder(Integer id, String num, String state, Date cTime, String contact, String cPhone, String tPrice, String userId, String cStart, String cEnd, Set<SaleFlight> saleFlights) {
        this.id = id;
        this.num = num;
        this.state = state;
        this.cTime = cTime;
        this.contact = contact;
        this.cPhone = cPhone;
        this.tPrice = tPrice;
        this.userId = userId;
        this.cStart = cStart;
        this.cEnd = cEnd;
        this.saleFlights = saleFlights;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", state='" + state + '\'' +
                ", cTime=" + cTime +
                ", contact='" + contact + '\'' +
                ", cPhone='" + cPhone + '\'' +
                ", tPrice='" + tPrice + '\'' +
                ", userId='" + userId + '\'' +
                ", cStart='" + cStart + '\'' +
                ", cEnd='" + cEnd + '\'' +
                ", saleFlights=" + saleFlights +
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

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getcStart() {
        return cStart;
    }

    public void setcStart(String cStart) {
        this.cStart = cStart;
    }

    public String getcEnd() {
        return cEnd;
    }

    public void setcEnd(String cEnd) {
        this.cEnd = cEnd;
    }

    public Set<SaleFlight> getSaleFlights() {
        return saleFlights;
    }

    public void setSaleFlights(Set<SaleFlight> saleFlights) {
        this.saleFlights = saleFlights;
    }
}
