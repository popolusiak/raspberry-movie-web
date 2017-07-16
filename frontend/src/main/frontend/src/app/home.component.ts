import { Component, OnInit } from '@angular/core';
import { MovieService } from './movie/movie.service'
import { Observable } from 'rxjs/Observable';
import { Movie } from './movie/movie';
import { Page } from './movie/page/page';
import { PageComponent } from './movie/page/page.component';
import { MovieDetail } from './movie/movie-detail';


@Component({    
    templateUrl:'./home.component.html'
})
export class HomeComponent implements OnInit  {
    page: Observable<Page<Movie>>
    pagginationVisible = true;    

    constructor(private movieService: MovieService){}

    navigateToPage(pageNumber: number){
        this.pagginationVisible = false;
        this.page.subscribe(p => {
            this.page = this.movieService.getPage(pageNumber-1, p)
            this.pagginationVisible=true;
        })
        
    }

    ngOnInit():void {        
        this.page = this.movieService.getWithCategories();
    }
}