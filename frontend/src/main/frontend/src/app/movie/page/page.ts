
export class Page<T> {
    public items: T[];
    private _size: number;
    private _totalElements: number;
    private _totalPages: number;
    private _number: number;
    private _selfLink: string;
    private _nextLink: string;
    private _lastLink: string;
    private _firstLink: string;

    constructor(json: any, listName:string){        
        let pageData = json;
        this.items = json._embedded[listName] as T[];
        let links = json._links
        this._size=pageData.page.size;
        this._totalElements = pageData.page.totalElements;
        this._number = pageData.page.number; 

        this._selfLink = links.self.href;
        this._firstLink = links.first ? links.first.href : this._selfLink;
        this._lastLink = links.last ? links.last.href : this._selfLink;
        this._nextLink = links.next ? links.next.href : this._selfLink;          
    }

    get size():number {
        return this._size;
    }

    get totalElements():number {
        return this._totalElements;
    }

    get totalPages(): number {
        return this._totalPages;
    }

    get number(): number {
        return this._number;
    }

    get next(): string {
        return this._nextLink;
    }

    get first(): string {
        return this._firstLink;
    }

    get last(): string {
        return this._lastLink;
    }

    nextPageLink(): string{
        return this.pageLink(this.number);
    }

    pageLink(pageNumber: number): string{
        if(pageNumber  <= this._totalPages){
            return this._firstLink.replace("page=0",`page=${pageNumber}`)
        }else{
            return this._lastLink;
        }
    }


}


