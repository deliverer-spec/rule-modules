package com.ciana.rule.engine.function;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.data.DataUtil;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.Data;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class DataExecute implements ExecInterface{

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> last_params, Boolean isTest) throws Exception {
		// TODO 数据源处理方式   直连模式 / rest方式 /cache方式 /intf接口
		Map<String,Object> tmpdata = DataUtil.getInstanceClass(node.getDatatype()).getData(node, last_params);
		//转换来源
		List<Data> datas = node.getDatas();
		for(Data data: datas) {
			String colum = data.getColum();
			if(tmpdata.containsKey(colum)) {
				//TODO 没有校验类型，没有做数据格式化
				last_params.put(colum, tmpdata.get(colum));
			}else {
				//TODO 没有确定默认值的使用方式
				throw new Exception("没有找到字段"+colum+" ,"+data.getColname());
			}
		}
		return last_params;
	}

	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		//数据节点的条件部分暂无用处
		return true;
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		//数据节点的条件部分暂无用处	
		return true;
	}

}
