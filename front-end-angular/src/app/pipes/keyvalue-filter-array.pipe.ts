import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'keyvalueFilterArray'
})
export class KeyvalueFilterArrayPipe implements PipeTransform {

  transform(obj:{}, filter:string):any[] {
    let filtrado:any[]=[];
    if (filter === 'value') {
      filtrado = Object.values(obj);
    } else if (filter === 'key') {
      filtrado = Object.keys(obj);
    }
    return filtrado;
  }
}
