package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookPostgreDAO;

@Service
public class BookService {

	private final BookPostgreDAO bookPostgreDAO;

	@Autowired
	public BookService(BookPostgreDAO bookPostgreDAO) {
		this.bookPostgreDAO = bookPostgreDAO;
	}

	public Book createBook(String title, String header, String author, String overview,
						   String status, int photoId, int fileId) {
		Book book = new Book(title, header, author, overview, status, photoId, fileId);
		bookPostgreDAO.createBook(book);

		return book;
	}

	public Book updateBook(int bookId, String title, String header, String author,
						   String overview, String status, int photoId, int fileId){
		Book book = bookPostgreDAO.getBookById(bookId);

		if (book != null) {
			book.setTitle(title);
			book.setHeader(header);
			book.setAuthor(author);
			book.setOverview(overview);
			book.setStatus(status);
			book.setPhotoId(photoId);
			book.setFileId(fileId);
			bookPostgreDAO.updateBook(book, bookId);
			return book;
		} else {
			return null;
		}
	}
}
