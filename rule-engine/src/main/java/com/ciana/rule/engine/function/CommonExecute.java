package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleRunner;

@Deprecated
public class CommonExecute implements ExecInterface{
	/**
	 * Common 规则执行节点
	
            规则条件： 在规则语法中属于  if 范围   规则动作：在规则语法中属于 then 范围
            当只输入条件时，返回值肯定是 true/false
                 规则条件   规则动作    
                输入       输入        规则满足时执行动作,返回动作结果
                输入       不输入      规则满足时，动作默认取条件返回结果返回
                不输入     输入        直接执行动作，返回接口不固定
                不输入     不输入       错误
         
        
	 */

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		return lastParams;
	}
	
	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		//TODO  当前处理只考虑 第三种情况，目前还没有场景要求必须分开处理
		if(node.getMatchCondition() == null || "".equals(node.getMatchCondition().trim())) {
			return false;  //设定缺省值默认为false
		}
//		System.out.println("执行语句："+node.toString());
		try {
			Object obj = RuleRunner.execute(node.getMatchCondition(), params);
			dLog.setObjIf(obj);
			dLog.setCode("success");
			return obj;
		} catch (Exception e) {
			//TODO  需要完善异常处理
			dLog.setCode("fail");
			dLog.setMessage(e.getMessage());
			throw e;
		}
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		// TODO Auto-generated method stub
		if(node.getRuleContent() == null || "".equals(node.getRuleContent().trim())) {
			return obj;
		}
//		System.out.println("执行语句："+node.toString());
		if(obj instanceof Boolean) {
			if(!(Boolean)obj) {
				return obj; //如果条件为false,则不执行动作
			}
		}
		try {
			obj = RuleRunner.execute(node.getRuleContent(), params);
			
			dLog.setObjThen(obj);
			dLog.setCode("success");
			return obj;
		} catch (Exception e) {
			//TODO  需要完善异常处理
			dLog.setCode("fail");
			dLog.setMessage(e.getMessage());
			throw e;
		}
	}

//	/**
//	  	 	//处理当前link路由与源节点的关系，这个定义场景还不太好确定
//			
//			//暂定规则：当前节点为 true , link-relation = or  则不执行后续节点
//			//         当前节点为false , link-relation = and 则不执行后续节点
//			//         RTree RTable RSet 只能返回true/false
//	 */
//	@Override
//	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params,
//			Object objIf, Object objThen) throws Exception {
//		// TODO Auto-generated method stub
//		
//		boolean is_leaf = false;
//		if(linkList==null || linkList.size()==0 ) {//叶子节点
//			is_leaf = true;
//		}
//		//RSet   子节点都需要执行，这里只需要判断 link路由是否为true
//		if("RSet".equals(ruleType)) {
//			if(objIf instanceof Boolean) {
//				Boolean lastObj = (Boolean)objIf;
//				if(!lastObj) {
//					return 1; //返回false  终结规则树
//				}
//			}else {
//				//TODO  语法还不确定定义，应该是错误
//			}
//			if(is_leaf) {
//				
//				return 0;
//			}
//			
//			//TODO 没有判断路由条件
//			return linkList;
//		}
//		//RTree RTable 如果为叶子节点，则有可能是最终结论
//		if("RTree".equals(ruleType) || "RTable".equals(ruleType)) {
//			if(is_leaf) {
//				if(objIf instanceof Boolean) {
//					Boolean lastObj = (Boolean)objIf;
//					if(lastObj) {
//						return 1; //返回false  终结规则树
//					}
//				}else {
//					//TODO  语法还不确定定义，应该是错误
//				}
//				return 0;
//			}
//			
//			//TODO 没有判断路由条件
//			return linkList;
//		}
//		return null;
//	}

}
