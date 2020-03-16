package com.ciana.rule.engine.node.card;

import java.util.Stack;
/**
 * 评分卡计算器
 * @author admin
 *
 */
public class RCardStack {

	
	public Double calcScore(Stack<StarckEntity> stack) {
		
		/*
		 评分卡节点类型定义：type可选择定义
		 T  总分节点     发生在start节点
		 D  汇总维度     设置一组维度占比比重
		 W  字段维度      设置该字段最大分值
		 E  某一维度计算得分    某一维度满足条件后得分 
		*/
		Double tmpE = 0.0; //缓存，用于计算字段得分不能大于字段最大分值
		Double sumE = 0.0;
		Double sumW = 0.0;
		Double sumD = 0.0;
		//遇到 D,计算 sumE/sumW * D; 重置sumE,sumW
		while(!stack.isEmpty()) {
			StarckEntity se = stack.pop();
//			System.out.println(se);
			if("E".equals(se.getType())) {
				tmpE += se.getScore();
				sumE += se.getScore();
			}else if("W".equals(se.getType())) {
				if(se.getScore()< tmpE) {
					//TODO 得分大于最大分值
					sumE = se.getScore();
				}
				sumW += se.getScore();
				tmpE = 0.0;
			}else if("D".equals(se.getType())) {
				Double b = (sumE / sumW );
				if(b>1) {
					b = se.getScore();
				}else {
					b =  b * se.getScore();
				}
//				System.out.println("分组得分："+ b);
				sumD += b;
				sumE = 0.0;
				sumW = 0.0;
			}else if("T".equals(se.getType())) {
				if(sumD> se.getScore()) {
					sumD = se.getScore();
				}
			}
		}
		return sumD;
	}
	
	
	public static void main(String[] args) {
		Stack<StarckEntity> stack = new Stack<>();
		for(int i= 0; i<1000; i++) {
			StarckEntity se = new StarckEntity();
			stack.push(se);
		}
		
		System.out.println(stack.size());
	}

}
