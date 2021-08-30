package com.yxx.ticketing.model;

/**
 *
 *
 */
public class SaleOppo {

    private Integer fid;
    private Integer afid;
    private Integer oid;
    private Integer opid;
    private String opname;
    private String idCard;
    private Double price;
    private String num;
    private String lv;

    @Override
    public String toString() {
        return "SaleOppo{" +
                "fid=" + fid +
                ", afid=" + afid +
                ", oid=" + oid +
                ", opid=" + opid +
                ", opname='" + opname + '\'' +
                ", idCard='" + idCard + '\'' +
                ", price=" + price +
                ", num='" + num + '\'' +
                ", lv='" + lv + '\'' +
                '}';
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getAfid() {
        return afid;
    }

    public void setAfid(Integer afid) {
        this.afid = afid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public SaleOppo() {
    }

    public SaleOppo(Integer fid, Integer afid, Integer oid, Integer opid, String opname, String idCard, Double price, String num, String lv) {
        this.fid = fid;
        this.afid = afid;
        this.oid = oid;
        this.opid = opid;
        this.opname = opname;
        this.idCard = idCard;
        this.price = price;
        this.num = num;
        this.lv = lv;
    }
}
