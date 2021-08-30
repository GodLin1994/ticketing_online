package com.yxx.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class FareExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FareExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
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

        public Criteria andOppoIdIsNull() {
            addCriterion("oppo_id is null");
            return (Criteria) this;
        }

        public Criteria andOppoIdIsNotNull() {
            addCriterion("oppo_id is not null");
            return (Criteria) this;
        }

        public Criteria andOppoIdEqualTo(Integer value) {
            addCriterion("oppo_id =", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdNotEqualTo(Integer value) {
            addCriterion("oppo_id <>", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdGreaterThan(Integer value) {
            addCriterion("oppo_id >", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("oppo_id >=", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdLessThan(Integer value) {
            addCriterion("oppo_id <", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdLessThanOrEqualTo(Integer value) {
            addCriterion("oppo_id <=", value, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdIn(List<Integer> values) {
            addCriterion("oppo_id in", values, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdNotIn(List<Integer> values) {
            addCriterion("oppo_id not in", values, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdBetween(Integer value1, Integer value2) {
            addCriterion("oppo_id between", value1, value2, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOppoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("oppo_id not between", value1, value2, "oppoId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andSeatIdIsNull() {
            addCriterion("seat_id is null");
            return (Criteria) this;
        }

        public Criteria andSeatIdIsNotNull() {
            addCriterion("seat_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeatIdEqualTo(Integer value) {
            addCriterion("seat_id =", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotEqualTo(Integer value) {
            addCriterion("seat_id <>", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdGreaterThan(Integer value) {
            addCriterion("seat_id >", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("seat_id >=", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdLessThan(Integer value) {
            addCriterion("seat_id <", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdLessThanOrEqualTo(Integer value) {
            addCriterion("seat_id <=", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdIn(List<Integer> values) {
            addCriterion("seat_id in", values, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotIn(List<Integer> values) {
            addCriterion("seat_id not in", values, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdBetween(Integer value1, Integer value2) {
            addCriterion("seat_id between", value1, value2, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("seat_id not between", value1, value2, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatNumIsNull() {
            addCriterion("seat_num is null");
            return (Criteria) this;
        }

        public Criteria andSeatNumIsNotNull() {
            addCriterion("seat_num is not null");
            return (Criteria) this;
        }

        public Criteria andSeatNumEqualTo(String value) {
            addCriterion("seat_num =", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumNotEqualTo(String value) {
            addCriterion("seat_num <>", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumGreaterThan(String value) {
            addCriterion("seat_num >", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumGreaterThanOrEqualTo(String value) {
            addCriterion("seat_num >=", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumLessThan(String value) {
            addCriterion("seat_num <", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumLessThanOrEqualTo(String value) {
            addCriterion("seat_num <=", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumLike(String value) {
            addCriterion("seat_num like", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumNotLike(String value) {
            addCriterion("seat_num not like", value, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumIn(List<String> values) {
            addCriterion("seat_num in", values, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumNotIn(List<String> values) {
            addCriterion("seat_num not in", values, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumBetween(String value1, String value2) {
            addCriterion("seat_num between", value1, value2, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatNumNotBetween(String value1, String value2) {
            addCriterion("seat_num not between", value1, value2, "seatNum");
            return (Criteria) this;
        }

        public Criteria andSeatLevelIsNull() {
            addCriterion("seat_level is null");
            return (Criteria) this;
        }

        public Criteria andSeatLevelIsNotNull() {
            addCriterion("seat_level is not null");
            return (Criteria) this;
        }

        public Criteria andSeatLevelEqualTo(String value) {
            addCriterion("seat_level =", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelNotEqualTo(String value) {
            addCriterion("seat_level <>", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelGreaterThan(String value) {
            addCriterion("seat_level >", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelGreaterThanOrEqualTo(String value) {
            addCriterion("seat_level >=", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelLessThan(String value) {
            addCriterion("seat_level <", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelLessThanOrEqualTo(String value) {
            addCriterion("seat_level <=", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelLike(String value) {
            addCriterion("seat_level like", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelNotLike(String value) {
            addCriterion("seat_level not like", value, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelIn(List<String> values) {
            addCriterion("seat_level in", values, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelNotIn(List<String> values) {
            addCriterion("seat_level not in", values, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelBetween(String value1, String value2) {
            addCriterion("seat_level between", value1, value2, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andSeatLevelNotBetween(String value1, String value2) {
            addCriterion("seat_level not between", value1, value2, "seatLevel");
            return (Criteria) this;
        }

        public Criteria andPlaneIdIsNull() {
            addCriterion("plane_id is null");
            return (Criteria) this;
        }

        public Criteria andPlaneIdIsNotNull() {
            addCriterion("plane_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlaneIdEqualTo(Integer value) {
            addCriterion("plane_id =", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdNotEqualTo(Integer value) {
            addCriterion("plane_id <>", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdGreaterThan(Integer value) {
            addCriterion("plane_id >", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plane_id >=", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdLessThan(Integer value) {
            addCriterion("plane_id <", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdLessThanOrEqualTo(Integer value) {
            addCriterion("plane_id <=", value, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdIn(List<Integer> values) {
            addCriterion("plane_id in", values, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdNotIn(List<Integer> values) {
            addCriterion("plane_id not in", values, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdBetween(Integer value1, Integer value2) {
            addCriterion("plane_id between", value1, value2, "planeId");
            return (Criteria) this;
        }

        public Criteria andPlaneIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plane_id not between", value1, value2, "planeId");
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