import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Educacion } from 'src/app/modelos/educacion';
import { ExperienciaLaboral } from 'src/app/modelos/experiencia-laboral';
import { ExperienciaEducacionService } from 'src/app/servicios/experiencia-educacion.service';
import { TokenService } from 'src/app/servicios/token.service';



@Component({
  selector: 'app-experiencia-educacion',
  templateUrl: './experiencia-educacion.component.html',
  styleUrls: ['./experiencia-educacion.component.css']
})
export class ExperienciaEducacionComponent implements OnInit {

  experienciasLaborales!: Array<ExperienciaLaboral>;
  educaciones!: Array<Educacion>;
  constructor(
    private tokenService: TokenService, 
    private expLabEducServicio: ExperienciaEducacionService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService
  ) { }

  isLogged = false;
  errorMsj: any;

  ngOnInit(): void {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];

    if (this.tokenService.getToken() && this.tokenService.getUserName() === nombre_usuario) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
     }

    this.cargarExpLabsPorPersona();
    this.cargarEducacionPorPersona();


  }

  cargarExpLabsPorPersona(): void {
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.expLabEducServicio.obtenerExpLabPorPersona(nombre_usuario).subscribe({
      next: data => {
        this.experienciasLaborales = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });

  }

  cargarEducacionPorPersona(): void {
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.expLabEducServicio.obtenerEducacionesPorPersona(nombre_usuario).subscribe({
      next: data => {
        this.educaciones = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });

  }

  borrarExpLab(id: number) {
    this.expLabEducServicio.borrarExpLaboral(id).subscribe({
      next: () => {
        window.location.reload();
        this.toastr.success('Datos borrados correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, 'Error en el borrado', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  }

  borrarEducacion(id: number) {
    this.expLabEducServicio.borrarEducacion(id).subscribe({
      next: () => {
        window.location.reload();
        this.toastr.success('Datos borrados correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, 'Error en el borrado', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
   }

}