import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Educacion } from '../modelos/educacion';
import { ExperienciaLaboral } from '../modelos/experiencia-laboral';
import { TipoEducacion } from '../modelos/tipo-educacion';
import { TipoEmpleo } from '../modelos/tipo-empleo';
import { Usuario } from '../modelos/usuario';

const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class ExperienciaEducacionService {

  usuarioURL = 'http://localhost:8080/api/usuarios/';



  constructor(private httpClient: HttpClient) { }


  ////////////////////////////////////   CRUD EXPERIENCIAS LABORALES  ////////////////////////////

  /* public crearExpLaboral(uploadData: any, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `exp_laborales/exp_lab/crear/${nombreUsuario}`, uploadData, { observe: 'response' });
  } */


  public crearExpLaboral(formData: FormData, nombreUsuario: string): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `exp_laborales/exp_lab/crear/${nombreUsuario}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  /*public crearExpLaboral(formData: FormData, nombreUsuario: string): Observable<any> {
     return this.httpClient.post(this.usuarioURL + `exp_laborales/exp_lab/crear/${nombreUsuario}`, formData,  { observe: 'response' }
   );
   }*/


  public borrarExpLaboral(idExpLab: number): Observable<any> {
    return this.httpClient.delete<any>(this.usuarioURL + `exp_laborales/exp_lab/delete/${idExpLab}`, cabecera);
  }


  public actualizarExpLaboral(formData: FormData, idExpLab: number): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `exp_laborales/exp_lab/update/${idExpLab}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  public obtenerTiposDeEmpleo(): Observable<any> {
    return this.httpClient.get<Array<TipoEmpleo>>(this.usuarioURL + `tipos_de_empleos`, cabecera);

  }


  public obtenerExperienciasLaboralesPorId(id: number): Observable<ExperienciaLaboral> {
    return this.httpClient.get<ExperienciaLaboral>(this.usuarioURL + `exp_laborales/exp_lab/${id}/edit`, cabecera);

  }

  public obtenerExpLabPorPersona(nombreUsuario: string): Observable<Array<ExperienciaLaboral>> {
    return this.httpClient.get<Array<ExperienciaLaboral>>(this.usuarioURL + `exp_laborales/obtener/${nombreUsuario}`, cabecera);

  }






  ////////////////////////////////////    CRUD EDUCACION   ////////////////////////////


  /* public crearExpLaboral(uploadData: any, nombreUsuario: string): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + `exp_laborales/exp_lab/crear/${nombreUsuario}`, uploadData, { observe: 'response' });
  } */


  public crearEducacion(formData: FormData, nombreUsuario: string): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `educaciones/educacion/crear/${nombreUsuario}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  /*public crearExpLaboral(formData: FormData, nombreUsuario: string): Observable<any> {
     return this.httpClient.post(this.usuarioURL + `exp_laborales/exp_lab/crear/${nombreUsuario}`, formData,  { observe: 'response' }
   );
   }*/


  public borrarEducacion(idEducacion: number): Observable<any> {
    return this.httpClient.delete<any>(this.usuarioURL + `educaciones/educacion/delete/${idEducacion}`, cabecera);
  }


  public actualizarEducacion(formData: FormData, idEducacion: number): Observable<any> {
    return this.httpClient.post(this.usuarioURL + `educaciones/educacion/update/${idEducacion}`, formData, { observe: 'response' }).pipe(map((data: any) => {
      return data;
    }));
  }

  public obtenerTiposDeEducacion(): Observable<any> {
    return this.httpClient.get<Array<TipoEducacion>>(this.usuarioURL + `tipos_de_educacion/`, cabecera);

  }


  public obtenerEducacionPorId(id: number): Observable<Educacion> {
    return this.httpClient.get<Educacion>(this.usuarioURL + `educaciones/educacion/edit/${id}`, cabecera);

  }

  public obtenerEducacionesPorPersona(nombreUsuario: string): Observable<Array<Educacion>> {
    return this.httpClient.get<Array<Educacion>>(this.usuarioURL + `educaciones/obtener/${nombreUsuario}`, cabecera);

  }



}
