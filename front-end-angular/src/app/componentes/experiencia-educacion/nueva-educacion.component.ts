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
  selector: 'app-nueva-educacion',
  templateUrl: './nueva-educacion.component.html',
  styleUrls: ['./nueva-educacion.component.css']
})
export class NuevaEducacionComponent implements OnInit {

  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;
  @ViewChild('f') f!:NgForm;
  tipoEducacion!: number;
  nombreEstablecimiento!: string;
  enCursoActual: boolean = false;
  fechaIngreso!: Date;
  fechaEgreso!: Date;
  tituloObtenido!: string;
  descripcion!: string;
  file!: File;
  formTiposEducacion!: Array<TipoEducacion>;
  errorMsj: any;
  educacion!: Educacion;
  constructor(
    private activatedRoute: ActivatedRoute,
    private educacionServicio: ExperienciaEducacionService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    

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


  onCreate(): void {
   
      const educacionDto = new EducacionDto(
      this.f.value.tipoEducacion, 
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
    
    this.saveFormData(formData);
    
  } 


  saveFormData(formData: FormData) {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.educacionServicio.crearEducacion(formData, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Educacion guardada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Educacion no guardada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
   }
    
}
