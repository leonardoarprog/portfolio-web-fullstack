import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtModel } from '../modelos/jwt-model';
import { LoginUsuario } from '../modelos/login-usuario';
import { NuevoUsuario } from '../modelos/nuevo-usuario';

const cabecera = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})



export class AuthService {

  private authURL = 'https://portfolio-web-back.herokuapp.com/api/auth/';

  constructor(private httpClient: HttpClient) { }

  public login(usuario: LoginUsuario): Observable<JwtModel> {
    return this.httpClient.post<JwtModel>(this.authURL + 'login', usuario, cabecera);
  }

  public registro(usuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(this.authURL + 'nuevo_usuario', usuario, cabecera);
  }


}
