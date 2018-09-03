package com.metro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerExample  extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnswerExample() {
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

        public Criteria andSortIdIsNull() {
            addCriterion("sort_id is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Integer value) {
            addCriterion("sort_id =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Integer value) {
            addCriterion("sort_id <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Integer value) {
            addCriterion("sort_id >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_id >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Integer value) {
            addCriterion("sort_id <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Integer value) {
            addCriterion("sort_id <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Integer> values) {
            addCriterion("sort_id in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Integer> values) {
            addCriterion("sort_id not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Integer value1, Integer value2) {
            addCriterion("sort_id between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_id not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("question_id like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("question_id not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andAnswerDescIsNull() {
            addCriterion("answer_desc is null");
            return (Criteria) this;
        }

        public Criteria andAnswerDescIsNotNull() {
            addCriterion("answer_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerDescEqualTo(String value) {
            addCriterion("answer_desc =", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescNotEqualTo(String value) {
            addCriterion("answer_desc <>", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescGreaterThan(String value) {
            addCriterion("answer_desc >", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescGreaterThanOrEqualTo(String value) {
            addCriterion("answer_desc >=", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescLessThan(String value) {
            addCriterion("answer_desc <", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescLessThanOrEqualTo(String value) {
            addCriterion("answer_desc <=", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescLike(String value) {
            addCriterion("answer_desc like", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescNotLike(String value) {
            addCriterion("answer_desc not like", value, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescIn(List<String> values) {
            addCriterion("answer_desc in", values, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescNotIn(List<String> values) {
            addCriterion("answer_desc not in", values, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescBetween(String value1, String value2) {
            addCriterion("answer_desc between", value1, value2, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerDescNotBetween(String value1, String value2) {
            addCriterion("answer_desc not between", value1, value2, "answerDesc");
            return (Criteria) this;
        }

        public Criteria andAnswerImageIsNull() {
            addCriterion("answer_image is null");
            return (Criteria) this;
        }

        public Criteria andAnswerImageIsNotNull() {
            addCriterion("answer_image is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerImageEqualTo(String value) {
            addCriterion("answer_image =", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageNotEqualTo(String value) {
            addCriterion("answer_image <>", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageGreaterThan(String value) {
            addCriterion("answer_image >", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageGreaterThanOrEqualTo(String value) {
            addCriterion("answer_image >=", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageLessThan(String value) {
            addCriterion("answer_image <", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageLessThanOrEqualTo(String value) {
            addCriterion("answer_image <=", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageLike(String value) {
            addCriterion("answer_image like", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageNotLike(String value) {
            addCriterion("answer_image not like", value, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageIn(List<String> values) {
            addCriterion("answer_image in", values, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageNotIn(List<String> values) {
            addCriterion("answer_image not in", values, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageBetween(String value1, String value2) {
            addCriterion("answer_image between", value1, value2, "answerImage");
            return (Criteria) this;
        }

        public Criteria andAnswerImageNotBetween(String value1, String value2) {
            addCriterion("answer_image not between", value1, value2, "answerImage");
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