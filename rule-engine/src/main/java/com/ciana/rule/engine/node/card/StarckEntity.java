package com.ciana.rule.engine.node.card;

/**
 * 评分卡压栈算法栈元素对象
 * @author admin
 *
 */
public class StarckEntity {

	/*
	 评分卡节点类型定义：type可选择定义
	 T  总分节点     发生在start节点
	 D  汇总维度     设置一组维度占比比重
	 W  字段维度      设置该字段最大分值
	 E  某一维度计算得分    某一维度满足条件后得分 
	 */
	private String type;// 节点类型
	private Double score; //元素值
	private String nodeName;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	@Override
	public String toString() {
		return "type="+type+", score="+score+", nodeName="+nodeName;
	}
	
}
