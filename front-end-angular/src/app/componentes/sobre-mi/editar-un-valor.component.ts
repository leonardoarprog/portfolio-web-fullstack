import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Persona } from 'src/app/modelos/persona';
import { DatosPortfolioService } from 'src/app/servicios/datos-portfolio.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-editar-un-valor',
  templateUrl: './editar-un-valor.component.html',
  styleUrls: ['./editar-un-valor.component.css']
})
export class EditarUnValorComponent implements OnInit {

  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  constructor(
    private personaServicio: DatosPortfolioService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
  ) { }

  sobreMi!: Persona;
  dbValor!: any;
  valorParaEditar!: string | null;
  errorMsj: any;

  ngOnInit(): void {

    this.activatedRoute.fragment.subscribe(fragment => {
      this.valorParaEditar = fragment;
    });

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];

    this.personaServicio.obtenerDatosPersonalesPorUsuario(nombre_usuario).subscribe({
      next: data => {

       
        this.dbValor = data.sobreMi;
        console.log("DBVALOR :" + this.dbValor )

      },
      error: _err => {
        this.router.navigate(['']);
      },
    });

  
  }

  updateValor() {
    if (this.valorParaEditar == 'sobre_mi') this.updateSobreMi();
  }


  updateSobreMi(): void {
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.personaServicio.actualizarSobreMi(this.dbValor, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate()
        this.toastr.success('Datos actualizados correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },

      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, 'Error en la actualización de datos', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });

      },

    });
  }

}
