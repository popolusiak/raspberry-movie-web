import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { Movie } from '../movie'

@Injectable()
export class MoviePlayerService {

    urlPath = "/api/movies";
    headers = new Headers({ "Content-Type": "application/json" })

    constructor(
        private http: Http
    ) { }

    findNowPlaying(): Observable<Movie> {
        const url = `${this.urlPath}/nowPlaying`
        return this.http.get(url).map(response => response.json().map as Movie)
    }

    play(): void {
        const url = `${this.urlPath}/play`
        this.http.get(url);
    }

    pause(): void {
        const url = `${this.urlPath}/pause`
        this.http.get(url);
    }

    stop(): void {
        const url = `${this.urlPath}/stop`
        this.http.get(url);
    }

    fastForward(): void {
        const url = `${this.urlPath}/fastForward`
        this.http.get(url);
    }

    fastRewind(): void {
        const url = `${this.urlPath}/fastForward`
        this.http.get(url);
    }
}