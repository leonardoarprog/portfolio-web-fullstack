import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Educacion } from 'src/app/modelos/educacion';
import { EducacionDto } from 'src/app/modelos/educacion-dto';
import { TipoEducacion } from 'src/app/modelos/tipo-educacion';
import { ExperienciaEducacionService } from 'src/app/servicios/experiencia-educacion.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-editar-educacion',
  templateUrl: './editar-educacion.component.html',
  styleUrls: ['./editar-educacion.component.css']
})
export class EditarEducacionComponent implements OnInit {

  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  
  educacion!: Educacion;
  formTiposEducacion!: Array<TipoEducacion>;
  file!: File;
  errorMsj: any;

  constructor(
    private educacionServicio: ExperienciaEducacionService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    
    const educacionId = this.activatedRoute.snapshot.params['id'];

    this.educacionServicio.obtenerEducacionPorId(educacionId).subscribe(
      data => {
        this.educacion = data;
      }
    );

    this.educacionServicio.obtenerTiposDeEducacion().subscribe({
      next: data => {
        this.formTiposEducacion = data;
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });


  }


  public onImagenUpload(event: any) {
    this.file = event.target.files[0];
  }




  onUpdateEducacion(): void {

    const educacionDto = new EducacionDto(
      this.f.value.tipoEducacion.id, 
      this.f.value.nombreEstablecimiento, 
      this.f.value.enCursoActual,
      this.f.value.fechaIngreso,
      this.f.value.fechaEgreso,
      this.f.value.tituloObtenido,
      this.f.value.descripcion
    );

  const formData: FormData = new FormData();
  formData.append('file', this.file);
  formData.append('info', new Blob([JSON
    .stringify(educacionDto )], {
    type: 'application/json'
  }));
  
  this.updateFormData(formData);
  
  }

  updateFormData(formData: FormData) {

    const educacionId = this.activatedRoute.snapshot.params['id'];
    this.educacionServicio.actualizarEducacion(formData, educacionId).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Educacion actualizada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Educacion no actualizada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
  }

  public compareObjects(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }



}
