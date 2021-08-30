package com.yxx.ticketing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlightsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFlightIdIsNull() {
            addCriterion("flight_id is null");
            return (Criteria) this;
        }

        public Criteria andFlightIdIsNotNull() {
            addCriterion("flight_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlightIdEqualTo(Integer value) {
            addCriterion("flight_id =", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdNotEqualTo(Integer value) {
            addCriterion("flight_id <>", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdGreaterThan(Integer value) {
            addCriterion("flight_id >", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flight_id >=", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdLessThan(Integer value) {
            addCriterion("flight_id <", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdLessThanOrEqualTo(Integer value) {
            addCriterion("flight_id <=", value, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdIn(List<Integer> values) {
            addCriterion("flight_id in", values, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdNotIn(List<Integer> values) {
            addCriterion("flight_id not in", values, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdBetween(Integer value1, Integer value2) {
            addCriterion("flight_id between", value1, value2, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flight_id not between", value1, value2, "flightId");
            return (Criteria) this;
        }

        public Criteria andFlightNumIsNull() {
            addCriterion("flight_num is null");
            return (Criteria) this;
        }

        public Criteria andFlightNumIsNotNull() {
            addCriterion("flight_num is not null");
            return (Criteria) this;
        }

        public Criteria andFlightNumEqualTo(String value) {
            addCriterion("flight_num =", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumNotEqualTo(String value) {
            addCriterion("flight_num <>", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumGreaterThan(String value) {
            addCriterion("flight_num >", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumGreaterThanOrEqualTo(String value) {
            addCriterion("flight_num >=", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumLessThan(String value) {
            addCriterion("flight_num <", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumLessThanOrEqualTo(String value) {
            addCriterion("flight_num <=", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumLike(String value) {
            addCriterion("flight_num like", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumNotLike(String value) {
            addCriterion("flight_num not like", value, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumIn(List<String> values) {
            addCriterion("flight_num in", values, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumNotIn(List<String> values) {
            addCriterion("flight_num not in", values, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumBetween(String value1, String value2) {
            addCriterion("flight_num between", value1, value2, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightNumNotBetween(String value1, String value2) {
            addCriterion("flight_num not between", value1, value2, "flightNum");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeIsNull() {
            addCriterion("flight_start_time is null");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeIsNotNull() {
            addCriterion("flight_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeEqualTo(Date value) {
            addCriterion("flight_start_time =", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeNotEqualTo(Date value) {
            addCriterion("flight_start_time <>", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeGreaterThan(Date value) {
            addCriterion("flight_start_time >", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("flight_start_time >=", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeLessThan(Date value) {
            addCriterion("flight_start_time <", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("flight_start_time <=", value, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeIn(List<Date> values) {
            addCriterion("flight_start_time in", values, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeNotIn(List<Date> values) {
            addCriterion("flight_start_time not in", values, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeBetween(Date value1, Date value2) {
            addCriterion("flight_start_time >",value1,"flightStartTime");
            addCriterion("flight_start_time <",value2,"flightStartTime");
            //addCriterion("flight_start_time between", value1, value2, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("flight_start_time not between", value1, value2, "flightStartTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeIsNull() {
            addCriterion("flight_end_time is null");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeIsNotNull() {
            addCriterion("flight_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeEqualTo(Date value) {
            addCriterion("flight_end_time =", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeNotEqualTo(Date value) {
            addCriterion("flight_end_time <>", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeGreaterThan(Date value) {
            addCriterion("flight_end_time >", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("flight_end_time >=", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeLessThan(Date value) {
            addCriterion("flight_end_time <", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("flight_end_time <=", value, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeIn(List<Date> values) {
            addCriterion("flight_end_time in", values, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeNotIn(List<Date> values) {
            addCriterion("flight_end_time not in", values, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeBetween(Date value1, Date value2) {
            addCriterion("flight_end_time between", value1, value2, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("flight_end_time not between", value1, value2, "flightEndTime");
            return (Criteria) this;
        }

        public Criteria andFlightStateIsNull() {
            addCriterion("flight_state is null");
            return (Criteria) this;
        }

        public Criteria andFlightStateIsNotNull() {
            addCriterion("flight_state is not null");
            return (Criteria) this;
        }

        public Criteria andFlightStateEqualTo(String value) {
            addCriterion("flight_state =", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateNotEqualTo(String value) {
            addCriterion("flight_state <>", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateGreaterThan(String value) {
            addCriterion("flight_state >", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateGreaterThanOrEqualTo(String value) {
            addCriterion("flight_state >=", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateLessThan(String value) {
            addCriterion("flight_state <", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateLessThanOrEqualTo(String value) {
            addCriterion("flight_state <=", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateLike(String value) {
            addCriterion("flight_state like", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateNotLike(String value) {
            addCriterion("flight_state not like", value, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateIn(List<String> values) {
            addCriterion("flight_state in", values, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateNotIn(List<String> values) {
            addCriterion("flight_state not in", values, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateBetween(String value1, String value2) {
            addCriterion("flight_state between", value1, value2, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightStateNotBetween(String value1, String value2) {
            addCriterion("flight_state not between", value1, value2, "flightState");
            return (Criteria) this;
        }

        public Criteria andFlightMileageIsNull() {
            addCriterion("flight_mileage is null");
            return (Criteria) this;
        }

        public Criteria andFlightMileageIsNotNull() {
            addCriterion("flight_mileage is not null");
            return (Criteria) this;
        }

        public Criteria andFlightMileageEqualTo(Double value) {
            addCriterion("flight_mileage =", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageNotEqualTo(Double value) {
            addCriterion("flight_mileage <>", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageGreaterThan(Double value) {
            addCriterion("flight_mileage >", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageGreaterThanOrEqualTo(Double value) {
            addCriterion("flight_mileage >=", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageLessThan(Double value) {
            addCriterion("flight_mileage <", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageLessThanOrEqualTo(Double value) {
            addCriterion("flight_mileage <=", value, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageIn(List<Double> values) {
            addCriterion("flight_mileage in", values, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageNotIn(List<Double> values) {
            addCriterion("flight_mileage not in", values, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageBetween(Double value1, Double value2) {
            addCriterion("flight_mileage between", value1, value2, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andFlightMileageNotBetween(Double value1, Double value2) {
            addCriterion("flight_mileage not between", value1, value2, "flightMileage");
            return (Criteria) this;
        }

        public Criteria andPlaneModelIsNull() {
            addCriterion("plane_model is null");
            return (Criteria) this;
        }

        public Criteria andPlaneModelIsNotNull() {
            addCriterion("plane_model is not null");
            return (Criteria) this;
        }

        public Criteria andPlaneModelEqualTo(String value) {
            addCriterion("plane_model =", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelNotEqualTo(String value) {
            addCriterion("plane_model <>", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelGreaterThan(String value) {
            addCriterion("plane_model >", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelGreaterThanOrEqualTo(String value) {
            addCriterion("plane_model >=", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelLessThan(String value) {
            addCriterion("plane_model <", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelLessThanOrEqualTo(String value) {
            addCriterion("plane_model <=", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelLike(String value) {
            addCriterion("plane_model like", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelNotLike(String value) {
            addCriterion("plane_model not like", value, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelIn(List<String> values) {
            addCriterion("plane_model in", values, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelNotIn(List<String> values) {
            addCriterion("plane_model not in", values, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelBetween(String value1, String value2) {
            addCriterion("plane_model between", value1, value2, "planeModel");
            return (Criteria) this;
        }

        public Criteria andPlaneModelNotBetween(String value1, String value2) {
            addCriterion("plane_model not between", value1, value2, "planeModel");
            return (Criteria) this;
        }

        public Criteria andAirlineNameIsNull() {
            addCriterion("airline_name is null");
            return (Criteria) this;
        }

        public Criteria andAirlineNameIsNotNull() {
            addCriterion("airline_name is not null");
            return (Criteria) this;
        }

        public Criteria andAirlineNameEqualTo(String value) {
            addCriterion("airline_name =", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameNotEqualTo(String value) {
            addCriterion("airline_name <>", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameGreaterThan(String value) {
            addCriterion("airline_name >", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameGreaterThanOrEqualTo(String value) {
            addCriterion("airline_name >=", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameLessThan(String value) {
            addCriterion("airline_name <", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameLessThanOrEqualTo(String value) {
            addCriterion("airline_name <=", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameLike(String value) {
            addCriterion("airline_name like", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameNotLike(String value) {
            addCriterion("airline_name not like", value, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameIn(List<String> values) {
            addCriterion("airline_name in", values, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameNotIn(List<String> values) {
            addCriterion("airline_name not in", values, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameBetween(String value1, String value2) {
            addCriterion("airline_name between", value1, value2, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineNameNotBetween(String value1, String value2) {
            addCriterion("airline_name not between", value1, value2, "airlineName");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandIsNull() {
            addCriterion("airline_shorthand is null");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandIsNotNull() {
            addCriterion("airline_shorthand is not null");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandEqualTo(String value) {
            addCriterion("airline_shorthand =", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandNotEqualTo(String value) {
            addCriterion("airline_shorthand <>", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandGreaterThan(String value) {
            addCriterion("airline_shorthand >", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandGreaterThanOrEqualTo(String value) {
            addCriterion("airline_shorthand >=", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandLessThan(String value) {
            addCriterion("airline_shorthand <", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandLessThanOrEqualTo(String value) {
            addCriterion("airline_shorthand <=", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandLike(String value) {
            addCriterion("airline_shorthand like", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandNotLike(String value) {
            addCriterion("airline_shorthand not like", value, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandIn(List<String> values) {
            addCriterion("airline_shorthand in", values, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandNotIn(List<String> values) {
            addCriterion("airline_shorthand not in", values, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandBetween(String value1, String value2) {
            addCriterion("airline_shorthand between", value1, value2, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andAirlineShorthandNotBetween(String value1, String value2) {
            addCriterion("airline_shorthand not between", value1, value2, "airlineShorthand");
            return (Criteria) this;
        }

        public Criteria andStartCityNameIsNull() {
            addCriterion("start_city_name is null");
            return (Criteria) this;
        }

        public Criteria andStartCityNameIsNotNull() {
            addCriterion("start_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andStartCityNameEqualTo(String value) {
            addCriterion("start_city_name =", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameNotEqualTo(String value) {
            addCriterion("start_city_name <>", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameGreaterThan(String value) {
            addCriterion("start_city_name >", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_city_name >=", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameLessThan(String value) {
            addCriterion("start_city_name <", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameLessThanOrEqualTo(String value) {
            addCriterion("start_city_name <=", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameLike(String value) {
            addCriterion("start_city_name like", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameNotLike(String value) {
            addCriterion("start_city_name not like", value, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameIn(List<String> values) {
            addCriterion("start_city_name in", values, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameNotIn(List<String> values) {
            addCriterion("start_city_name not in", values, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameBetween(String value1, String value2) {
            addCriterion("start_city_name between", value1, value2, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartCityNameNotBetween(String value1, String value2) {
            addCriterion("start_city_name not between", value1, value2, "startCityName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameIsNull() {
            addCriterion("start_airport_name is null");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameIsNotNull() {
            addCriterion("start_airport_name is not null");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameEqualTo(String value) {
            addCriterion("start_airport_name =", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameNotEqualTo(String value) {
            addCriterion("start_airport_name <>", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameGreaterThan(String value) {
            addCriterion("start_airport_name >", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_airport_name >=", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameLessThan(String value) {
            addCriterion("start_airport_name <", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameLessThanOrEqualTo(String value) {
            addCriterion("start_airport_name <=", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameLike(String value) {
            addCriterion("start_airport_name like", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameNotLike(String value) {
            addCriterion("start_airport_name not like", value, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameIn(List<String> values) {
            addCriterion("start_airport_name in", values, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameNotIn(List<String> values) {
            addCriterion("start_airport_name not in", values, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameBetween(String value1, String value2) {
            addCriterion("start_airport_name between", value1, value2, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andStartAirportNameNotBetween(String value1, String value2) {
            addCriterion("start_airport_name not between", value1, value2, "startAirportName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameIsNull() {
            addCriterion("end_city_name is null");
            return (Criteria) this;
        }

        public Criteria andEndCityNameIsNotNull() {
            addCriterion("end_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndCityNameEqualTo(String value) {
            addCriterion("end_city_name =", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameNotEqualTo(String value) {
            addCriterion("end_city_name <>", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameGreaterThan(String value) {
            addCriterion("end_city_name >", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_city_name >=", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameLessThan(String value) {
            addCriterion("end_city_name <", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameLessThanOrEqualTo(String value) {
            addCriterion("end_city_name <=", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameLike(String value) {
            addCriterion("end_city_name like", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameNotLike(String value) {
            addCriterion("end_city_name not like", value, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameIn(List<String> values) {
            addCriterion("end_city_name in", values, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameNotIn(List<String> values) {
            addCriterion("end_city_name not in", values, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameBetween(String value1, String value2) {
            addCriterion("end_city_name between", value1, value2, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndCityNameNotBetween(String value1, String value2) {
            addCriterion("end_city_name not between", value1, value2, "endCityName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameIsNull() {
            addCriterion("end_airport_name is null");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameIsNotNull() {
            addCriterion("end_airport_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameEqualTo(String value) {
            addCriterion("end_airport_name =", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameNotEqualTo(String value) {
            addCriterion("end_airport_name <>", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameGreaterThan(String value) {
            addCriterion("end_airport_name >", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_airport_name >=", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameLessThan(String value) {
            addCriterion("end_airport_name <", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameLessThanOrEqualTo(String value) {
            addCriterion("end_airport_name <=", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameLike(String value) {
            addCriterion("end_airport_name like", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameNotLike(String value) {
            addCriterion("end_airport_name not like", value, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameIn(List<String> values) {
            addCriterion("end_airport_name in", values, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameNotIn(List<String> values) {
            addCriterion("end_airport_name not in", values, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameBetween(String value1, String value2) {
            addCriterion("end_airport_name between", value1, value2, "endAirportName");
            return (Criteria) this;
        }

        public Criteria andEndAirportNameNotBetween(String value1, String value2) {
            addCriterion("end_airport_name not between", value1, value2, "endAirportName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}