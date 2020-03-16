package com.ciana.rule.engine.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciana.rule.engine.log.RuleLog;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.Data;
import com.ciana.rule.engine.parse.entity.Param;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 规则配置解析，结果与json一致
 * 提供各种查询方式返回需要的内容
 * @author yinbo
 * @date: 2019/06/16 14:03
 */
public class RuleParse {
	
	private String id;
	private String name;
	private String code;
	private String descript;
	private String ruletype;
	private String ruleJson;
	/**
	 * 存储连接关系
	 */
	private List<RuleLink> linkList = new ArrayList<RuleLink>();
	/**
	 * 存储规则节点
	 */
	private List<RuleNode> nodeList = new ArrayList<RuleNode>();
	
	//以下是扩展
	private String startId = null;
	private Map<String, RuleNode> nodeMap = new HashMap<String, RuleNode>();
	private Map<String, List<RuleLink>> linkMap = new HashMap<String, List<RuleLink>>();
//	private Map<String, List<RuleLink>> parentLinkMap = new HashMap<String, List<RuleLink>>();//父节点，只处理只有一个父节点的情况
	private Map<String, String> parentLinkMap = new HashMap<String, String>();
	private RuleLog log;
	private Boolean isDevp = false;
	private Integer depth = 1; //默认为1，当为子规则时，需要继承上一级别

	public RuleParse(String json) {
		this.ruleJson = json;
		this.parse(json);
	}

	private void parse(String jsonStr) {
		//解析基本信息
		JSONObject json = JSON.parseObject(jsonStr);
		String attr = json.getString("attr");
		JSONObject dtl = JSON.parseObject(attr);
		this.id = dtl.getString("id");
		this.name = dtl.getString("name");
		this.ruletype = dtl.getString("ruletype");
		this.descript = dtl.getString("descript");
		this.code = dtl.getString("code");
		
		//解析link
        JSONArray linkArray = json.getJSONArray("linkList");
        for (int i = 0; i < linkArray.size(); i++) {  
            JSONObject link = linkArray.getJSONObject(i);  
            
            RuleLink ruleLink = new RuleLink();
            ruleLink.setId(link.getString("id"));
            ruleLink.setType(link.getString("type"));
            ruleLink.setSourceId(link.getString("sourceId"));
            ruleLink.setTargetId(link.getString("targetId"));
            ruleLink.setRelations(link.getString("relations"));
            ruleLink.setPriority(link.getInteger("priority"));
            ruleLink.setExecs("false");

            linkList.add(ruleLink);
            
            //节点快速映射
            if(!linkMap.containsKey(link.getString("sourceId"))) {
            	linkMap.put(link.getString("sourceId"), new ArrayList<RuleLink>());
            }
            
            linkMap.get(link.getString("sourceId")).add(ruleLink);
            
            //父节点快速映射
            parentLinkMap.put(link.getString("targetId"), link.getString("sourceId"));
        }
		
		//解析node
		JSONArray node_array = json.getJSONArray("nodeList");
        for (int i = 0; i < node_array.size(); i++) {  
            JSONObject node = node_array.getJSONObject(i);  
            
            RuleNode ruleNode = new RuleNode();
            ruleNode.setId(node.getString("id"));
            ruleNode.setName(node.getString("nodeName"));
            ruleNode.setRuleGroupId(this.id);
            ruleNode.setRuleGroupName(this.name);
            ruleNode.setDetail(node.getString("detail"));
            ruleNode.setType(node.getString("type"));
            ruleNode.setModel(node.getInteger("model"));
            ruleNode.setMatchCondition(node.getString("match_condition"));
            ruleNode.setRuleContent(node.getString("rule_content"));
            ruleNode.setAction(node.getInteger("action"));
            ruleNode.setLevel(node.getInteger("level"));
            ruleNode.setHasSpecialRule(node.getInteger("has_special_rule"));
            ruleNode.setStatus(node.getInteger("status"));
            ruleNode.setCardNodeType(node.getString("card_node_type"));
            
            if("start".equals(node.getString("type"))) {
            	if(this.startId != null) {
            		//有两个开始节点
            	}
            	this.startId = node.getString("id");
            	ruleNode.setRuleContent(node.getString("total_score"));//将评分卡的最大分值存入动作节点
            	//解析输入参数
            	JSONArray paramsNode = node.getJSONArray("params");
            	if(paramsNode!=null) {
            		for(int k=0; k<paramsNode.size() ;k++) {
                        JSONObject p_node = paramsNode.getJSONObject(k); 
                        
            			Param param = new Param();
            			param.setColum(p_node.getString("colum"));
            			param.setColname(p_node.getString("colname"));
            			param.setColtype(p_node.getString("coltype"));
            			param.setDefvaule(p_node.getString("defvaule"));
            			param.setKey(p_node.getString("key"));
            			
            			ruleNode.addParam(param);
                	}
            	}
            	
            }else if("data".equals(node.getString("type"))) {
            	ruleNode.setDatatype(node.getString("datatype"));
            	ruleNode.setDataSourceName(node.getString("datasource"));
            	ruleNode.setSqlStr(node.getString("sql"));
            	ruleNode.setResturl(node.getString("resturl"));
            	ruleNode.setCachekey(node.getString("cachekey"));
            	ruleNode.setIntfname(node.getString("intfname"));
            	
            	//解析输入参数
            	JSONArray datasNode = node.getJSONArray("datas");
            	if(datasNode!=null) {
            		for(int k=0; k<datasNode.size() ;k++) {
                        JSONObject p_node = datasNode.getJSONObject(k); 
                        
            			Data data = new Data();
            			data.setColum(p_node.getString("colum"));
            			data.setColname(p_node.getString("colname"));
            			data.setColtype(p_node.getString("coltype"));
            			data.setDefvaule(p_node.getString("defvaule"));
            			data.setKey(p_node.getString("key"));
            			
            			ruleNode.addData(data);
                	}
            	}
            }else if("block".equals(node.getString("type"))) {
            	ruleNode.setIntfname(node.getString("classname"));
            }else if("child".equals(node.getString("type"))) {
            	ruleNode.setChildCode(node.getString("childcode"));
            	ruleNode.setChildName(node.getString("childname"));
            	ruleNode.setVersion(node.getString("version"));
            }

            ruleNode.setDevp(isDevp);
            
            nodeList.add(ruleNode);
            
            nodeMap.put(node.getString("id"), ruleNode);
        }
	}
	

	public RuleNode getRuleNode(String id) {
		return this.nodeMap.get(id);
	}
	public RuleNode getParentNode(String id) {
		String parentId = this.parentLinkMap.get(id);
		return getRuleNode(parentId);
	}
	public List<RuleLink> getRuleLinkList(String id){
		return this.linkMap.get(id);
	}
	
	////////////////
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}

	public String getDescript() {
		return descript;
	}

	public String getRuleJson() {
		return ruleJson;
	}
	
	public String getStartId() {
		return startId;
	}
	
	public String getRuletype() {
		return ruletype;
	}
	
	public void setRuleLog(RuleLog log) {
		this.log = log;
	}
	
	public void setDevp(Boolean isDevp) {
		this.isDevp = isDevp;
	}
	
	public Boolean isDevp() {
		return isDevp;
	}
	
	public RuleLogDtl newRuleLogDtl() {
		RuleLogDtl dLog = new RuleLogDtl();
		dLog.setRuleLogId(log.getId());
		dLog.setRuleGroupId(log.getRuleGroupId());
		dLog.setRuleGroupName(log.getRuleGroupName());
		dLog.setVersion(log.getVersion());
		
		return dLog;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
}
