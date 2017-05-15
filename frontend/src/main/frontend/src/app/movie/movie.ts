import { Title } from './title';
import { Category } from './category/category';

export class Movie {    
    id: number;
    titles: Title[];
    categories: Category[];
    description: string;
}