import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'deleteProperty'
})
export class DeletePropertyPipe implements PipeTransform {


  transform(values: any[], filters: string[]) {
    return values.reduce((acc, val) => {
      if (filters.some(filter => filter == val.type)) {
        acc.push(val)
      }
      return acc
    }, [])
  }

}
