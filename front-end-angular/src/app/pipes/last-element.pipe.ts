import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'lastElement'
})

  export class LastElementPipe implements PipeTransform {
    transform(input: any): any {
 
      if (input.constructor !== Array) {
        return input;
      }
      return input[input.length - 1]
    }
  }
