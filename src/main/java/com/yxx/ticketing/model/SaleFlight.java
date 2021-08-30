package com.yxx.ticketing.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 */
public class SaleFlight {
    private Integer oid;

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

    private Set<SaleOppo> saleOppos = new HashSet<>();

    @Override
    public String toString() {
        return "SaleFlight{" +
                "oid=" + oid +
                ", flightId=" + flightId +
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
                ", saleOppos=" + saleOppos +
                '}';
    }

    public SaleFlight(Integer oid, Integer flightId, String flightNum, Date flightStartTime, Date flightEndTime, String flightState, Double flightMileage, String planeModel, String airlineName, String airlineShorthand, String startCityName, String startAirportName, String endCityName, String endAirportName, Set<SaleOppo> saleOppos) {
        this.oid = oid;
        this.flightId = flightId;
        this.flightNum = flightNum;
        this.flightStartTime = flightStartTime;
        this.flightEndTime = flightEndTime;
        this.flightState = flightState;
        this.flightMileage = flightMileage;
        this.planeModel = planeModel;
        this.airlineName = airlineName;
        this.airlineShorthand = airlineShorthand;
        this.startCityName = startCityName;
        this.startAirportName = startAirportName;
        this.endCityName = endCityName;
        this.endAirportName = endAirportName;
        this.saleOppos = saleOppos;
    }

    public SaleFlight() {
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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
        this.flightNum = flightNum;
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
        this.flightState = flightState;
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
        this.planeModel = planeModel;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineShorthand() {
        return airlineShorthand;
    }

    public void setAirlineShorthand(String airlineShorthand) {
        this.airlineShorthand = airlineShorthand;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getStartAirportName() {
        return startAirportName;
    }

    public void setStartAirportName(String startAirportName) {
        this.startAirportName = startAirportName;
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName;
    }

    public String getEndAirportName() {
        return endAirportName;
    }

    public void setEndAirportName(String endAirportName) {
        this.endAirportName = endAirportName;
    }

    public Set<SaleOppo> getSaleOppos() {
        return saleOppos;
    }

    public void setSaleOppos(Set<SaleOppo> saleOppos) {
        this.saleOppos = saleOppos;
    }
}
