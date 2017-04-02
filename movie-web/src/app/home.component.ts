import { Component, OnInit } from '@angular/core';
import { MovieService } from './movie/movie.service'
import { Observable } from 'rxjs/Observable';
import { Movie } from './movie/movie';
import { MovieDetail } from './movie/movie-detail';


@Component({    
    templateUrl:'./home.component.html',
    styles:[`
    :host >>> md-card.mat-card-image{
        width: 90px;
        height: 180px;
    }
    `]
})
export class HomeComponent implements OnInit  {
    movies: Observable<Movie[]>

    constructor(private movieService: MovieService){}

    ngOnInit():void {        
        this.movies = this.movieService.getAll();
    }
}