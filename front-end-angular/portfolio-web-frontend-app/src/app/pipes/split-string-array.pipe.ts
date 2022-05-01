import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'splitStringArray'
})

export class SplitStringArrayPipe implements PipeTransform {
  transform(texto: any): string[] {
    let arrayTexto: string[] = [];
    for (let key in texto) {
      if (texto[key] == ' ') {
        arrayTexto.push('\u2009');
      } else
      arrayTexto.push(texto[key]);
    }
    return arrayTexto;
  }
}