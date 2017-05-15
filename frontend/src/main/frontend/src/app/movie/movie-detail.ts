import { Title } from './title';
import { Interpret } from '../interpret/interpret'; 
import { Movie } from './movie';

export class MovieDetail {
    movie: Movie;
    actors: Interpret[];
    directedBy: Interpret[];
    scenario: Interpret[];
    camera: Interpret[];
    music: Interpret[];
    description: string;
}