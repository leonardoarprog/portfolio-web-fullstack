import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../modelos/persona';
import { Usuario } from '../modelos/usuario';

const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})

export class PersonaService {

  personaURL = 'http://localhost:8080/api/usuarios/';

  constructor(private httpClient: HttpClient) { }

 public obtenerDatosPersonalesPorUsuario(nombreUsuario: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.personaURL + `username/${nombreUsuario}`, cabecera);

  }


  public obtenerDatosPersonalesPorId(usuario_id: number): Observable<Persona> {
    return this.httpClient.get<Persona>(this.personaURL + `datos_personales/user_id/${usuario_id}`, cabecera);

  }

 /* public obtenerDatosPersonales(nombreUsuario: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.personaURL + `username/leonardouser`, cabecera);

  }*/


  public actualizarDatosPersonales(persona: Persona, usuario_id: number): Observable<any> {
    return this.httpClient.post<any>(this.personaURL + `datos_personales/actualizar/${usuario_id}`, persona, cabecera);
  }

  public borrarExpLaboral(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.personaURL + `exp_laborales/delete/${id}`, cabecera);
  }

}