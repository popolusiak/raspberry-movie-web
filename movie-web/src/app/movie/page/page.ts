
export class Page<T> {
    public items: T[];
    private size: number;
    private totalElements: number;
    private totalPages: number;
    private number: number;

    constructor(json: any, listName:string){        
        this.items = json.data[0]._embedded[listName] as T[];           
    }



}


