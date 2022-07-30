import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Pais } from '../modelos/pais';
import { Provincia } from '../modelos/provincia';
import { Localidad } from '../modelos/localidad';
import { Ubicacion } from '../modelos/ubicacion';



const cabecera = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class SobreMiFormService {

 

  private usuarioURL = 'http://localhost:8080/api/usuarios/'; 



  constructor(private httpClient: HttpClient) { }

  public obtenerPaises(): Observable<Pais[]> {
    return this.httpClient.get<Pais[]>(this.usuarioURL + `paises`, cabecera);

  }

  public obtenerProvincias(id: number): Observable<Provincia[]> {

    const busquedaProvinciasUrl = `${this.usuarioURL}provincias/buscar/pais_id/${id}`;
    return this.httpClient.get<Provincia[]>(busquedaProvinciasUrl, cabecera);

  }


  public obtenerLocalidades(id: number): Observable<Localidad[]> {

    const busquedaLocalidadesUrl = `${this.usuarioURL}localidades/buscar/provincia_id/${id}`;
    return this.httpClient.get<Localidad[]>(busquedaLocalidadesUrl);

  }

  public obtenerLocalidadDefault(): Observable<Ubicacion> {

    const busquedaLocalidadesUrl = `${this.usuarioURL}localidades/buscar/default`;
    return this.httpClient.get<Ubicacion>(busquedaLocalidadesUrl);

  }
 


}

