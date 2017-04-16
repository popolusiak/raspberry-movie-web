import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { MovieCardComponent } from './movie-card.component';
import { Movie } from './movie';
import { MovieDetail } from './movie-detail';
import { MovieService } from './movie.service';
import { MoviePlayerComponent } from './movie-player/movie-player.component'
import { MoviePlayerService } from './movie-player/movie-player.service'



@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        HttpModule
    ],
    declarations: [MovieCardComponent, MoviePlayerComponent],
    exports:  [MovieCardComponent, MoviePlayerComponent],
    providers: [MovieService, MoviePlayerService]
})
export class MovieModule {

}