package com.ciana.rule.engine.check;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.check.node.CNInterface;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 语法语义校验
 * @author admin
 *
 */
public class RuleCheck {
	
	private CheckLog log = new CheckLog(); 
	
	public CheckLog getLog() {
		return log;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void check(String id, RuleParse parse, Map<String, Object> params) {
		RuleNode node = parse.getRuleNode(id);
		//与 RuleExecute中一致
		setDepth(id, node, parse, true);
		
		log.info(node, "开始校验节点：      " + node.getName());
		//校验节点
		checkNode(id, node, parse, params);
		
		List<RuleLink> linkList = parse.getRuleLinkList(id);
		if(linkList!=null && linkList.size()>0) {
			//排序
	 		linkList.sort(new Comparator() {
	 			@Override
				public int compare(Object o1, Object o2) {
					Integer pri1 = ((RuleLink)o1).getPriority();
					Integer pri2 = ((RuleLink)o2).getPriority();
					return pri1 - pri2;
				}
			});
	 		
	 		for(RuleLink link: linkList) {
	 			//路由校验
	 			checkLink(node, link);
	 			check(link.getTargetId(), parse, params);
	 		}
		}
	}

	private void checkNode(String id, RuleNode node, RuleParse parse, Map<String, Object> params){

		try {
			CNInterface cnIntf = CheckUtil.getInstanceClass(parse.getRuletype(), node.getType());
			
			cnIntf.check(id, node, parse, params, log);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(node, "检查异常:"+e.getMessage());
		}
	}

	private void checkLink(RuleNode node,RuleLink link) {
//		System.out.println("路由校验"+link.getTargetId() );
		log.info(node, "路由："+link.getTargetId());
		String rela = link.getRelations();
		if(!("AND".equals(rela) || "OR".equals(rela))) {
			log.error(node, "路由关系取值只能选 AND / OR; 默认设置 AND");
		}
		Integer priority = link.getPriority();
		if(priority == null) {
			log.error(node, "优先级设置为数值，值越小优先级越高，不限制负数");
		}
		
	}
	

	/**
	 * 与 RuleExecute中一致
	 * @param id
	 * @param node
	 * @param parse
	 * @return
	 */
	private int setDepth(String id, RuleNode node, RuleParse parse, boolean b) {
		RuleNode parentNode = parse.getParentNode(id);
		int depth;
		if(parentNode ==null) {
			depth = 1;
		}else {
			Integer pdepth = parentNode.getDepth();
			if(pdepth == null) { //如果有多个节点，解析时只保留一个，这里会导致父节点没有深度值
				pdepth = setDepth(parentNode.getId(), parentNode, parse, false);
			}
			depth = pdepth + 1;
		}
		if(node.getDepth()!=null && b) {
			log.error(node,"节点    "+node.getName()+"   找到多个父类");// b 参数控制只检查当前节点,只对当前节点赋值
			//TODO 这个检查不正确，目的是检查没有死循环节点
		}
		if(b) {
			node.setDepth(depth);
		}
		
		return depth;
	}
	
}
