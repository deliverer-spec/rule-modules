package com.ciana.rule.engine.link;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.link.intf.LinkInterface;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class SetLink implements LinkInterface {

	/**
	 * //处理当前link路由与源节点的关系，这个定义场景还不太好确定
	 * 
	 * //暂定规则：当前节点为 true , link-relation = or 则不执行后续节点 // 当前节点为false , link-relation
	 * = and 则不执行后续节点 // RTree RTable RSet 只能返回true/false
	 */
	@Override
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params,
			Object objIf, Object objThen) throws Exception {
		// TODO Auto-generated method stub

		boolean is_leaf = false;
		if (linkList == null || linkList.size() == 0) {// 叶子节点
			is_leaf = true;
		}
		//TODO  set 为什么要定义叶子节点全部成功
		// RSet 子节点都需要执行，这里只需要判断 link路由是否为true
		if (objIf instanceof Boolean) {
			Boolean lastObj = (Boolean) objIf;
			if (!lastObj) {
				return 1; // 返回false 终结规则树 
			}
		} else {
			// TODO 语法还不确定定义，应该是错误
		}
		if (is_leaf) {

			return 0;
		}

		// TODO 没有判断路由条件
		return linkList;
	}
}
