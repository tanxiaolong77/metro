package com.metro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RuleExample   extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RuleExample() {
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

        public Criteria andSkillTypeIsNull() {
            addCriterion("skill_type is null");
            return (Criteria) this;
        }

        public Criteria andSkillTypeIsNotNull() {
            addCriterion("skill_type is not null");
            return (Criteria) this;
        }

        public Criteria andSkillTypeEqualTo(String value) {
            addCriterion("skill_type =", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeNotEqualTo(String value) {
            addCriterion("skill_type <>", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeGreaterThan(String value) {
            addCriterion("skill_type >", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("skill_type >=", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeLessThan(String value) {
            addCriterion("skill_type <", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeLessThanOrEqualTo(String value) {
            addCriterion("skill_type <=", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeLike(String value) {
            addCriterion("skill_type like", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeNotLike(String value) {
            addCriterion("skill_type not like", value, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeIn(List<String> values) {
            addCriterion("skill_type in", values, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeNotIn(List<String> values) {
            addCriterion("skill_type not in", values, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeBetween(String value1, String value2) {
            addCriterion("skill_type between", value1, value2, "skillType");
            return (Criteria) this;
        }

        public Criteria andSkillTypeNotBetween(String value1, String value2) {
            addCriterion("skill_type not between", value1, value2, "skillType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNull() {
            addCriterion("question_type is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNotNull() {
            addCriterion("question_type is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeEqualTo(String value) {
            addCriterion("question_type =", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotEqualTo(String value) {
            addCriterion("question_type <>", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThan(String value) {
            addCriterion("question_type >", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("question_type >=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThan(String value) {
            addCriterion("question_type <", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThanOrEqualTo(String value) {
            addCriterion("question_type <=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLike(String value) {
            addCriterion("question_type like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotLike(String value) {
            addCriterion("question_type not like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIn(List<String> values) {
            addCriterion("question_type in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotIn(List<String> values) {
            addCriterion("question_type not in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeBetween(String value1, String value2) {
            addCriterion("question_type between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotBetween(String value1, String value2) {
            addCriterion("question_type not between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(String value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(String value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(String value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(String value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(String value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLike(String value) {
            addCriterion("job_id like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotLike(String value) {
            addCriterion("job_id not like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<String> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<String> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(String value1, String value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(String value1, String value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andMatchLevelIsNull() {
            addCriterion("match_level is null");
            return (Criteria) this;
        }

        public Criteria andMatchLevelIsNotNull() {
            addCriterion("match_level is not null");
            return (Criteria) this;
        }

        public Criteria andMatchLevelEqualTo(String value) {
            addCriterion("match_level =", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelNotEqualTo(String value) {
            addCriterion("match_level <>", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelGreaterThan(String value) {
            addCriterion("match_level >", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelGreaterThanOrEqualTo(String value) {
            addCriterion("match_level >=", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelLessThan(String value) {
            addCriterion("match_level <", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelLessThanOrEqualTo(String value) {
            addCriterion("match_level <=", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelLike(String value) {
            addCriterion("match_level like", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelNotLike(String value) {
            addCriterion("match_level not like", value, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelIn(List<String> values) {
            addCriterion("match_level in", values, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelNotIn(List<String> values) {
            addCriterion("match_level not in", values, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelBetween(String value1, String value2) {
            addCriterion("match_level between", value1, value2, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andMatchLevelNotBetween(String value1, String value2) {
            addCriterion("match_level not between", value1, value2, "matchLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelIsNull() {
            addCriterion("auth_level is null");
            return (Criteria) this;
        }

        public Criteria andAuthLevelIsNotNull() {
            addCriterion("auth_level is not null");
            return (Criteria) this;
        }

        public Criteria andAuthLevelEqualTo(String value) {
            addCriterion("auth_level =", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelNotEqualTo(String value) {
            addCriterion("auth_level <>", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelGreaterThan(String value) {
            addCriterion("auth_level >", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelGreaterThanOrEqualTo(String value) {
            addCriterion("auth_level >=", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelLessThan(String value) {
            addCriterion("auth_level <", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelLessThanOrEqualTo(String value) {
            addCriterion("auth_level <=", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelLike(String value) {
            addCriterion("auth_level like", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelNotLike(String value) {
            addCriterion("auth_level not like", value, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelIn(List<String> values) {
            addCriterion("auth_level in", values, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelNotIn(List<String> values) {
            addCriterion("auth_level not in", values, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelBetween(String value1, String value2) {
            addCriterion("auth_level between", value1, value2, "authLevel");
            return (Criteria) this;
        }

        public Criteria andAuthLevelNotBetween(String value1, String value2) {
            addCriterion("auth_level not between", value1, value2, "authLevel");
            return (Criteria) this;
        }

        public Criteria andContentRateIsNull() {
            addCriterion("content_rate is null");
            return (Criteria) this;
        }

        public Criteria andContentRateIsNotNull() {
            addCriterion("content_rate is not null");
            return (Criteria) this;
        }

        public Criteria andContentRateEqualTo(String value) {
            addCriterion("content_rate =", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateNotEqualTo(String value) {
            addCriterion("content_rate <>", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateGreaterThan(String value) {
            addCriterion("content_rate >", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateGreaterThanOrEqualTo(String value) {
            addCriterion("content_rate >=", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateLessThan(String value) {
            addCriterion("content_rate <", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateLessThanOrEqualTo(String value) {
            addCriterion("content_rate <=", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateLike(String value) {
            addCriterion("content_rate like", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateNotLike(String value) {
            addCriterion("content_rate not like", value, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateIn(List<String> values) {
            addCriterion("content_rate in", values, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateNotIn(List<String> values) {
            addCriterion("content_rate not in", values, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateBetween(String value1, String value2) {
            addCriterion("content_rate between", value1, value2, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentRateNotBetween(String value1, String value2) {
            addCriterion("content_rate not between", value1, value2, "contentRate");
            return (Criteria) this;
        }

        public Criteria andContentTypeIsNull() {
            addCriterion("content_type is null");
            return (Criteria) this;
        }

        public Criteria andContentTypeIsNotNull() {
            addCriterion("content_type is not null");
            return (Criteria) this;
        }

        public Criteria andContentTypeEqualTo(String value) {
            addCriterion("content_type =", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotEqualTo(String value) {
            addCriterion("content_type <>", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThan(String value) {
            addCriterion("content_type >", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("content_type >=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThan(String value) {
            addCriterion("content_type <", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThanOrEqualTo(String value) {
            addCriterion("content_type <=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLike(String value) {
            addCriterion("content_type like", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotLike(String value) {
            addCriterion("content_type not like", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeIn(List<String> values) {
            addCriterion("content_type in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotIn(List<String> values) {
            addCriterion("content_type not in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeBetween(String value1, String value2) {
            addCriterion("content_type between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotBetween(String value1, String value2) {
            addCriterion("content_type not between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andOneChooseIsNull() {
            addCriterion("one_choose is null");
            return (Criteria) this;
        }

        public Criteria andOneChooseIsNotNull() {
            addCriterion("one_choose is not null");
            return (Criteria) this;
        }

        public Criteria andOneChooseEqualTo(String value) {
            addCriterion("one_choose =", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseNotEqualTo(String value) {
            addCriterion("one_choose <>", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseGreaterThan(String value) {
            addCriterion("one_choose >", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseGreaterThanOrEqualTo(String value) {
            addCriterion("one_choose >=", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseLessThan(String value) {
            addCriterion("one_choose <", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseLessThanOrEqualTo(String value) {
            addCriterion("one_choose <=", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseLike(String value) {
            addCriterion("one_choose like", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseNotLike(String value) {
            addCriterion("one_choose not like", value, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseIn(List<String> values) {
            addCriterion("one_choose in", values, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseNotIn(List<String> values) {
            addCriterion("one_choose not in", values, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseBetween(String value1, String value2) {
            addCriterion("one_choose between", value1, value2, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andOneChooseNotBetween(String value1, String value2) {
            addCriterion("one_choose not between", value1, value2, "oneChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseIsNull() {
            addCriterion("many_choose is null");
            return (Criteria) this;
        }

        public Criteria andManyChooseIsNotNull() {
            addCriterion("many_choose is not null");
            return (Criteria) this;
        }

        public Criteria andManyChooseEqualTo(String value) {
            addCriterion("many_choose =", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseNotEqualTo(String value) {
            addCriterion("many_choose <>", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseGreaterThan(String value) {
            addCriterion("many_choose >", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseGreaterThanOrEqualTo(String value) {
            addCriterion("many_choose >=", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseLessThan(String value) {
            addCriterion("many_choose <", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseLessThanOrEqualTo(String value) {
            addCriterion("many_choose <=", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseLike(String value) {
            addCriterion("many_choose like", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseNotLike(String value) {
            addCriterion("many_choose not like", value, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseIn(List<String> values) {
            addCriterion("many_choose in", values, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseNotIn(List<String> values) {
            addCriterion("many_choose not in", values, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseBetween(String value1, String value2) {
            addCriterion("many_choose between", value1, value2, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andManyChooseNotBetween(String value1, String value2) {
            addCriterion("many_choose not between", value1, value2, "manyChoose");
            return (Criteria) this;
        }

        public Criteria andJudgeIsNull() {
            addCriterion("judge is null");
            return (Criteria) this;
        }

        public Criteria andJudgeIsNotNull() {
            addCriterion("judge is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeEqualTo(String value) {
            addCriterion("judge =", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeNotEqualTo(String value) {
            addCriterion("judge <>", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeGreaterThan(String value) {
            addCriterion("judge >", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeGreaterThanOrEqualTo(String value) {
            addCriterion("judge >=", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeLessThan(String value) {
            addCriterion("judge <", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeLessThanOrEqualTo(String value) {
            addCriterion("judge <=", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeLike(String value) {
            addCriterion("judge like", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeNotLike(String value) {
            addCriterion("judge not like", value, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeIn(List<String> values) {
            addCriterion("judge in", values, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeNotIn(List<String> values) {
            addCriterion("judge not in", values, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeBetween(String value1, String value2) {
            addCriterion("judge between", value1, value2, "judge");
            return (Criteria) this;
        }

        public Criteria andJudgeNotBetween(String value1, String value2) {
            addCriterion("judge not between", value1, value2, "judge");
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