import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoriaHabilidad } from 'src/app/modelos/categoria-habilidad';
import { Habilidad } from 'src/app/modelos/habilidad';
import { PersonaHabilidad } from 'src/app/modelos/persona-habilidad';
import { HabilidadService } from 'src/app/servicios/habilidad.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-nueva-persona-habilidad',
  templateUrl: './nueva-persona-habilidad.component.html',
  styleUrls: ['./nueva-persona-habilidad.component.css']
})
export class NuevaPersonaHabilidadComponent implements OnInit {

  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;
  categoriaHabilidad!: CategoriaHabilidad;
  categoriasHabilidad!: CategoriaHabilidad[];
  habilidad!: Habilidad;
  habilidades!: Habilidad[];
  score!: number;
  habilidadesSeteadas: any[] = [];
  errorMsj: any;
  

  constructor(
    private activatedRoute: ActivatedRoute,
    private habilidadServicio: HabilidadService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];

    this.habilidadServicio.obtenerPersonaHabilidadPorUsuario(nombre_usuario).subscribe({
      next: (data: any) => {
      data = data.map((a: { score: any; }) => a.score)
      this.habilidadesSeteadas = Object.keys(Object.assign({}, ...data))
      },

      error: _err => {
        this.router.navigate(['']);
      },
    });

    this.habilidadServicio.obtenerCategoriasHabilidad().subscribe({
      next: data => {
        this.categoriasHabilidad = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });
    
  }

  onCreate(): void {
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    const nuevaPersonaHabilidad = new PersonaHabilidad(this.habilidad, this.score);
    this.habilidadServicio.crearPersonaHabilidad(nuevaPersonaHabilidad, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Habilidad guardada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Habilidad no guardada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });

  }


  obtenerHabilidades() {

    const idCategoria = this.f.value.categoriaHabilidad.id;
    this.habilidadServicio.obtenerHabilidadesPorCategoriaId(idCategoria).subscribe(
      data => {
        this.habilidades = data;
      }
    );
  }

}
