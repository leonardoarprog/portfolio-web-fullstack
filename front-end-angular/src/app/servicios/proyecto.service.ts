import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Proyecto } from '../modelos/proyecto';

const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  private usuarioURL = 'https://portfolio-web-back.herokuapp.com/api/usuarios/';

  //private usuarioURL = 'http://localhost:8080/api/usuarios/';

  constructor(private httpClient: HttpClient) { }


  public crearProyecto(formData: FormData, nombreUsuario: string): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `proyectos/proyecto/crear/${nombreUsuario}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  public borrarProyecto(idExpLab: number): Observable<any> {
    return this.httpClient.delete<any>(this.usuarioURL + `proyectos/proyecto/delete/${idExpLab}`, cabecera);
  }

  public actualizarProyecto(formData: FormData, idProyecto: number): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `proyectos/proyecto/update/${idProyecto}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  public obtenerProyectosPorId(id: number): Observable<Proyecto> {
    return this.httpClient.get<Proyecto>(this.usuarioURL + `proyectos/proyecto/edit/${id}`, cabecera);

  }

  public obtenerProyectosPorPersona(nombreUsuario: string): Observable<Array<Proyecto>> {
    return this.httpClient.get<Array<Proyecto>>(this.usuarioURL + `proyectos/obtener/${nombreUsuario}`, cabecera);

  }


}
