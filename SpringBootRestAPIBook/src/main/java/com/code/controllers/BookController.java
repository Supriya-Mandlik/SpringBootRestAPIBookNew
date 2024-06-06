package com.code.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.entities.Book;
import com.code.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	// Getting All Books handler
	 /*  @GetMapping("/books")
	    public List<Book> getBooks() {
		   
	        return this.bookservice.getAllBooks();
	    }*/
	   
	// ResponseEntity
	// Handling HTTP Status Code
	   @GetMapping("/books")
	    public ResponseEntity<List<Book>> getBooks() {
		   
		  List<Book> list = bookservice.getAllBooks();
		  if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		  }
		   
	        return ResponseEntity.status(HttpStatus.CREATED).body(list);
	    }
	   
	   // Getting single Book handler
	 /*  @GetMapping("/books/{id}")
	   public Book getBook(@PathVariable("id") int id) {
		  return bookservice.getBookById(id); 
	   }*/
	   
	    // ResponseEntity
		// Handling HTTP Status Code
	   @GetMapping("/books/{id}")
	   public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		   Book book = bookservice.getBookById(id);
		   if (book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		  return ResponseEntity.of(Optional.of(book)); 
	   }
	   
	   // add new Book handler
	  /* @PostMapping("/books")
	   public Book addBook(@RequestBody Book book) { // @RequestBody convert JSON data into Book Object
		  Book b = this.bookservice.addbook(book);
		  System.out.println(b);
		   return b;
	   }*/
	   
	   @PostMapping("/books")
	   public ResponseEntity<Book> addBook(@RequestBody Book book) { 
		  Book b = null;
		  try {
			 b= this.bookservice.addbook(book);
			  System.out.println(b);
			  return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		 
	   }
	   
	   // delete book handler
	  /* @DeleteMapping("/books/{bookId}")
	   public void deleteBook(@PathVariable("bookId") int bookId) {
		   this.bookservice.deleteBook(bookId);
	   }*/
	   
	   @DeleteMapping("/books/{bookId}")
	   public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		   try {
			   this.bookservice.deleteBook(bookId);
			   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		   
	   }
	   
	   // update book handler
	  /* @PutMapping("/books/{bookId}")
	   public Book updateBook(@RequestBody Book book , @PathVariable("bookId") int bookId) {
		  this.bookservice.updateBook(book,bookId); 
		  return book;
	   }*/
	   
	   @PutMapping("/books/{bookId}")
	   public ResponseEntity<Book> updateBook(@RequestBody Book book , @PathVariable("bookId") int bookId) {
		 try {
			 this.bookservice.updateBook(book,bookId);
			 return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();		
					}
		   
	   }


}
