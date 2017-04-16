import { Component, Input, OnInit } from '@angular/core';
import { Movie } from './movie';

@Component({
    selector: 'movie-card',
    templateUrl: 'movie-card.component.html',
    styleUrls: ['./movie-card.component.css']
})
export class MovieCardComponent implements OnInit{
    @Input() movie: Movie
    title: string;

    ngOnInit():void {
        this.title = this.movie.titles[0].title;
    }
} 