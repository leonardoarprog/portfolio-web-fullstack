import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'safeHtml'
})
export class SafeHtmlPipe implements PipeTransform {

  constructor(private domSanitizer: DomSanitizer) { }

  transform(html: string) {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(html);
  }

}
