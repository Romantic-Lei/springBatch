package com.dxc.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dxc.Listener.Listener;
import com.dxc.domain.Book;
import com.dxc.domain.People;


@Configuration
public class DBConfiguration {

	@Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("outputDBItemReader")
    private ItemReader<? extends People> outputDBItemReader;
    @Autowired
    @Qualifier("reader2")
    private ItemReader<? extends Book> reader2;
    @Autowired
    @Qualifier("outputDBItemWriter")
    private ItemWriter<? super People> outputDBItemWriter;
    @Autowired
    @Qualifier("writer2")
    private ItemWriter<? super Book> writer2;

    @Autowired
    private ItemProcessor<? super People, ? extends People> myProcess;
    @Autowired
    private ItemProcessor<? super Book, ? extends Book> myProcess2;
    
    @Bean
    public Job OutputItemWriterDBJob2() {
    	System.out.println("执行springbatch的Job，在Job中调用执行Step");
        return jobBuilderFactory.get("OutputItemWriterDBJob2")
        		.start(OutputItemWriterDBStep2())
        		// 需要使用next(step)方法来操作下一步该执行的step，如果使用start()方法，则只会执行最后的一个start()方法的step
        		.next(step1())
//        		.start(step1())
        		.listener(listener())
        		.build();

    }

    @Bean
    public Step OutputItemWriterDBStep2() {
    	System.out.println("执行springbatch的Step，它被Step调用启动");
    	// chunk()函数，指定程序一次读取的行数，达到指定行数之后他批量提交到sql语句
        return stepBuilderFactory.get("OutputItemWriterDBStep2").<People, People>chunk(10)
                .reader(outputDBItemReader)
                .processor(myProcess)
                .writer(outputDBItemWriter)
                .faultTolerant()
                // skipLimit 最多可以容错回滚次数，超过这个数，该STEP抛出异常
                .skipLimit(30)
                // 。此处的exception类型必须和 MyProcess 类自定义的异常类型一致
                .skip(Exception.class)
                // 检查到代码抛出某种类型异常之后，我们重复提交的次数，如果在重复提交的次数内依然无法成功，则跳过
                .retryLimit(3)
                // 只能retry代码抛出的异常和我们retry的异常是一致的或者retry的异常为他的父类
                .retry(Exception.class)
                .build();
    }
    
    @Bean
    public Step step1() {
    	// chunk()函数，指定程序一次读取的行数，达到指定行数之后他批量提交到sql语句
    	return stepBuilderFactory.get("step1")
    			.<Book, Book>chunk(2)
    			.reader(reader2)
    			.processor(myProcess2)
    			.writer(writer2)
    			.faultTolerant()
    			// skipLimit 最多可以容错回滚次数，超过这个数，该STEP抛出异常
    			.skipLimit(4)
    			// 。此处的exception类型必须和 MyProcess 类自定义的异常类型一致
    			.skip(Exception.class)
    			.build();
    }
    
    @Bean
    public Listener listener() {
    	return new Listener();
    } 
	
}
