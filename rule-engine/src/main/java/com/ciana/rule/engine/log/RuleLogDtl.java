package com.ciana.rule.engine.log;
/**
 * 每个节点有一个日志
 * @author admin
 *
 */
public class RuleLogDtl {
	

	private String id;
	private String ruleLogId;
	private String ruleGroupId;
	private String ruleGroupName;
	private String ruleId;
	private String ruleName;
	private String match;
	private String content;
	private String version;
	private String description;
	private String params;
	private String opTime;
	private Integer depth;//当前节点所在节点深度
	private String nodeType;
	
	private Object objIf;
	private Object objThen;
	private String code;
	private String message;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleLogId() {
		return ruleLogId;
	}
	public void setRuleLogId(String ruleLogId) {
		this.ruleLogId = ruleLogId;
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
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public Object getObjIf() {
		return objIf;
	}
	public void setObjIf(Object objIf) {
		this.objIf = objIf;
	}
	public Object getObjThen() {
		return objThen;
	}
	public void setObjThen(Object objThen) {
		this.objThen = objThen;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	//尽量能达到重现

}
