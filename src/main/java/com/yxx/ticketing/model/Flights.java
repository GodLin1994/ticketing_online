package com.yxx.ticketing.model;

import java.util.Date;

public class Flights {
    private Integer flightId;

    private String flightNum;

    private Date flightStartTime;

    private Date flightEndTime;

    private String flightState;

    private Double flightMileage;

    private String planeModel;

    private String airlineName;

    private String airlineShorthand;

    private String startCityName;

    private String startAirportName;

    private String endCityName;

    private String endAirportName;

    //各舱位剩余票数和价格
    private FareNum fareNum;

    public FareNum getFareNum() {
        return fareNum;
    }

    public void setFareNum(FareNum fareNum) {
        this.fareNum = fareNum;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum == null ? null : flightNum.trim();
    }

    public Date getFlightStartTime() {
        return flightStartTime;
    }

    public void setFlightStartTime(Date flightStartTime) {
        this.flightStartTime = flightStartTime;
    }

    public Date getFlightEndTime() {
        return flightEndTime;
    }

    public void setFlightEndTime(Date flightEndTime) {
        this.flightEndTime = flightEndTime;
    }

    public String getFlightState() {
        return flightState;
    }

    public void setFlightState(String flightState) {
        this.flightState = flightState == null ? null : flightState.trim();
    }

    public Double getFlightMileage() {
        return flightMileage;
    }

    public void setFlightMileage(Double flightMileage) {
        this.flightMileage = flightMileage;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel == null ? null : planeModel.trim();
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName == null ? null : airlineName.trim();
    }

    public String getAirlineShorthand() {
        return airlineShorthand;
    }

    public void setAirlineShorthand(String airlineShorthand) {
        this.airlineShorthand = airlineShorthand == null ? null : airlineShorthand.trim();
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName == null ? null : startCityName.trim();
    }

    public String getStartAirportName() {
        return startAirportName;
    }

    public void setStartAirportName(String startAirportName) {
        this.startAirportName = startAirportName == null ? null : startAirportName.trim();
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName == null ? null : endCityName.trim();
    }

    public String getEndAirportName() {
        return endAirportName;
    }

    public void setEndAirportName(String endAirportName) {
        this.endAirportName = endAirportName == null ? null : endAirportName.trim();
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flightId=" + flightId +
                ", flightNum='" + flightNum + '\'' +
                ", flightStartTime=" + flightStartTime +
                ", flightEndTime=" + flightEndTime +
                ", flightState='" + flightState + '\'' +
                ", flightMileage=" + flightMileage +
                ", planeModel='" + planeModel + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", airlineShorthand='" + airlineShorthand + '\'' +
                ", startCityName='" + startCityName + '\'' +
                ", startAirportName='" + startAirportName + '\'' +
                ", endCityName='" + endCityName + '\'' +
                ", endAirportName='" + endAirportName + '\'' +
                '}';
    }
}