import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../modelos/persona';


const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
//const cabeceraMultipart = { headers: new HttpHeaders({ 'Content-Type': '') };


@Injectable({
  providedIn: 'root'
})

export class DatosPortfolioService {

  usuarioURL = 'https://portfolio-web-back.herokuapp.com/api/usuarios/';


  constructor(private httpClient: HttpClient) { }

  /////////////////////////////////////  CRUD PERSONA   /////////////////////////////////////////

  public obtenerDatosPersonalesPorUsuario(nombreUsuario: string): Observable<Persona> {
    return this.httpClient.get<Persona>(this.usuarioURL + `username/${nombreUsuario}`, cabecera);

  }

  public obtenerDatosPersonalesPorId(usuario_id: number): Observable<Persona> {
    return this.httpClient.get<Persona>(this.usuarioURL + `datos_personales/user_id/${usuario_id}`, cabecera);

  }

  public actualizarDatosPersonales(persona: Persona, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `datos_personales/actualizar/${nombreUsuario}`, persona, cabecera);
  }


  public actualizarSobreMi(sobreMi: String, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `datos_personales/sobre_mi/actualizar/${nombreUsuario}`, sobreMi, cabecera);
  }

  public obtenerSobreMi(nombreUsuario: string): Observable<string> {
    return this.httpClient.get<string>(this.usuarioURL + `datos_personales/sobre_mi/obtener/${nombreUsuario}`, cabecera);

  }

  public borrarSobreMi(nombreUsuario: string): Observable<any> {
    return this.httpClient.delete<any>(this.usuarioURL + `datos_personales/sobre_mi/borrar/${nombreUsuario}`, cabecera);

  }



  ////////////////////////////////////  IMAGENES  ////////////////////////////

  public uploadFotoPerfil(imagenFormData: any, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `datos_personales/foto_perfil/actualizar/${nombreUsuario}`, imagenFormData, { observe: 'response' });
  }

  public obtenerFotoPerfilPorUsuario(nombreUsuario: string): Observable<any> {
    return this.httpClient.get(this.usuarioURL + `datos_personales/foto_perfil/obtener/${nombreUsuario}`, { responseType: 'blob' });

  }

  public uploadImgBg(imagenFormData: any, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `datos_personales/img_bg/actualizar/${nombreUsuario}`, imagenFormData, { observe: 'response' });
  }

  public obtenerImgBgPorUsuario(nombreUsuario: string): Observable<any> {
    return this.httpClient.get(this.usuarioURL + `datos_personales/img_bg/obtener/${nombreUsuario}`, { responseType: 'blob' });

  }


}