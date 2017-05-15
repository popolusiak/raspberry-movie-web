import { Component, OnInit } from '@angular/core'
import { Movie } from '../movie';
import { MoviePlayerService } from './movie-player.service';
import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'movie-player',
    templateUrl: './movie-player.component.html'
})
export class MoviePlayerComponent implements OnInit {
    nowPlaying: Observable<Movie>;

    constructor(
        private moviePlayerService: MoviePlayerService
    ) { }

    play(): void {
        this.moviePlayerService.play();
    }

    pause(): void {
        this.moviePlayerService.pause();
    }

    stop(): void {
        this.moviePlayerService.stop();
    }

    fastForward(): void {
        this.moviePlayerService.fastForward();
    }

    fastRewind(): void {
        this.moviePlayerService.fastRewind();
    }

    ngOnInit():void {
        this.nowPlaying =  this.moviePlayerService.findNowPlaying();
    }

}