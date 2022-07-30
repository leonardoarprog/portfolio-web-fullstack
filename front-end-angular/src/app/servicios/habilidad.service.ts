import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoriaHabilidad } from '../modelos/categoria-habilidad';
import { Habilidad } from '../modelos/habilidad';
import { PersonaHabilidad } from '../modelos/persona-habilidad';

const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class HabilidadService {

  usuarioURL = 'http://localhost:8080/api/usuarios/';



  constructor(private httpClient: HttpClient) { }

////////////////////////////////////  CATEGORIAS HABILIDADES  ////////////////////////////

  public obtenerCategoriasHabilidad(): Observable<any> {
    return this.httpClient.get<Array<CategoriaHabilidad>>(this.usuarioURL + `categorias_habilidad`, cabecera);

  }

  public obtenerHabilidadesPorCategoriaId(id: number): Observable<Habilidad[]> {

    return this.httpClient.get<Habilidad[]>(this.usuarioURL + `habilidades/buscar/categoria_habilidad_id/${id}`, cabecera);

  }


////////////////////////////////////  HABILIDADES CRUD  ////////////////////////////

public crearPersonaHabilidad(personaHabilidad: PersonaHabilidad, nombreUsuario: string): Observable<any> {
  return this.httpClient.post<any>(this.usuarioURL + `habilidades/habilidad/crear/${nombreUsuario}`, personaHabilidad, cabecera);
}


public obtenerPersonaHabilidadPorUsuario(nombreUsuario: string): Observable<any[]> {
  return this.httpClient.get<any[]>(this.usuarioURL + `habilidades/obtener/${nombreUsuario}`, cabecera);
}

public borrarPersonaHabilidad(idPersHab: number): Observable<any> {
  return this.httpClient.delete<any>(this.usuarioURL + `habilidades/habilidad/delete/${idPersHab}`, cabecera);
}

public obtenerPersonaHabilidadPorId(id: number): Observable<any> {
  return this.httpClient.get<any>(this.usuarioURL + `habilidades/habilidad/edit/${id}`, cabecera);

}

public actualizarPersonaHabilidadPorId(score: number, id: number): Observable<any> {
  return this.httpClient.post<any>(this.usuarioURL + `habilidades/habilidad/update/${id}`, score, cabecera);

}

public obtenerScore(id: number): Observable<any> {
  return this.httpClient.get<any>(this.usuarioURL + `habilidades/habilidad/score/obtener/${id}`, cabecera);
}

}
