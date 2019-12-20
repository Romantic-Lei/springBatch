package com.dxc.springbatch;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dxc.domain.Book;
import com.dxc.domain.People;

@Configuration
public class DBItemWriterConfiguration {

	@Autowired
    private DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<People> outputDBItemWriter() {
        System.out.println("准备向 数据库 中插入数据");
        // 。将数据写入到数据库
        JdbcBatchItemWriter<People> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql(
                "insert into person"
                + "(id,name,age,nation,address) values"
                + "(:id,:name,:age,:nation,:address)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<People>());
        return writer;
    }
    
    @Bean
    public JdbcBatchItemWriter<Book> writer2() {
    	System.out.println("准备向 数据库 中插入数据");
    	// 。将数据写入到数据库
    	JdbcBatchItemWriter<Book> writer = new JdbcBatchItemWriter<>();
    	writer.setDataSource(dataSource);
    	writer.setSql(
    			"insert into book"
    					+ "(id,bookName,author,totalNum,price) values"
    					+ "(:id,:bookName,:author,:totalNum,:price)");
    	writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Book>());
    	return writer;
    }
	
}
