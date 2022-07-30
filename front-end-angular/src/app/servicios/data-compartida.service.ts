import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataCompartidaService {

  constructor() { }

  dataByEvent: EventEmitter<string[]> = new EventEmitter<string[]>();

  enviarDataByEvent(data:string[]) {
    this.dataByEvent.emit(data);
  }
   
}
