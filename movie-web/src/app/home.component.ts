import { Component, OnInit } from '@angular/core';
import { MovieService } from './movie/movie.service'
import { Observable } from 'rxjs/Observable';
import { Movie } from './movie/movie';
import { Page } from './movie/page/page';
import { MovieDetail } from './movie/movie-detail';


@Component({    
    templateUrl:'./home.component.html'
})
export class HomeComponent implements OnInit  {
    page: Observable<Page<Movie>>

    constructor(private movieService: MovieService){}

    ngOnInit():void {        
        this.page = this.movieService.getAll();
    }
}