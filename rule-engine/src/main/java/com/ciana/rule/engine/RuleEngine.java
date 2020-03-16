package com.ciana.rule.engine;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.node.child.FindAllChild;
import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.log.RuleLog;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.run.RuleExecute;

public class RuleEngine {

	private Object obj; //规则返回结果
	private List<RuleLogDtl> logs; //调用日志栈
	private RuleLog rlog; //执行日志
	
	public Object getObj() {
		return obj;
	}
	public List<RuleLogDtl> getLogs() {
		return logs;
	}
	public RuleLog getRlog() {
		return rlog;
	}
	/**
	 * 开发调用
	 * @return
	 */
	public Object develop(Rule rule, Map<String, Object> params) {
		return exec(rule, params, true);
	}
	
	/**
	 * 执行规则
	 * @param rule
	 * @param params
	 * @return
	 */
	public Object exec(Rule rule, Map<String, Object> params) {
		return exec(rule, params, false);
	}
	
	
	private Object exec(Rule rule, Map<String, Object> params, Boolean isDevp) {
		RuleLog log = new RuleLog();
		try {
			log.setRuleGroupId(rule.getId());
			log.setRuleCode(rule.getCode());
			log.setVersion(rule.getVersion());
			log.setRuleGroupName(rule.getName());
			log.setParams(params.toString());//需要转json
			
			RuleParse parse = new RuleParse(rule.getRuleJson());
			parse.setRuleLog(log);
			parse.setDevp(isDevp);
			
			RuleExecute exec = new RuleExecute();
			logs = exec.execute(parse.getStartId(),parse.getRuletype(), parse, params, log);
			
			if(logs.size()>0) {
				RuleLogDtl dlog = logs.get(logs.size()-1);
				
				//这里需要区分类型
				obj = dlog.getObjThen();
				
//				System.out.println(" 返回结果："+JSON.toJSON(log));
			}
			
			String msg = printLog(logs);
			log.setExecLog(msg);
			
			log.setCode("success");
			log.setResult(obj);
		} catch (Exception e) {
			log.setCode("fail");
			log.setMessage(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		log.setOpTime(new Date().toString());
		
		this.rlog = log;
		
		return log.getResult();
	}
	/**
	 * 从规则中解析rule基本信息
	 * @param rule
	 * @return
	 */
	public Rule getRule(String ruleStr) {
		RuleParse parse = new RuleParse(ruleStr);
		Rule rule = new Rule();
		rule.setId(parse.getId());
		rule.setCode(parse.getCode());
		rule.setName(parse.getName());
		rule.setRuletype(parse.getRuletype());
		rule.setDescript(parse.getDescript());
		
		return rule;
	}
	
	/**
	 * 查询编辑域所有子规则
	 * @param ruleStr
	 * @return
	 */
	public List<Map<String, String>> getAllChild(String ruleStr) {
		
		try {
			RuleParse parse = new RuleParse(ruleStr);
			parse.setDevp(true);
			
			FindAllChild fc = new FindAllChild();
			List<Map<String, String>> flist = fc.findAllChild(parse);
			return flist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private String printLog(List<RuleLogDtl> logList) {
		String print = "";
		for(RuleLogDtl log: logList) {
			String msg = "";
			int depth = log.getDepth();
			while(depth>0) {
				msg = msg +"    ";
				depth--;
			}
			msg = msg + log.getRuleName() +"  (" + log.getMatch()+"="+log.getObjIf() + ")  ("+log.getContent()+"="+log.getObjThen()+")";
			
//			System.out.println(msg);
			print = print + msg + "\r\n";
		}
		return print;
	}
}
