import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from '../modelos/nuevo-usuario';
import { AuthService } from '../servicios/auth.service';
import { TokenService } from '../servicios/token.service';

@Component({
  selector: 'app-registracion',
  templateUrl: './registracion.component.html',
  styleUrls: ['./registracion.component.css']
})
export class RegistracionComponent implements OnInit {

  nuevoUsuario!: NuevoUsuario;
  nombreUsuario: string = '';
  password: string = '';
  isLogged = false;
  isRegister = false;
  isRegisterFail = false;
  errorMsj = '';

  mensajeFail = '';
  mensajeOK = '';

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

 
  onRegister() {
    this.nuevoUsuario = new NuevoUsuario(this.nombreUsuario, this.password);
    
    this.authService.registro(this.nuevoUsuario).subscribe({
      next:  data => {
        this.mensajeOK = data.mensaje;
        this.isRegister = true;
        this.isRegisterFail = false;
        this.router.navigate(['/login']);
      },
      error: err => {
        this.mensajeFail = err.error.mensaje;
        this.errorMsj = err.error.mensaje;
        this.isRegister = false;
        this.isRegisterFail = true;
      },
   });

  }

}
