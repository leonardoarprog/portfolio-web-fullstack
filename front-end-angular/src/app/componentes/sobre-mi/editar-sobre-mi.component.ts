import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { forkJoin } from 'rxjs';
import { Localidad } from 'src/app/modelos/localidad';
import { Pais } from 'src/app/modelos/pais';
import { Persona } from 'src/app/modelos/persona';
import { Provincia } from 'src/app/modelos/provincia';
import { DatosPortfolioService } from 'src/app/servicios/datos-portfolio.service';
import { SobreMiFormService } from 'src/app/servicios/sobre-mi-form.service';
import { ModalComponent } from '../modal/modal.component';


@Component({
  selector: 'app-editar-sobre-mi',
  templateUrl: './editar-sobre-mi.component.html',
  styleUrls: ['./editar-sobre-mi.component.css']
})
export class EditarSobreMiComponent implements OnInit {

  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  sobreMi!: Persona;
  errorMsj: any;
  paises!: Pais[];
  provincias!: Provincia[];
  localidades!: Localidad[];


  constructor(
    private personaServicio: DatosPortfolioService,
    private sobreMiFormServicio: SobreMiFormService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,

  ) { }

  ngOnInit() {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    forkJoin(
      [this.personaServicio.obtenerDatosPersonalesPorUsuario(nombre_usuario), this.sobreMiFormServicio.obtenerLocalidadDefault()]).subscribe({
        next: data => {
          if (data[0].ubicacion === null) {

            Object.assign(data[0], { ubicacion: data[1] });
            this.sobreMi = data[0];

          } else {
            this.sobreMi = data[0];
          }

          this.sobreMiFormServicio.obtenerProvincias(this.sobreMi.ubicacion.pais.id).subscribe(
            data => {
              this.provincias = data;
            }
          );

          this.sobreMiFormServicio.obtenerLocalidades(this.sobreMi.ubicacion.provincia.id).subscribe(
            data => {
              this.localidades = data;
            }
          );


        },
        error: _err => {
          this.router.navigate(['']);
        },
      });

    this.sobreMiFormServicio.obtenerPaises().subscribe(
      data => {
        this.paises = data;
      }
    );

  }

  onUpdate(): void {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];

    this.personaServicio.actualizarDatosPersonales(this.sobreMi, nombre_usuario).subscribe({
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


  updateProvinciaModel() {
    this.f.form.controls['provincia'].setErrors({ 'incorrect': true });
  }

  updateLocalidadModel() {
    this.f.form.controls['localidad'].setErrors({ 'incorrect': true });
  }

  someFunction() {
    alert('TOT');
  }


  obtenerProvincias() {

    const idPais = this.f.value.pais.id;



    this.sobreMiFormServicio.obtenerProvincias(idPais).subscribe(
      data => {

        this.provincias = data;


      }
    );

    this.updateProvinciaModel();
  }


  obtenerLocalidades() {

    const idProvincia = this.f.value.provincia.id;

    this.sobreMiFormServicio.obtenerLocalidades(idProvincia).subscribe(
      data => {

        this.localidades = data;


      }
    );

    this.updateLocalidadModel();

  }

  public compareObjects(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }

}