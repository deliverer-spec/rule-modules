package com.ciana.rule.engine.link;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.link.intf.LinkInterface;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class TableLink implements LinkInterface{

	/**
	  	 	//处理当前link路由与源节点的关系，这个定义场景还不太好确定
			
			 * 规则表节点逻辑：路由中的 and/or 有意义
	 */
	@Override
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params,
			Object objIf, Object objThen) throws Exception {
		// TODO Auto-generated method stub
		
		boolean is_leaf = false;
		if(linkList==null || linkList.size()==0 ) {//叶子节点
			is_leaf = true;
		}
		
		if(!is_leaf) {
			if(objIf instanceof Boolean) {
				Boolean lastObj = (Boolean)objIf;
				if(!lastObj) {
					return -1; //规则树找到符合条件的节点  终结规则树
				}
			}else {
				//TODO  语法还不确定定义，应该是错误
				throw new Exception("语法错误，此节点返回动作必须是布尔值");
			}
		}
		if(is_leaf) {
			if(objIf instanceof Boolean) {
				Boolean lastObj = (Boolean)objIf;
				if(lastObj) {
					return 1; //规则树找到符合条件的节点  终结规则树
				}
			}else {
				//TODO  语法还不确定定义，应该是错误
			}
			return 0;
		}
		
		return linkList;
	}
}
