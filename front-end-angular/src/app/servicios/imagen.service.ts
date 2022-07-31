import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ImagenService {

  usuarioURL = 'https://portfolio-web-back.herokuapp.com/api/usuarios/';

  constructor(private httpClient: HttpClient) { }


  public uploadImagen(imagenFormData: any): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `upload/imagen`, imagenFormData, { observe: 'response' });
  }


  public obtenerImagen(imagen:any):Observable<any> {
    return this.httpClient.get(this.usuarioURL + `get/imagen/` + imagen);

  }


}
