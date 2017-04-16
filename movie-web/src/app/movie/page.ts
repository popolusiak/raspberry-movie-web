
export class Page<T> {
    private items: T[];
    public size: number;
    public totalElements: number;
    public totalPages: number;
    public number: number;

    constructor(json: any, listName:string){
        let embeded = json['_embedded']
        this.items = embeded[listName] as T[]        
    }
}


