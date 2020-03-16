package com.ciana.rule.engine.parse.entity;

import java.util.ArrayList;
import java.util.List;

public class RuleNode {

	private String id;
	private String name;
	private String ruleGroupId;
	private String ruleGroupName;
	private String detail;
	private String type;
	private Integer depth;
	private Integer model;
	private String matchCondition;
	private String ruleContent;
	private Integer action;
	private Integer level;
	private Integer hasSpecialRule;
	private Integer status;
	private String cardNodeType; 
	private String datatype; //data: 获取数据模式
	private String dataSourceName; //数据源
	private String sqlStr; //data: 查询语句，条件通过 #param# 获取
	private String resturl; //data: rest接口
	private String cachekey; //data: 缓存id
	private String intfname; //自定义实现
	private String childCode;
	private String childName;
	private String version;
	private List<Param> params = new ArrayList<Param>();  //只有start节点才有
	private List<Data> datas = new ArrayList<Data>();  //数据节点才有
	private Boolean isDevp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRuleGroupId() {
		return ruleGroupId;
	}
	public void setRuleGroupId(String ruleGroupId) {
		this.ruleGroupId = ruleGroupId;
	}
	public String getRuleGroupName() {
		return ruleGroupName;
	}
	public void setRuleGroupName(String ruleGroupName) {
		this.ruleGroupName = ruleGroupName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getModel() {
		return model;
	}
	public void setModel(Integer model) {
		this.model = model;
	}
	public String getMatchCondition() {
		return matchCondition;
	}
	public void setMatchCondition(String matchCondition) {
		this.matchCondition = matchCondition;
	}
	public String getRuleContent() {
		return ruleContent;
	}
	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getHasSpecialRule() {
		return hasSpecialRule;
	}
	public void setHasSpecialRule(Integer hasSpecialRule) {
		this.hasSpecialRule = hasSpecialRule;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Param> getParams() {
		return params;
	}
	public void addParam(Param param) {
		this.params.add(param);
	}
	public List<Data> getDatas() {
		return datas;
	}
	public void addData(Data data) {
		this.datas.add(data);
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getCardNodeType() {
		return cardNodeType;
	}
	public void setCardNodeType(String cardNodeType) {
		this.cardNodeType = cardNodeType;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getResturl() {
		return resturl;
	}
	public void setResturl(String resturl) {
		this.resturl = resturl;
	}
	public String getCachekey() {
		return cachekey;
	}
	public void setCachekey(String cachekey) {
		this.cachekey = cachekey;
	}
	public String getIntfname() {
		return intfname;
	}
	public void setIntfname(String intfname) {
		this.intfname = intfname;
	}
	public String getChildCode() {
		return childCode;
	}
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Boolean getDevp() {
		return isDevp;
	}
	public void setDevp(Boolean isDevp) {
		this.isDevp = isDevp;
	}
	
	@Override
	public String toString() {
		return id+"  "+name+"  "+ruleContent;
	}
}
