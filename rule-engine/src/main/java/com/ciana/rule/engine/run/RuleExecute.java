package com.ciana.rule.engine.run;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.alibaba.fastjson.JSON;
import com.ciana.rule.engine.card.RCardStack;
import com.ciana.rule.engine.card.StarckEntity;
import com.ciana.rule.engine.function.ClassUtil;
import com.ciana.rule.engine.function.ExecInterface;
import com.ciana.rule.engine.link.LinkInterface;
import com.ciana.rule.engine.link.LinkUtil;
import com.ciana.rule.engine.log.RuleLog;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class RuleExecute {
	
	private Stack<StarckEntity> stack = null;
	
	/**
	 * 规则调用，返回调用日志
	 * @param id
	 * @param type
	 * @param parse
	 * @param req_params
	 * @param isChiled
	 * @return
	 * @throws Exception
	 */
	public List<RuleLogDtl> execute(String id, String type, RuleParse parse, Map<String, Object> req_params, RuleLog rlog) throws Exception  {
		//1 处理传入参数
		List<RuleLogDtl> logList = new ArrayList<>();
		if("RCard".equals(parse.getRuletype())) {//评分卡 初始化栈
			stack = new Stack<>();
		}
		//2 处理节点
		execNode(id, parse, req_params, logList);
		
		//3 评分卡计算
		if("RCard".equals(parse.getRuletype())) {//评分卡 初始化栈
			stack(logList, parse);
		}
		
		return logList;
	}
	
	//将  execute 的返回值作为是否叶子节点的结束依据 ，  树 + 表  
	//决策流， 遇见错误则停止
	//卡 ： 有两种情况，第一种   某个维度的判断只在一个节点上进行   这种不需要结束
	//                第二种   叶子节点这是某个维度的一个条件       这种如果不结束，则使用符合条件的都记录，就是完全执行
	
	//参数处理有两种方案
	//最大范围参数处理，即全程只维护一个map，所有参数全放里面
	//严格参数处理。即每个节点都需要配置入参；data节点参数作用范围是子节点以下
	//
	//测试特殊逻辑：数据域增加设置默认值； 跳出规则不考虑类型（完全测试节点）
	//
	/**
	 * 执行所有节点，回调  注意这些返回结果不要与接口返回结果混淆
	 * 注意  首次传入的node一定要是start节点
	 * 当前节点处理下级链表返回定义：0  叶子节点返回
	 * 							   1 叶子节点，但已匹配结果，终止所有节点     规则树使用
	 * 							   -1 非叶子节点，动作条件为false，终止本节点以下     规则树使用
	 * 							   2 叶子节点，找到结果，但只终止上一节点     仅评分卡逻辑使用
	 * 							null 空值返回，等同0继续 0
	 * 							list 返回待执行下级节点
	 * 当前节点接收下一节点返回值定义： 0  程序继续
	 * 					  1 程序退出节点验证，终止所有节点
	 * 					  2 程序停止当前节点后续验证(标准卡示例)，终止上级节点
	 * @param id
	 * @param parse
	 * @param params
	 * @return  true 继续执行  ； false 不执行后续代码
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int execNode(String id, RuleParse parse, Map<String, Object> last_params, List<RuleLogDtl> logList) throws Exception{
		//取出node
		RuleNode node = parse.getRuleNode(id);
		//处理相应node
		System.out.println(node);
		Object objIf = null;
		Object objThen = null;

		RuleLogDtl dLog = parse.newRuleLogDtl();
		dLog.setDescription(node.getDetail());
		dLog.setNodeType(node.getType());
		dLog.setDepth(setDepth(id, node, parse));
		dLog.setRuleId(node.getId());
		dLog.setRuleName(node.getName());
		dLog.setMatch(node.getMatchCondition());
		dLog.setContent(node.getRuleContent());
		
		ExecInterface execIntf = ClassUtil.getInstanceClass(parse.getRuletype(), node.getType());
		//初始化参数
		Map<String, Object> params = execIntf.initParams(node, last_params, parse.isDevp());
		
		//执行条件
		objIf = execIntf.ifExec(parse.getRuletype(), node, params, dLog);
		dLog.setObjIf(objIf);
		
		//执行动作
		objThen = execIntf.thenExec(parse.getRuletype(), node, params, dLog, objIf);
		List<RuleLogDtl> childLogs = null;
		if(objThen instanceof StarckEntity) {
			stack.add((StarckEntity)objThen);
		}else if(objThen instanceof List) {//子规则 返回结果
			childLogs = (List<RuleLogDtl>)objThen;
			//获取子规则返回结果
			RuleLogDtl log = childLogs.get(childLogs.size()-1);
			objThen = log.getObjThen();
			
			dLog.setObjIf(log.getObjIf());
			objIf = log.getObjIf();
		}
		
		dLog.setObjThen(objThen);
		dLog.setOpTime(new Date().toString());
		dLog.setParams(JSON.toJSONString(params));
		
//		System.out.println(JSON.toJSONString(dLog));
		logList.add(dLog);
		
		if(childLogs!=null) {
			for(RuleLogDtl log: childLogs) {
				logList.add(log);
			}
		}
		
		//执行下级路由
		LinkInterface linkIntf = LinkUtil.getInstanceClass(parse.getRuletype(), node.getType());
		Object linkObj = linkIntf.syncLinks(parse.getRuletype(), node, parse.getRuleLinkList(id), params, objIf, objThen);
		if(linkObj == null) {
			return 0;
		}
		if(linkObj instanceof StarckEntity) {
			stack.add((StarckEntity)linkObj);
			// 评分卡评分成功，下级节点不需要执行，直到回退到节点动作是分组或者维度
			return 2;
		}
		
		if(linkObj instanceof Integer) {
			Integer intObj = (Integer)linkObj;
			if(intObj==0) {
				return 0;
			}else if(intObj==1) {
				//规则树，找到符合条件的节点
				return 1;
			}else if(intObj==-1) {
				//规则树，非叶子节点，条件不满足，以后节点不执行
				return -1;
			}
//			else if(intObj==2) {
//				//TODO 评分卡 返回叶子节点分值
//				return 2;
//			}
		}
		
		
		if(linkObj instanceof List) {
			List<RuleLink> linkList = (List<RuleLink>)linkObj;
			//排序
	 		linkList.sort(new Comparator() {
				public int compare(Object o1, Object o2) {
					Integer pri1 = ((RuleLink)o1).getPriority();
					Integer pri2 = ((RuleLink)o2).getPriority();
					return pri1 - pri2;
				}			
			});
			//如果存在执行下级节点
			for(RuleLink link: linkList) {
				//回调execute
				int nextObj = execNode(link.getTargetId(), parse, params, logList);
				if(nextObj == 0) {
					//继续,不做处理
				}else if(nextObj == 1) {
					//评分卡，找到符合条件的叶子节点
					return 1;
				}else if(nextObj == -1) {
					//评分卡，非叶子节点不符合条件，父节点继续查找其他路由
				}else if(nextObj == 2) {
					if("W".equals(node.getCardNodeType())) {//当前是维度节点才结束
						break;
					}
				}else {
					System.out.println("未定义的返回状态");
				}
			}
			
		}
		
		return 0;
	}
	
	/**
	 * 不预先计算节点深度，因为没有找到使用场景
	 * @param id
	 * @param node
	 * @param parse
	 * @return
	 */
	private int setDepth(String id, RuleNode node, RuleParse parse) {
		RuleNode parentNode = parse.getParentNode(id);
		int depth;
		if(parentNode ==null) {
			depth = parse.getDepth();
		}else {
			depth = parentNode.getDepth() + 1;
		}
		node.setDepth(depth);
		
		return depth;
	}
	
	private void stack(List<RuleLogDtl> logList, RuleParse parse) {
		
		RCardStack rs = new RCardStack();
		Double sumD = rs.calcScore(stack);
//		System.out.println("计算得分："+ sumD);
		
		RuleLogDtl dLog = parse.newRuleLogDtl();
		dLog.setDescription("评分值");
		dLog.setNodeType("");
		dLog.setRuleId("");
		dLog.setRuleName("评分值结果");
		dLog.setMatch("true");
		dLog.setContent("score");
		
		dLog.setObjThen(sumD);
		dLog.setOpTime(new Date().toString());
		dLog.setDepth(logList.get(logList.size()-1).getDepth());
		
//		System.out.println(JSON.toJSONString(dLog));
		logList.add(dLog);
	}
	
}
