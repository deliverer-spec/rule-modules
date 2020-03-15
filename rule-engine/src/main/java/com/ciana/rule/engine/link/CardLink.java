package com.ciana.rule.engine.link;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.card.StarckEntity;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class CardLink implements LinkInterface{
	/**
	 	//处理当前link路由与源节点的关系，这个定义场景还不太好确定
	
		//暂定规则：当前节点为 true , link-relation = or  则不执行后续节点
		//         当前节点为false , link-relation = and 则不执行后续节点
		//         RTree RTable RSet 只能返回true/false
	*/
	@Override
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params,
			Object objIf, Object objThen) throws Exception {
		boolean is_leaf = false;
		if(linkList==null || linkList.size()==0 ) {//叶子节点
			is_leaf = true;
		}
		
		
		if(is_leaf) {
			if(objIf instanceof Boolean) {
				Boolean lastObj = (Boolean)objIf;
				if(lastObj) {
					StarckEntity se = new StarckEntity();
					se.setType("E");
					se.setScore(Double.valueOf(node.getRuleContent()));
					se.setNodeName(node.getName()+"A");
					
					return se;
				}
			}else if(objIf instanceof Integer) {
				StarckEntity se = new StarckEntity();
				se.setType("E");
				se.setScore(Double.valueOf((Integer)objIf));
				se.setNodeName(node.getName()+"B");
				
				return se;
			}else {
				//TODO  语法还不确定定义，应该是错误
			}
//			return 0; //表示该叶子节点不满足计分条件
		}
		
		//TODO 没有判断路由条件
		return linkList;
	}

}
