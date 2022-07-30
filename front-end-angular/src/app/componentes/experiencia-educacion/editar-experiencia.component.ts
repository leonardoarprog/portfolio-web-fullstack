import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ExperienciaLaboral } from 'src/app/modelos/experiencia-laboral';
import { ExperienciaLaboralDto } from 'src/app/modelos/experiencia-laboral-dto';
import { TipoEmpleo } from 'src/app/modelos/tipo-empleo';
import { ExperienciaEducacionService } from 'src/app/servicios/experiencia-educacion.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-editar-experiencia',
  templateUrl: './editar-experiencia.component.html',
  styleUrls: ['./editar-experiencia.component.css']
})
export class EditarExperienciaComponent implements OnInit {

  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  
  experienciaLaboral!: ExperienciaLaboral;
  formTiposEmpleo!: Array<TipoEmpleo>;
  file!: File;
  errorMsj: any;

  constructor(
    private expLaboralServicio: ExperienciaEducacionService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    
    const experienciaLaboralId = this.activatedRoute.snapshot.params['id'];

    this.expLaboralServicio.obtenerExperienciasLaboralesPorId(experienciaLaboralId).subscribe(
      data => {
        this.experienciaLaboral = data;
      }
    );

    this.expLaboralServicio.obtenerTiposDeEmpleo().subscribe({
      next: data => {
        this.formTiposEmpleo = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });


  }


  public onImagenUpload(event: any) {
    this.file = event.target.files[0];
  }




  onUpdateExpLab(): void {

    const experienciaLaboralDto = new ExperienciaLaboralDto(
      this.f.value.tipoEmpleo.id, 
      this.f.value.nombreEmpresa, 
      this.f.value.esTrabajoActual,
      this.f.value.fechaIngreso,
      this.f.value.fechaEgreso,
      this.f.value.puesto,
      this.f.value.descripcion
    );

  const formData: FormData = new FormData();
  formData.append('file', this.file);
  formData.append('info', new Blob([JSON
    .stringify(experienciaLaboralDto )], {
    type: 'application/json'
  }));
  
  this.updateFormData(formData);
  
  }

  updateFormData(formData: FormData) {

    const experienciaLaboralId = this.activatedRoute.snapshot.params['id'];
    this.expLaboralServicio.actualizarExpLaboral(formData, experienciaLaboralId).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Experiencia laboral actualizada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Experiencia laboral no actualizada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
  }

  public compareObjects(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }



}
