package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.BookDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookPostgreDAO implements BookDAO {

  private final JdbcTemplate jdbcTemplate;
  private final FilterCriterionQuery filterCriterionQuery;

  @Autowired
  public BookPostgreDAO(JdbcTemplate jdbcTemplate, FilterCriterionQuery filterCriterionQuery) {
    this.jdbcTemplate = jdbcTemplate;
    this.filterCriterionQuery = filterCriterionQuery;
  }

  @Override
  public Book getBookById(int bookId) {
    try {
      return jdbcTemplate.queryForObject(BookQuery.GET_BOOK_BY_ID, new Object[]{bookId}, new BookRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Book> getBooksByTitle(String title) {
    try {
      return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_TITLE, new Object[]{title}, new BookRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Book> getBooksByAuthor(String author) {
    try {
      return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_AUTHOR, new Object[]{author}, new BookRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Book> getBooksByStatus(String status) {
    try {
      return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_STATUS, new Object[]{status}, new BookRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Book> getBooksByGenre(String genre) {
    try {
      return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_GENRE, new Object[]{genre}, new BookRowMapper());
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
      book.getStatus(),
      book.getGenre());
  }

  public void updateBook(int bookId, String title, String header,
                         String author, String overview,
                         String status, int photoId, int fileId, String genre) {
    jdbcTemplate.update(BookQuery.UPDATE_BOOK, title, header, author, overview,
      status, photoId, fileId, genre, bookId);
  }

  @Override
  public void updateBook(Book book, int bookId) {
    jdbcTemplate.update(BookQuery.UPDATE_BOOK, book.getTitle(), book.getHeader(), book.getAuthor(),
      book.getOverview(), book.getPhotoId(), book.getFileId(), book.getStatus(), book.getGenre(), bookId);
  }


  @Override
  public List<Book> getAllBooks()
  {
    return jdbcTemplate.query(BookQuery.GET_ALL_BOOKS, new BookRowMapper());
  }


/*public List<Book> filterBooks
    (FilterCriterionQuery filterCriterionQuery){
	  return jdbcTemplate.query(BookQuery.JOIN_BOOKS_ANNOUNCEMENT + filterCriterionQuery.getConditionsQuery()
      ,new BookRowMapper(),filterCriterionQuery.
        getSqlCriteriaMap().get(BookQuery.CONDITIONS_NAME),
      filterCriterionQuery.getSqlCriteriaMap().get(BookQuery.CONDITIONS_GENRES),
      filterCriterionQuery.getSqlCriteriaMap().get(BookQuery.CONDITIONS_GENRES)
    );
  }

	 */

  public List<Book> filterBooks
    (FilterCriterionQuery filterCriterionQuery) {
    String q = filterCriterionQuery.makeQuery();
   // List<Object> argsList = new ArrayList<Object>();
  //  argsList.addAll(filterCriterionQuery.getGenre());
  //  argsList.addAll(argsList.size()-1,filterCriterionQuery.getAuthor());
   // argsList.add(filterCriterionQuery.getHeader());


    Object[] args =filterCriterionQuery.makeArrayArgs();
    return jdbcTemplate.query(q,
      args, new BookRowMapper());

  }
}

