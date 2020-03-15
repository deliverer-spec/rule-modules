package com.ciana.rule.engine.data.intf;

import java.sql.Connection;
/**
 * 通过配置的DataSource 创建连接
 * 调用rule-engine时需要实现其接口
 * 需要在engine.properties中新增 rule.engine.connimpl参数
 * TODO 这里只一次创建使用，暂不考虑连接池问题
 * @author admin
 *
 */
public interface ConnInterface {

	/**
	 * 根据数据源名称返回连接
	 * 这个连接使用后将被释放
	 * @param dataSourcename
	 * @return
	 */
	public Connection getconn(String dataSourcename) ;
	
}
