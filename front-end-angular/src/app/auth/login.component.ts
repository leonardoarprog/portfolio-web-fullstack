import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUsuario } from '../modelos/login-usuario';
import { AuthService } from '../servicios/auth.service';
import { TokenService } from '../servicios/token.service';
import { ToastrService } from 'ngx-toastr';
import { ModalComponent } from '../componentes/modal/modal.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;
  //form: any = {};
  loginUsuario!: LoginUsuario;
  nombreUsuario!: string;
  password!: string;
  isLogged = false;
  isLoginFail = false;
  roles: string[] = [];
  errorMsj!: string;
  token!: any;

  mensajeFail = '';


  constructor(private authService: AuthService, private tokenService: TokenService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {

    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }

  }

  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);

    this.authService.login(this.loginUsuario).subscribe({
      next: data => {
        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.nombreUsuario);
        this.tokenService.setAuthorities(data.authorities);

        this.isLogged = true;
        this.isLoginFail = false;
        this.roles = this.tokenService.getAuthorities();
        this.token = this.tokenService.getToken();
        this.toastr.success('Bienvenido ' + data.nombreUsuario, 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        
        this.router.navigate(['../username/' + this.nombreUsuario])
          .then(() => {
          //  window.location.reload();
          this.modalUpdate.onCloseAfterUpdate();
          });

      },
      error: err => {
        
        this.mensajeFail = err.error.mensaje;
        this.errorMsj = err.error.mensaje;
        this.toastr.error(this.errorMsj, 'Error de autenticación', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.isLogged = false;
        this.isLoginFail = true;
        
      },
      
    });

  }

}
