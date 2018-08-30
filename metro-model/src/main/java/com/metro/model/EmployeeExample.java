package com.metro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserCardIsNull() {
            addCriterion("user_card is null");
            return (Criteria) this;
        }

        public Criteria andUserCardIsNotNull() {
            addCriterion("user_card is not null");
            return (Criteria) this;
        }

        public Criteria andUserCardEqualTo(String value) {
            addCriterion("user_card =", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardNotEqualTo(String value) {
            addCriterion("user_card <>", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardGreaterThan(String value) {
            addCriterion("user_card >", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardGreaterThanOrEqualTo(String value) {
            addCriterion("user_card >=", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardLessThan(String value) {
            addCriterion("user_card <", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardLessThanOrEqualTo(String value) {
            addCriterion("user_card <=", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardLike(String value) {
            addCriterion("user_card like", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardNotLike(String value) {
            addCriterion("user_card not like", value, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardIn(List<String> values) {
            addCriterion("user_card in", values, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardNotIn(List<String> values) {
            addCriterion("user_card not in", values, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardBetween(String value1, String value2) {
            addCriterion("user_card between", value1, value2, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserCardNotBetween(String value1, String value2) {
            addCriterion("user_card not between", value1, value2, "userCard");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNull() {
            addCriterion("user_number is null");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNotNull() {
            addCriterion("user_number is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumberEqualTo(String value) {
            addCriterion("user_number =", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotEqualTo(String value) {
            addCriterion("user_number <>", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThan(String value) {
            addCriterion("user_number >", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThanOrEqualTo(String value) {
            addCriterion("user_number >=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThan(String value) {
            addCriterion("user_number <", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThanOrEqualTo(String value) {
            addCriterion("user_number <=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLike(String value) {
            addCriterion("user_number like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotLike(String value) {
            addCriterion("user_number not like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberIn(List<String> values) {
            addCriterion("user_number in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotIn(List<String> values) {
            addCriterion("user_number not in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberBetween(String value1, String value2) {
            addCriterion("user_number between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotBetween(String value1, String value2) {
            addCriterion("user_number not between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNull() {
            addCriterion("operater is null");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNotNull() {
            addCriterion("operater is not null");
            return (Criteria) this;
        }

        public Criteria andOperaterEqualTo(String value) {
            addCriterion("operater =", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotEqualTo(String value) {
            addCriterion("operater <>", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThan(String value) {
            addCriterion("operater >", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThanOrEqualTo(String value) {
            addCriterion("operater >=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThan(String value) {
            addCriterion("operater <", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThanOrEqualTo(String value) {
            addCriterion("operater <=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLike(String value) {
            addCriterion("operater like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotLike(String value) {
            addCriterion("operater not like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterIn(List<String> values) {
            addCriterion("operater in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotIn(List<String> values) {
            addCriterion("operater not in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterBetween(String value1, String value2) {
            addCriterion("operater between", value1, value2, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotBetween(String value1, String value2) {
            addCriterion("operater not between", value1, value2, "operater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1IsNull() {
            addCriterion("verb_filed1 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1IsNotNull() {
            addCriterion("verb_filed1 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1EqualTo(String value) {
            addCriterion("verb_filed1 =", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1NotEqualTo(String value) {
            addCriterion("verb_filed1 <>", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1GreaterThan(String value) {
            addCriterion("verb_filed1 >", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed1 >=", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1LessThan(String value) {
            addCriterion("verb_filed1 <", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1LessThanOrEqualTo(String value) {
            addCriterion("verb_filed1 <=", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1Like(String value) {
            addCriterion("verb_filed1 like", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1NotLike(String value) {
            addCriterion("verb_filed1 not like", value, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1In(List<String> values) {
            addCriterion("verb_filed1 in", values, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1NotIn(List<String> values) {
            addCriterion("verb_filed1 not in", values, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1Between(String value1, String value2) {
            addCriterion("verb_filed1 between", value1, value2, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled1NotBetween(String value1, String value2) {
            addCriterion("verb_filed1 not between", value1, value2, "verbFiled1");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2IsNull() {
            addCriterion("verb_filed2 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2IsNotNull() {
            addCriterion("verb_filed2 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2EqualTo(String value) {
            addCriterion("verb_filed2 =", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2NotEqualTo(String value) {
            addCriterion("verb_filed2 <>", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2GreaterThan(String value) {
            addCriterion("verb_filed2 >", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed2 >=", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2LessThan(String value) {
            addCriterion("verb_filed2 <", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2LessThanOrEqualTo(String value) {
            addCriterion("verb_filed2 <=", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2Like(String value) {
            addCriterion("verb_filed2 like", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2NotLike(String value) {
            addCriterion("verb_filed2 not like", value, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2In(List<String> values) {
            addCriterion("verb_filed2 in", values, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2NotIn(List<String> values) {
            addCriterion("verb_filed2 not in", values, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2Between(String value1, String value2) {
            addCriterion("verb_filed2 between", value1, value2, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled2NotBetween(String value1, String value2) {
            addCriterion("verb_filed2 not between", value1, value2, "verbFiled2");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3IsNull() {
            addCriterion("verb_filed3 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3IsNotNull() {
            addCriterion("verb_filed3 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3EqualTo(String value) {
            addCriterion("verb_filed3 =", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3NotEqualTo(String value) {
            addCriterion("verb_filed3 <>", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3GreaterThan(String value) {
            addCriterion("verb_filed3 >", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed3 >=", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3LessThan(String value) {
            addCriterion("verb_filed3 <", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3LessThanOrEqualTo(String value) {
            addCriterion("verb_filed3 <=", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3Like(String value) {
            addCriterion("verb_filed3 like", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3NotLike(String value) {
            addCriterion("verb_filed3 not like", value, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3In(List<String> values) {
            addCriterion("verb_filed3 in", values, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3NotIn(List<String> values) {
            addCriterion("verb_filed3 not in", values, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3Between(String value1, String value2) {
            addCriterion("verb_filed3 between", value1, value2, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled3NotBetween(String value1, String value2) {
            addCriterion("verb_filed3 not between", value1, value2, "verbFiled3");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4IsNull() {
            addCriterion("verb_filed4 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4IsNotNull() {
            addCriterion("verb_filed4 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4EqualTo(String value) {
            addCriterion("verb_filed4 =", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4NotEqualTo(String value) {
            addCriterion("verb_filed4 <>", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4GreaterThan(String value) {
            addCriterion("verb_filed4 >", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed4 >=", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4LessThan(String value) {
            addCriterion("verb_filed4 <", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4LessThanOrEqualTo(String value) {
            addCriterion("verb_filed4 <=", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4Like(String value) {
            addCriterion("verb_filed4 like", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4NotLike(String value) {
            addCriterion("verb_filed4 not like", value, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4In(List<String> values) {
            addCriterion("verb_filed4 in", values, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4NotIn(List<String> values) {
            addCriterion("verb_filed4 not in", values, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4Between(String value1, String value2) {
            addCriterion("verb_filed4 between", value1, value2, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled4NotBetween(String value1, String value2) {
            addCriterion("verb_filed4 not between", value1, value2, "verbFiled4");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5IsNull() {
            addCriterion("verb_filed5 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5IsNotNull() {
            addCriterion("verb_filed5 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5EqualTo(String value) {
            addCriterion("verb_filed5 =", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5NotEqualTo(String value) {
            addCriterion("verb_filed5 <>", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5GreaterThan(String value) {
            addCriterion("verb_filed5 >", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed5 >=", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5LessThan(String value) {
            addCriterion("verb_filed5 <", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5LessThanOrEqualTo(String value) {
            addCriterion("verb_filed5 <=", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5Like(String value) {
            addCriterion("verb_filed5 like", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5NotLike(String value) {
            addCriterion("verb_filed5 not like", value, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5In(List<String> values) {
            addCriterion("verb_filed5 in", values, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5NotIn(List<String> values) {
            addCriterion("verb_filed5 not in", values, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5Between(String value1, String value2) {
            addCriterion("verb_filed5 between", value1, value2, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled5NotBetween(String value1, String value2) {
            addCriterion("verb_filed5 not between", value1, value2, "verbFiled5");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6IsNull() {
            addCriterion("verb_filed6 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6IsNotNull() {
            addCriterion("verb_filed6 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6EqualTo(String value) {
            addCriterion("verb_filed6 =", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6NotEqualTo(String value) {
            addCriterion("verb_filed6 <>", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6GreaterThan(String value) {
            addCriterion("verb_filed6 >", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed6 >=", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6LessThan(String value) {
            addCriterion("verb_filed6 <", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6LessThanOrEqualTo(String value) {
            addCriterion("verb_filed6 <=", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6Like(String value) {
            addCriterion("verb_filed6 like", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6NotLike(String value) {
            addCriterion("verb_filed6 not like", value, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6In(List<String> values) {
            addCriterion("verb_filed6 in", values, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6NotIn(List<String> values) {
            addCriterion("verb_filed6 not in", values, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6Between(String value1, String value2) {
            addCriterion("verb_filed6 between", value1, value2, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled6NotBetween(String value1, String value2) {
            addCriterion("verb_filed6 not between", value1, value2, "verbFiled6");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7IsNull() {
            addCriterion("verb_filed7 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7IsNotNull() {
            addCriterion("verb_filed7 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7EqualTo(String value) {
            addCriterion("verb_filed7 =", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7NotEqualTo(String value) {
            addCriterion("verb_filed7 <>", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7GreaterThan(String value) {
            addCriterion("verb_filed7 >", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed7 >=", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7LessThan(String value) {
            addCriterion("verb_filed7 <", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7LessThanOrEqualTo(String value) {
            addCriterion("verb_filed7 <=", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7Like(String value) {
            addCriterion("verb_filed7 like", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7NotLike(String value) {
            addCriterion("verb_filed7 not like", value, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7In(List<String> values) {
            addCriterion("verb_filed7 in", values, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7NotIn(List<String> values) {
            addCriterion("verb_filed7 not in", values, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7Between(String value1, String value2) {
            addCriterion("verb_filed7 between", value1, value2, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled7NotBetween(String value1, String value2) {
            addCriterion("verb_filed7 not between", value1, value2, "verbFiled7");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8IsNull() {
            addCriterion("verb_filed8 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8IsNotNull() {
            addCriterion("verb_filed8 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8EqualTo(String value) {
            addCriterion("verb_filed8 =", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8NotEqualTo(String value) {
            addCriterion("verb_filed8 <>", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8GreaterThan(String value) {
            addCriterion("verb_filed8 >", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed8 >=", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8LessThan(String value) {
            addCriterion("verb_filed8 <", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8LessThanOrEqualTo(String value) {
            addCriterion("verb_filed8 <=", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8Like(String value) {
            addCriterion("verb_filed8 like", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8NotLike(String value) {
            addCriterion("verb_filed8 not like", value, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8In(List<String> values) {
            addCriterion("verb_filed8 in", values, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8NotIn(List<String> values) {
            addCriterion("verb_filed8 not in", values, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8Between(String value1, String value2) {
            addCriterion("verb_filed8 between", value1, value2, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled8NotBetween(String value1, String value2) {
            addCriterion("verb_filed8 not between", value1, value2, "verbFiled8");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9IsNull() {
            addCriterion("verb_filed9 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9IsNotNull() {
            addCriterion("verb_filed9 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9EqualTo(String value) {
            addCriterion("verb_filed9 =", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9NotEqualTo(String value) {
            addCriterion("verb_filed9 <>", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9GreaterThan(String value) {
            addCriterion("verb_filed9 >", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed9 >=", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9LessThan(String value) {
            addCriterion("verb_filed9 <", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9LessThanOrEqualTo(String value) {
            addCriterion("verb_filed9 <=", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9Like(String value) {
            addCriterion("verb_filed9 like", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9NotLike(String value) {
            addCriterion("verb_filed9 not like", value, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9In(List<String> values) {
            addCriterion("verb_filed9 in", values, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9NotIn(List<String> values) {
            addCriterion("verb_filed9 not in", values, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9Between(String value1, String value2) {
            addCriterion("verb_filed9 between", value1, value2, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled9NotBetween(String value1, String value2) {
            addCriterion("verb_filed9 not between", value1, value2, "verbFiled9");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10IsNull() {
            addCriterion("verb_filed10 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10IsNotNull() {
            addCriterion("verb_filed10 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10EqualTo(String value) {
            addCriterion("verb_filed10 =", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10NotEqualTo(String value) {
            addCriterion("verb_filed10 <>", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10GreaterThan(String value) {
            addCriterion("verb_filed10 >", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed10 >=", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10LessThan(String value) {
            addCriterion("verb_filed10 <", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10LessThanOrEqualTo(String value) {
            addCriterion("verb_filed10 <=", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10Like(String value) {
            addCriterion("verb_filed10 like", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10NotLike(String value) {
            addCriterion("verb_filed10 not like", value, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10In(List<String> values) {
            addCriterion("verb_filed10 in", values, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10NotIn(List<String> values) {
            addCriterion("verb_filed10 not in", values, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10Between(String value1, String value2) {
            addCriterion("verb_filed10 between", value1, value2, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled10NotBetween(String value1, String value2) {
            addCriterion("verb_filed10 not between", value1, value2, "verbFiled10");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11IsNull() {
            addCriterion("verb_filed11 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11IsNotNull() {
            addCriterion("verb_filed11 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11EqualTo(String value) {
            addCriterion("verb_filed11 =", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11NotEqualTo(String value) {
            addCriterion("verb_filed11 <>", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11GreaterThan(String value) {
            addCriterion("verb_filed11 >", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed11 >=", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11LessThan(String value) {
            addCriterion("verb_filed11 <", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11LessThanOrEqualTo(String value) {
            addCriterion("verb_filed11 <=", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11Like(String value) {
            addCriterion("verb_filed11 like", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11NotLike(String value) {
            addCriterion("verb_filed11 not like", value, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11In(List<String> values) {
            addCriterion("verb_filed11 in", values, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11NotIn(List<String> values) {
            addCriterion("verb_filed11 not in", values, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11Between(String value1, String value2) {
            addCriterion("verb_filed11 between", value1, value2, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled11NotBetween(String value1, String value2) {
            addCriterion("verb_filed11 not between", value1, value2, "verbFiled11");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12IsNull() {
            addCriterion("verb_filed12 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12IsNotNull() {
            addCriterion("verb_filed12 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12EqualTo(String value) {
            addCriterion("verb_filed12 =", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12NotEqualTo(String value) {
            addCriterion("verb_filed12 <>", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12GreaterThan(String value) {
            addCriterion("verb_filed12 >", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed12 >=", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12LessThan(String value) {
            addCriterion("verb_filed12 <", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12LessThanOrEqualTo(String value) {
            addCriterion("verb_filed12 <=", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12Like(String value) {
            addCriterion("verb_filed12 like", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12NotLike(String value) {
            addCriterion("verb_filed12 not like", value, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12In(List<String> values) {
            addCriterion("verb_filed12 in", values, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12NotIn(List<String> values) {
            addCriterion("verb_filed12 not in", values, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12Between(String value1, String value2) {
            addCriterion("verb_filed12 between", value1, value2, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled12NotBetween(String value1, String value2) {
            addCriterion("verb_filed12 not between", value1, value2, "verbFiled12");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13IsNull() {
            addCriterion("verb_filed13 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13IsNotNull() {
            addCriterion("verb_filed13 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13EqualTo(String value) {
            addCriterion("verb_filed13 =", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13NotEqualTo(String value) {
            addCriterion("verb_filed13 <>", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13GreaterThan(String value) {
            addCriterion("verb_filed13 >", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed13 >=", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13LessThan(String value) {
            addCriterion("verb_filed13 <", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13LessThanOrEqualTo(String value) {
            addCriterion("verb_filed13 <=", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13Like(String value) {
            addCriterion("verb_filed13 like", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13NotLike(String value) {
            addCriterion("verb_filed13 not like", value, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13In(List<String> values) {
            addCriterion("verb_filed13 in", values, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13NotIn(List<String> values) {
            addCriterion("verb_filed13 not in", values, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13Between(String value1, String value2) {
            addCriterion("verb_filed13 between", value1, value2, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled13NotBetween(String value1, String value2) {
            addCriterion("verb_filed13 not between", value1, value2, "verbFiled13");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14IsNull() {
            addCriterion("verb_filed14 is null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14IsNotNull() {
            addCriterion("verb_filed14 is not null");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14EqualTo(String value) {
            addCriterion("verb_filed14 =", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14NotEqualTo(String value) {
            addCriterion("verb_filed14 <>", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14GreaterThan(String value) {
            addCriterion("verb_filed14 >", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14GreaterThanOrEqualTo(String value) {
            addCriterion("verb_filed14 >=", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14LessThan(String value) {
            addCriterion("verb_filed14 <", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14LessThanOrEqualTo(String value) {
            addCriterion("verb_filed14 <=", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14Like(String value) {
            addCriterion("verb_filed14 like", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14NotLike(String value) {
            addCriterion("verb_filed14 not like", value, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14In(List<String> values) {
            addCriterion("verb_filed14 in", values, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14NotIn(List<String> values) {
            addCriterion("verb_filed14 not in", values, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14Between(String value1, String value2) {
            addCriterion("verb_filed14 between", value1, value2, "verbFiled14");
            return (Criteria) this;
        }

        public Criteria andVerbFiled14NotBetween(String value1, String value2) {
            addCriterion("verb_filed14 not between", value1, value2, "verbFiled14");
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