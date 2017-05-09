import { Component, Pipe, PipeTransform, Input, Output, EventEmitter } from '@angular/core';
import { Page } from './page';


@Component({
    selector: 'pg-page',
    templateUrl: 'page.component.html',
    
})
export class PageComponent {
    @Input()page: Page<any>;
    @Output() pageClicked = new EventEmitter()


    buttonPageClicked(page: number):void {
        this.pageClicked.emit(page);
    }
}

@Pipe({name: 'iterate'})
export class Iterate implements PipeTransform {
  transform(value, args:string[]) : any {
    let res = [];

    value = value + 1;
    for (let i = 1; i < value; i++) {
        res.push(i);
      }
      return res;
  }
}


