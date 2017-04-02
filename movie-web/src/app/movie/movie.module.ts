import { NgModule } from '@angular/core';
import { MaterialModule } from '@angular/material';
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { MovieCardComponent } from './movie-card.component';
import { Movie } from './movie';
import { MovieDetail } from './movie-detail';
import { MovieService } from './movie.service';



@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        HttpModule,
        MaterialModule
    ],
    declarations: [MovieCardComponent],
    exports:  [MovieCardComponent],
    providers: [MovieService]
})
export class MovieModule {

}