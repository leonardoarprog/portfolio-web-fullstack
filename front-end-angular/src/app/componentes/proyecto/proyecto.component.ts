import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Proyecto } from 'src/app/modelos/proyecto';
import { ProyectoService } from 'src/app/servicios/proyecto.service';
import { TokenService } from 'src/app/servicios/token.service';

@Component({
  selector: 'app-proyecto',
  templateUrl: './proyecto.component.html',
  styleUrls: ['./proyecto.component.css']
})
export class ProyectoComponent implements OnInit {

  proyectos!: Array<Proyecto>;
  constructor(
    private tokenService: TokenService,
    private proyectoServicio: ProyectoService,
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

    this.cargarProyectos();

  }

  cargarProyectos(): void {
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.proyectoServicio.obtenerProyectosPorPersona(nombre_usuario).subscribe({
      next: data => {
        this.proyectos = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });

  }

  borrarProyecto(id: number) {
    this.proyectoServicio.borrarProyecto(id).subscribe({
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

}
