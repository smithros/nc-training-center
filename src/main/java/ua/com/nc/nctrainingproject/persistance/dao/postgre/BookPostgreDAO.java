package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.BookDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.util.List;

@Repository
public class BookPostgreDAO implements BookDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookPostgreDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Book getBookByTitle(String title) {
		try {
			return jdbcTemplate.queryForObject(BookQuery.GET_BOOK_BY_TITLE, new Object[]{title}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Book getBookByAuthor(String author) {
		try {
			return jdbcTemplate.queryForObject(BookQuery.GET_BOOK_BY_AUTHOR, new Object[]{author}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Book getBookByStatus(String status) {
		try {
			return jdbcTemplate.queryForObject(BookQuery.GET_BOOK_BY_STATUS, new Object[]{status}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void createBook(Book book) {
		jdbcTemplate.update(BookQuery.CREATE_BOOK,
				book.getTitle(),
				book.getHeader(),
				book.getAuthor(),
				book.getOverview(),
				book.getPhotoId(),
				book.getFileId(),
				book.getStatus());
	}

	@Override
	public void updateBook(int book_id, String title, String header,
						   String author, String overview,
						   String status, int photoId, int fileId) {
		jdbcTemplate.update(BookQuery.UPDATE_BOOK, title, header, author, overview, status, photoId, fileId, book_id);
	}


	@Override
	public List<Book> getAllBooks() {
		return jdbcTemplate.query(BookQuery.GET_ALL_BOOKS, new BookRowMapper());
	}
}