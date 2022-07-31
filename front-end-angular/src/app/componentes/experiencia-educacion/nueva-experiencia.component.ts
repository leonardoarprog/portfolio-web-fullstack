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
  selector: 'app-nueva-experiencia',
  templateUrl: './nueva-experiencia.component.html',
  styleUrls: ['./nueva-experiencia.component.css']
})
export class NuevaExperienciaComponent implements OnInit {

  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;
  @ViewChild('f') f!:NgForm;
  tipoEmpleo!: number;
  nombreEmpresa!: string;
  esTrabajoActual: boolean = false;
  fechaIngreso!: Date;
  fechaEgreso!: Date;
  puesto!: string;
  descripcion!: string;
  file!: File;
  formTiposEmpleo!: Array<TipoEmpleo>;
  errorMsj: any;
  experienciaLaboral!: ExperienciaLaboral;
  constructor(
    private activatedRoute: ActivatedRoute,
    private expLaboralServicio: ExperienciaEducacionService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    

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

  onCreate(): void {
   
      const experienciaLaboralDto = new ExperienciaLaboralDto(
      this.f.value.tipoEmpleo, 
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
    
    this.saveFormData(formData);
    
  } 

  saveFormData(formData: FormData) {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.expLaboralServicio.crearExpLaboral(formData, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Experiencia laboral guardada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Experiencia laboral no guardada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
   }  

}
