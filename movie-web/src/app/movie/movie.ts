import { Title } from './title';
import { Genre } from './genre/genre';

export class Movie {    
    id: number;
    titles: Title[];
    genres: Genre[];
    description: string;
}