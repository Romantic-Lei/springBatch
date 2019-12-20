package com.dxc.springbatch;


import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.dxc.domain.Book;
import com.dxc.domain.People;
import com.dxc.mapper.BookMapper;
import com.dxc.mapper.PeopleMapper;

@Configuration
public class DBItemReaderConfiguration {

	@Bean
    public FlatFileItemReader<People> outputDBItemReader(){
        FlatFileItemReader<People> reader=new FlatFileItemReader<People>();
        System.out.println("========================================================================");
        
        // 。设置文件读取格式为UTF-8
        reader.setEncoding("UTF-8");
        // 、设置文件读取的位置
        reader.setResource(new ClassPathResource("people.csv"));
//      reader.setResource(new FileSystemResource("C:/person.csv"));
        // 。跳过开头行数,必须设置，不然程序会抛出异常
        reader.setLinesToSkip(1);
        System.out.println("-------------------------------------------------------------------------");

        // lineTokenizer将文件的一行分解成一个 FieldSet，然后由 fieldSetMapper映射成Pojo对象
        DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] 
                {"id","name","age","nation","address"}
        );
        // 。将文件的每一行映射成一个Pojo对象
        DefaultLineMapper<People> lineMapper=new DefaultLineMapper<People>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new PeopleMapper());  // 映射成一个POJO对象
        lineMapper.afterPropertiesSet();
        reader.setLineMapper(lineMapper);
        return reader;
    }
	
	@Bean
	public FlatFileItemReader<Book> reader2(){
		FlatFileItemReader<Book> reader=new FlatFileItemReader<Book>();
		
		// 。设置文件读取格式为UTF-8
		reader.setEncoding("UTF-8");
		// 、设置文件读取的位置
		reader.setResource(new ClassPathResource("book.csv"));
		// 。跳过开头行数,必须设置，不然程序会抛出异常
		reader.setLinesToSkip(1);
		
		// lineTokenizer将文件的一行分解成一个 FieldSet，然后由 fieldSetMapper映射成Pojo对象
		DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] 
				{"id","bookName","author","totalNum","price"}
				);
		// 。将文件的每一行映射成一个Pojo对象
		DefaultLineMapper<Book> lineMapper=new DefaultLineMapper<Book>();
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new BookMapper());  // 映射成一个POJO对象
		lineMapper.afterPropertiesSet();
		reader.setLineMapper(lineMapper);
		return reader;
	}
	
}
