package com.dxc.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.dxc.domain.Book;

public class BookMapper implements FieldSetMapper<Book> {

	@Override
	public Book mapFieldSet(FieldSet fieldSet) throws BindException {
		return new Book(fieldSet.readInt(0)
              , fieldSet.readString(1)
              ,fieldSet.readString(2)
              , fieldSet.readInt(3)
              , fieldSet.readDouble(4)
              );
	}

}
