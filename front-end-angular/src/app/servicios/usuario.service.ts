import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../modelos/usuario';

const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  //private usuarioURL = 'http://localhost:8080/api/usuarios/';

  private usuarioURL = 'https://portfolio-web-back.herokuapp.com/api/usuarios/';

  constructor(private httpClient: HttpClient) { }


  public agregar(usuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + 'agregar', usuario, cabecera);
  }

  public actualizar(usuario: Usuario, id: number): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `actualizar/${id}`, usuario, cabecera);
  }

  public eliminar(id: number): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `eliminar/${id}`, cabecera);
  }

}