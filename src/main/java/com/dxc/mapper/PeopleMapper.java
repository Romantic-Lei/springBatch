package com.dxc.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.dxc.domain.People;


public class PeopleMapper implements FieldSetMapper<People> {

	@Override
	public People mapFieldSet(FieldSet fieldSet) throws BindException {
		return new People(fieldSet.readInt("id")
                , fieldSet.readString("name")
                ,fieldSet.readInt("age")
                , fieldSet.readString("nation")
                , fieldSet.readString("address")
                );
		
		// 。两种方式均可
//		return new People(fieldSet.readInt(0)
//                , fieldSet.readString(1)
//                ,fieldSet.readInt(2)
//                , fieldSet.readString(3)
//                , fieldSet.readString(4)
//                );
	}

}
