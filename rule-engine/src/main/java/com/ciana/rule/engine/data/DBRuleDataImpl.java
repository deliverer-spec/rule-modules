package com.ciana.rule.engine.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 数据查询
 * @author admin
 *
 */
public class DBRuleDataImpl implements RuleDataInterface {

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> params) throws Exception {
		//获取连接
		Connection conn = null;
		//根据数据源名称查询数据库中配置的数据库链接
		conn = SqlUtil.getconn(node);
		//处理参数 
		//select id from user where id=#params#
		String sql = node.getSqlStr();
		//解析s'q
		Map<String, Object> result  = SqlUtil.getAnalysisSql(sql);
		sql = result.get("sql")+"";
		@SuppressWarnings("unchecked")
		List<String> paramName = (List<String>)result.get("param");
		List<Object> param_ = new ArrayList<Object>();
		if(paramName!=null) {
			for(int i =0; i<paramName.size(); i++) {
				String p = paramName.get(i);
				if(!params.containsKey(p)) {
					throw new Exception("没有找到参数"+p);
				}
				param_.add(params.get(p));
			}
		}
		//查询
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		try{
			pst = conn.prepareStatement(sql);
			for(int i =0; i<param_.size(); i++) {
				pst.setObject(i+1, param_.get(i));
			}
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next()) {
			    map = new HashMap<String,Object>();
				for(int j=1; j<=rsmd.getColumnCount(); j++) {
					map.put(rsmd.getColumnName(j).toLowerCase(), rs.getObject(j));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally{
			close(conn, pst,rs);
		}
		
		//查询结果 只能一条记录
		return map;
	}
	
	private void close(Connection conn, Statement st, ResultSet rs) throws Exception {
		try{
			if(rs!=null) rs.close();
		}catch (Exception e) {
//			throw new Exception(e);
		}
		try{
			if(st!=null) st.close();
		}catch (Exception e) {
//			throw new Exception(e);
		}
		try{
			if(conn!=null) conn.close();
		}catch (Exception e) {
//			throw new Exception(e);
		}
	}
	
}
