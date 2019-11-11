import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import { Observable,of,throwError  } from 'rxjs';
import { Book } from 'src/app/models/book';
import { Announcement } from 'src/app/models/announcement';
import { Review } from 'src/app/models/review';
import { /*Filter,*/BookFilter } from 'src/app/models/bookfilter';

import { catchError, map, tap, retry} from 'rxjs/operators';
import set = Reflect.set;

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private booksUrl = 'http://localhost:8080/book'; // change with necessity
  private announcementsUrl = '/announcements'; // change with necessity

  // private reviewsUrl = 'api/books'; // -?

  // books : Book[] = [];
  // announcements : Announcement[] = [];
  // reviews : Review[] = [];

  constructor(private http: HttpClient) {
  }
  httpOptions = {headers:new HttpHeaders().set('Content-Type', 'application/json')
      .set('Accept', 'application/json').set('Access-Control-Allow-Origin','*')};


  getBooks(): Observable<Book[]> {
    const url = `${this.booksUrl}\\all`;
    return this.http.get<Book[]>(url);
    //return null;
  }

  getBooksByFilter(filter: BookFilter): Observable<Book[]> { // + filter ?
    const url = `${this.booksUrl}/${filter.author}/${filter.genre}`;
    return this.http.get<Book[]>(url);
  }

  getBookById(id:number){
    const url = `${this.booksUrl}/?id=${id}`;
    return this.http.get<Book>(url);
  }

  createBook(book:Book):Observable<Book>{
    console.log(book);
    return this.http.post<Book>(this.booksUrl, book, this.httpOptions);
  }

  deleteBook(id:number):Observable<Book>{
    const url = `${this.booksUrl}/?id=${id}`;
    return this.http.delete<Book>(url);
  }

  updateBook(book:Book){
    const url = `${this.booksUrl}\\update\\?id=${book.id}`;
    this.http.post(this.booksUrl, book, this.httpOptions);
  }
  getAnnouncements(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(this.announcementsUrl);
  }
  getAnnouncementsByFilter() : Observable<Announcement[]>{
  }

  getAllAuthor():Observable<string[]>{
    const url = `${this.booksUrl}/author`;
    //return this.http.get<string[]>(url);
    return null;
  }
  getAllGenre():Observable<string[]>{
    const url = `${this.booksUrl}/genre`;
    //return this.http.get<string[]>(url);
    return null;
  }
  getReviews(id:number) : Observable<Review[]>{
    const url = `${this.booksUrl}/${id}`;
   // return this.http.get<Review[]>(url);
    return null;
  }

  recoverPassword() {

  }

  changePassword() {

  }

  manageNotifications() {

  }

  login() {

  }
}
