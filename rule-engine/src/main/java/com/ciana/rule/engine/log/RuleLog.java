package com.ciana.rule.engine.log;
/**
 * 每个ruleGroupInfo 的每次执行有一个日志 RuleLog日志
 * @author admin
 *
 */
public class RuleLog {
	
	private String id;//表主键
	private String ruleCode; //规则码
	private String version;
	private String ruleGroupId;
	private String params;//输入参数  json格式
	private String ruleGroupName;
	private String opTime;
	private Object result;//规则返回结果
	private String code; //执行成功、失败
	private String message;//失败原因
	private String execLog;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
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
	public String getExecLog() {
		return execLog;
	}
	public void setExecLog(String execLog) {
		this.execLog = execLog;
	}
}
