package com.yxx.ticketing.model;

/**
 *
 *
 * 各舱位剩余机票数量和机票价格
 */
public class FareNum {

    private Integer firstNum;
    private Integer firstPrice;
    private Integer businessNum;
    private Integer businessPrice;
    private Integer economyNum;
    private Integer economyPrice;

    public Integer getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(Integer firstNum) {
        this.firstNum = firstNum;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(Integer businessNum) {
        this.businessNum = businessNum;
    }

    public Integer getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Integer businessPrice) {
        this.businessPrice = businessPrice;
    }

    public Integer getEconomyNum() {
        return economyNum;
    }

    public void setEconomyNum(Integer economyNum) {
        this.economyNum = economyNum;
    }

    public Integer getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(Integer economyPrice) {
        this.economyPrice = economyPrice;
    }
}
