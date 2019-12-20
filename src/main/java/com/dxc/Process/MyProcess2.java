package com.dxc.Process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.dxc.domain.Book;

@Component
public class MyProcess2 implements ItemProcessor<Book, Book> {

	public Book process(Book item) throws Exception {
		System.out.println("----------------MyProcess2----------------");
		System.out.println(item);
        return item;
	}

}
