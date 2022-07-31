import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'arrayToObj'
})
export class ArrayToObjPipe implements PipeTransform {

  transform(arr: any[]) {
    const result = arr.map(({ id }) => {
      return { id };
    });
    return result;
  }
}
