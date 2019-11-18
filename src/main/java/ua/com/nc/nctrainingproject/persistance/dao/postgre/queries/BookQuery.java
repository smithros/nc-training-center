package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class BookQuery {

  public static final String TABLE_NAME = "books";
  public static final String BOOK_ID = "book_id";
  public static final String TITLE = "name";
  public static final String HEADER = "header";
  public static final String AUTHOR = "author_name";
  public static final String OVERVIEW = "overview";
  public static final String PHOTO = "photo_id";
  public static final String FILE = "file";
  public static final String STATUS = "status";
  public static final String GENRE_ID = "genre_id";

  public static final String GENRE = "genre_name";
  public static final String GENRES_TABLE = "genres";


  public static final String GET_ALL ="SELECT *" + " FROM " + TABLE_NAME;

  public static final String GET_BOOK = GET_ALL+" WHERE "+TABLE_NAME+"."+BOOK_ID+" =(?)";

  public static final String GET_BOOK_BY_ID = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + BOOK_ID + " =(?)";

  public static final String GET_BOOK_BY_TITLE = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + HEADER + " =(?)";


  public static final String GET_BOOK_BY_STATUS = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + STATUS + " =(?)";

  public static final String CREATE_BOOK = "INSERT INTO " + TABLE_NAME
    + " (" + HEADER + ","
    + OVERVIEW + "," + FILE + "," + STATUS  + "," + PHOTO + ")" + " VALUES(?,?,?,?,?)";

  public static final String GET_ALL_BOOKS = "SELECT * FROM " + TABLE_NAME;

  public static final String UPDATE_BOOK = "UPDATE " + TABLE_NAME + " SET " +
    HEADER + "=(?), " +
    OVERVIEW + "=(?), " + FILE + "=(?), " + STATUS + "=(?)," + PHOTO + "=(?) " +
    " WHERE " + BOOK_ID + "=(?)";

  public static final String DELETE_BOOK_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + BOOK_ID + " =(?)";


  public static final String GET_BOOKS_BY_TITLE = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + HEADER + " =(?)";


  public static final String GET_BOOKS_BY_STATUS = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + STATUS + " =(?)";


  public static final String GET_BOOKS_FILTRATION=
    "select "+ TABLE_NAME + "."+BOOK_ID +" , "+ HEADER+ ", " + AUTHOR+ ", " + OVERVIEW+ " ," + STATUS+ " ," + PHOTO+ ", " + FILE+ ", " + GENRE + " " +
      " from " + TABLE_NAME +" join "+GENRES_TABLE +" ON " + TABLE_NAME + "." + GENRE_ID + " = " + GENRES_TABLE + "." + GENRE_ID+
     " join "+ AuthorBookQuery.TABLE_NAME  + " on "+ TABLE_NAME + "." + BOOK_ID +" = " +AuthorBookQuery.TABLE_NAME+ "." + AuthorBookQuery.BOOK_ID+
      " join "+AuthorQuery.TABLE_NAME +" on " +AuthorBookQuery.TABLE_NAME+ "." + AuthorBookQuery.AUTHOR_ID+" = " + AuthorQuery.TABLE_NAME+ "." +  AuthorQuery.ID+" where ";

  /*select header,author,overview,status,photo_id,file,genre_name from books join genres on books.genre_id = genres.genre_id join book_author ba on books.book_id = ba.book_id
join authors on ba.author_id = authors.id

   */
  public static final String CONDITIONS_GENRES = GENRE + "=(?)";
  public static final String CONDITIONS_NAME =HEADER +" LIKE "+"(?) ";
  public static final String CONDITION_AUTHOR = AUTHOR+" =(?)";
//	public static final String CREATE_BOOK = "INSERT INTO " + TABLE_NAME
//			+ " (" + TITLE + "," + HEADER + "," + AUTHOR + ","
//			+ OVERVIEW + "," + PHOTO + "," + FILE +  "," + STATUS + "," + GENRE
//			+ ")" + " VALUES(?,?,?,?,?,?,?,?)";
//
//	public static final String GET_ALL_BOOKS = "SELECT * FROM " + TABLE_NAME;
//
//	public static final String UPDATE_BOOK = "UPDATE " + TABLE_NAME + " SET " +
//			TITLE + "=(?), " + HEADER + "=(?), " + AUTHOR + "=(?), " +
//			OVERVIEW + "=(?), " + PHOTO + "=(?), " + FILE + "=(?), " +
//			STATUS + "=(?), " + GENRE + "=(?)" + " WHERE " + BOOK_ID + "=(?)";
//
// /*public static final String JOIN_BOOKS_ANNOUNCEMENT =
//    "select " +TITLE+HEADER+AUTHOR+OVERVIEW+PHOTO+FILE+STATUS +
//      "from "+TABLE_NAME +" join"+ TABLE_NAME_ANNOUNCEMENTS +
//    "    on books.book_id = announcements.book_id WHERE ";*/

 // public static final String CONDITIONS_NAME = HEADER +" =(?) ";
//  public static final String CONDITION_ANNOUNCEMENT_DATE ="announcements.date is between"
//    +"=(?)" +" AND "+"=(?)";


}
