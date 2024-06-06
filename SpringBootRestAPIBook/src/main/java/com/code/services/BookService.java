package com.code.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code.dao.BookRepository;
import com.code.entities.Book;

@Component
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	/*private static List<Book> list = new ArrayList<>();
	
	static {
		list.add(new Book(12,"Java Complete Reference","xyz"));
	    list.add(new Book(36,"Head First To Java","abc"));
		list.add(new Book(123,"Think In Java","lmn"));
		
	}*/
	
	//get all books
	public List<Book> getAllBooks(){
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list;
		//return list;
	}
	
	// get single book by id
	public Book getBookById(int id) {
		Book book=null;
		try {
			book=this.bookRepository.findById(id);
	//	book=list.stream().filter(e-> e.getId()==id ).findFirst().get();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	// adding the book
	public Book addbook(Book b) {
		Book result = bookRepository.save(b);
		return result;
		//list.add(b);
		//return b;
		}
	// delete book
	public void deleteBook(int bid) {
		bookRepository.deleteById(bid);
	//list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());	
	}

	// update book
	public void updateBook(Book book, int bookId) {
		
		book.setId(bookId);
		bookRepository.save(book);         // save is used for update also
		
	/*list = list.stream().map(b-> {
		if (b.getId()==bookId) {
		b.setTitle(book.getTitle());
		b.setAuthor(book.getAuthor());
		}
		return b;
	}).collect(Collectors.toList());*/
		
	}
	
	

}
