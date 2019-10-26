package com.ass.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCourseExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andWeektimeIsNull() {
            addCriterion("weektime is null");
            return (Criteria) this;
        }

        public Criteria andWeektimeIsNotNull() {
            addCriterion("weektime is not null");
            return (Criteria) this;
        }

        public Criteria andWeektimeEqualTo(Integer value) {
            addCriterion("weektime =", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeNotEqualTo(Integer value) {
            addCriterion("weektime <>", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeGreaterThan(Integer value) {
            addCriterion("weektime >", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("weektime >=", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeLessThan(Integer value) {
            addCriterion("weektime <", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeLessThanOrEqualTo(Integer value) {
            addCriterion("weektime <=", value, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeIn(List<Integer> values) {
            addCriterion("weektime in", values, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeNotIn(List<Integer> values) {
            addCriterion("weektime not in", values, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeBetween(Integer value1, Integer value2) {
            addCriterion("weektime between", value1, value2, "weektime");
            return (Criteria) this;
        }

        public Criteria andWeektimeNotBetween(Integer value1, Integer value2) {
            addCriterion("weektime not between", value1, value2, "weektime");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Integer value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Integer value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Integer value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Integer value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Integer value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Integer> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Integer> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Integer value1, Integer value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andAssessIsNull() {
            addCriterion("assess is null");
            return (Criteria) this;
        }

        public Criteria andAssessIsNotNull() {
            addCriterion("assess is not null");
            return (Criteria) this;
        }

        public Criteria andAssessEqualTo(String value) {
            addCriterion("assess =", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotEqualTo(String value) {
            addCriterion("assess <>", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessGreaterThan(String value) {
            addCriterion("assess >", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessGreaterThanOrEqualTo(String value) {
            addCriterion("assess >=", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLessThan(String value) {
            addCriterion("assess <", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLessThanOrEqualTo(String value) {
            addCriterion("assess <=", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessLike(String value) {
            addCriterion("assess like", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotLike(String value) {
            addCriterion("assess not like", value, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessIn(List<String> values) {
            addCriterion("assess in", values, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotIn(List<String> values) {
            addCriterion("assess not in", values, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessBetween(String value1, String value2) {
            addCriterion("assess between", value1, value2, "assess");
            return (Criteria) this;
        }

        public Criteria andAssessNotBetween(String value1, String value2) {
            addCriterion("assess not between", value1, value2, "assess");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andSavnumIsNull() {
            addCriterion("savNum is null");
            return (Criteria) this;
        }

        public Criteria andSavnumIsNotNull() {
            addCriterion("savNum is not null");
            return (Criteria) this;
        }

        public Criteria andSavnumEqualTo(Integer value) {
            addCriterion("savNum =", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumNotEqualTo(Integer value) {
            addCriterion("savNum <>", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumGreaterThan(Integer value) {
            addCriterion("savNum >", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("savNum >=", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumLessThan(Integer value) {
            addCriterion("savNum <", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumLessThanOrEqualTo(Integer value) {
            addCriterion("savNum <=", value, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumIn(List<Integer> values) {
            addCriterion("savNum in", values, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumNotIn(List<Integer> values) {
            addCriterion("savNum not in", values, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumBetween(Integer value1, Integer value2) {
            addCriterion("savNum between", value1, value2, "savnum");
            return (Criteria) this;
        }

        public Criteria andSavnumNotBetween(Integer value1, Integer value2) {
            addCriterion("savNum not between", value1, value2, "savnum");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNull() {
            addCriterion("departmentId is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNotNull() {
            addCriterion("departmentId is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidEqualTo(Integer value) {
            addCriterion("departmentId =", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotEqualTo(Integer value) {
            addCriterion("departmentId <>", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThan(Integer value) {
            addCriterion("departmentId >", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("departmentId >=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThan(Integer value) {
            addCriterion("departmentId <", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThanOrEqualTo(Integer value) {
            addCriterion("departmentId <=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIn(List<Integer> values) {
            addCriterion("departmentId in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotIn(List<Integer> values) {
            addCriterion("departmentId not in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidBetween(Integer value1, Integer value2) {
            addCriterion("departmentId between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotBetween(Integer value1, Integer value2) {
            addCriterion("departmentId not between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNull() {
            addCriterion("groups is null");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNotNull() {
            addCriterion("groups is not null");
            return (Criteria) this;
        }

        public Criteria andGroupsEqualTo(String value) {
            addCriterion("groups =", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotEqualTo(String value) {
            addCriterion("groups <>", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThan(String value) {
            addCriterion("groups >", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThanOrEqualTo(String value) {
            addCriterion("groups >=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThan(String value) {
            addCriterion("groups <", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThanOrEqualTo(String value) {
            addCriterion("groups <=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLike(String value) {
            addCriterion("groups like", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotLike(String value) {
            addCriterion("groups not like", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsIn(List<String> values) {
            addCriterion("groups in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotIn(List<String> values) {
            addCriterion("groups not in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsBetween(String value1, String value2) {
            addCriterion("groups between", value1, value2, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotBetween(String value1, String value2) {
            addCriterion("groups not between", value1, value2, "groups");
            return (Criteria) this;
        }

        public Criteria andWayIsNull() {
            addCriterion("way is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("way is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(String value) {
            addCriterion("way =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(String value) {
            addCriterion("way <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(String value) {
            addCriterion("way >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(String value) {
            addCriterion("way >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(String value) {
            addCriterion("way <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(String value) {
            addCriterion("way <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLike(String value) {
            addCriterion("way like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotLike(String value) {
            addCriterion("way not like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<String> values) {
            addCriterion("way in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<String> values) {
            addCriterion("way not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(String value1, String value2) {
            addCriterion("way between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(String value1, String value2) {
            addCriterion("way not between", value1, value2, "way");
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