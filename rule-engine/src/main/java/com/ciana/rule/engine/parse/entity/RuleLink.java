package com.ciana.rule.engine.parse.entity;

public class RuleLink {

	private String id;
	private String type;
	private String sourceId;
	private String targetId;
	private String relations;
	private Integer priority;
	private String execs; //是否执行，默认false
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getRelations() {
		return relations;
	}
	public void setRelations(String relations) {
		this.relations = relations;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getExecs() {
		return execs;
	}
	public void setExecs(String execs) {
		this.execs = execs;
	}
	
}
