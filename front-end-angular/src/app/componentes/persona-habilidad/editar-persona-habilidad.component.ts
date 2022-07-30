import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoriaHabilidad } from 'src/app/modelos/categoria-habilidad';
import { Habilidad } from 'src/app/modelos/habilidad';
import { HabilidadService } from 'src/app/servicios/habilidad.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-editar-persona-habilidad',
  templateUrl: './editar-persona-habilidad.component.html',
  styleUrls: ['./editar-persona-habilidad.component.css']
})
export class EditarPersonaHabilidadComponent implements OnInit {

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

    const habilidadId = this.activatedRoute.snapshot.params['id'];

    this.habilidadServicio.obtenerPersonaHabilidadPorId(habilidadId).subscribe({
      next: (data) => {

        let categoriaHabilidad_: any = data.categoria_habilidad;
        this.categoriaHabilidad = categoriaHabilidad_;
        let habilidad_: any = Object.keys(data.score);
        this.habilidad = habilidad_;

      },
      error: _err => {
        this.router.navigate(['']);
      },
    });


    this.habilidadServicio.obtenerScore(habilidadId).subscribe({
      next: (data) => {
        this.score = data.valor;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });

  }

  onSubmit(): void {

    this.score = this.f.value.score;
    this.updateScore(this.f.value.score);

  }

  updateScore(newScore: number): void {
    const habilidadId = this.activatedRoute.snapshot.params['id'];
    this.habilidadServicio.actualizarPersonaHabilidadPorId(newScore, habilidadId).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Habilidad actualizada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Habilidad no actualizada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });

  }

}
