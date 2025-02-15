package com.code.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.code.dao.BookRepository;
import com.code.entities.Book;

//Junit Testing

@SpringBootTest                  //class level Annotation
public class BookServiceTest {
	
	@InjectMocks
	BookService bookService;
	//now spring container will provide bean of Bookservice
	
	@Mock   //it it used when in testing we dont want to hit database//it creates proxy objects
	BookRepository repo;
	//we use mock for all the dependencies we autowired in the class which we are going to test.
	
	
	@Test                        //method level Annotation
	public void getBookByIdTest() {
		
		when(repo.findById(1)).thenReturn(createBookStub());  //when we dont want to hit database
		//for return value
		
		//when(repo.findById(1)).thenThrow(); //for exception
		
		Book testedBookById = bookService.getBookById(1);
		
		assertEquals(testedBookById.getTitle(), "Drawing Book");
		
	}
	
	@Test
	public void getAllBooksTest() {
		when(repo.findAll()).thenReturn(Stream
				.of(new Book(1, "English", null),new Book(2, "Hindi", null))
				.collect(Collectors.toList()));
		assertEquals(2, bookService.getAllBooks().size());
		
	}
	
	@Test
	public void addbookTest() {
		Book book = new Book(5, "Physics", null);
		when(repo.save(book)).thenReturn(book);
		assertEquals(book, bookService.addbook(book));
	}
	
/*	@Test
	public void deleteBookTest() {
		Book book = new Book(5, "Physics", null);
		bookService.deleteBook(5);
		verify(repo,times(1)).delete(5);
	}*/
	
/*	@Test
	public void getBookByIdTestWithException() {
		
		when(repo.findById(1)).thenThrow(new Exception());
		Exception exception = assertThrows(Exception.class, ()->bookService.getBookById(1));
		assertEquals(exception.getMessage(), null);
	} */
	
	private Book createBookStub() {
		Book stubBook = new Book(1, "Drawing Book", null);
		return stubBook;
	}

}
