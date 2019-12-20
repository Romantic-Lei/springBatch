package com.dxc.Process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.dxc.domain.People;

@Component
public class MyProcess implements ItemProcessor<People, People> {

	public People process(People item) throws Exception {
		System.out.println("----------------MyProcess----------------");
		System.out.println(item);
		if(item.getAge() > 40 || item.getAge() < 10) {
			throw new Exception("custom exception");
		}
        return item;
	}

}
