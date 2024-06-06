package com.code.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.code.entities.Book;


public interface BookRepository extends CrudRepository<Book,Integer>{
	
	public Book findById(int id);
	

}
