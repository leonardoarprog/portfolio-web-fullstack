import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'firstElement'
})


export class FirstElementPipe implements PipeTransform {
  transform(input: any): any {
    let filtrado:any[]=[];
    if (input.constructor !== Array) {
      return input;
    }
    filtrado.push(input[0]);
    return filtrado;
  }
}