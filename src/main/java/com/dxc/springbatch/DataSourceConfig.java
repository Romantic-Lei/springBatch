package com.dxc.springbatch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.context.annotation.Configuration;

// 。必须要存在，否则程序报程序框架不安全警告，程序无法正常完整执行
// @Configuration用于定义配置类，可替换xml配置文件
@Configuration
public class DataSourceConfig extends DefaultBatchConfigurer {
	
	public void setDataSource(DataSource dataSource) {
		
	}

}
