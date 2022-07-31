import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mapField'
})
export class MapFieldPipe implements PipeTransform {

  transform(objArray:any) {
     let result = Object.assign({}, ...objArray.map((a: { score: any; }) => a.score));
    return result;
  }

}
