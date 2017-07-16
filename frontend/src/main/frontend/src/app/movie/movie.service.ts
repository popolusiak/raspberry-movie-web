import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Movie } from './movie';
import { Page } from './page/page'
import 'rxjs/add/operator/map'

@Injectable()
export class MovieService {

    urlPath = "api/movies";
    headers = new Headers({'Content-Type': 'application/json'});
    
    constructor(private http: Http){}
    
    findById(id:number): Observable<Movie> {
        const url = `${this.urlPath}/${id}`;
        return this.http.get(url).map(response => response.json().data as Movie);
    }

    getAll(): Observable<Page<Movie>> {
        return this.http.get(this.urlPath).map(response => new Page<Movie>(response.json(), "movies"));
    }

    nextPage(page: Page<Movie>): Observable<Page<Movie>> {
        return this.http.get(page.nextPageLink())
            .map(response => new Page<Movie>(response.json(), "movies"));
    }

    getPage(pageNumber:number, page: Page<Movie>): Observable<Page<Movie>> {
        return this.http.get(page.pageLink(pageNumber))
            .map(response => new Page<Movie>(response.json(), "movies"));
    }

    getWithCategories(){
        const url = `${this.urlPath}?projection=withCategories&sort=id,asc&page=0&size=1`;
        return this.http.get(url).map(response => new Page(response.json(), "movies"));
    }

}