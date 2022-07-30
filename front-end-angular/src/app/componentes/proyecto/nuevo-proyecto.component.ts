import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Proyecto } from 'src/app/modelos/proyecto';
import { ProyectoDto } from 'src/app/modelos/proyecto-dto';
import { ProyectoService } from 'src/app/servicios/proyecto.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-nuevo-proyecto',
  templateUrl: './nuevo-proyecto.component.html',
  styleUrls: ['./nuevo-proyecto.component.css']
})
export class NuevoProyectoComponent implements OnInit {

  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;
  @ViewChild('f') f!:NgForm;

  nombreProyecto!: string;
  fechaRealizacion!: Date;
  descripcion!: string;
  urlProyecto!: string;


  file1!: File;
  file2!: File;
  file3!: File;
  errorMsj: any;
  //proyecto!: Proyecto;
  constructor(
    private activatedRoute: ActivatedRoute,
    private proyectoServicio: ProyectoService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    

  }

  public onImagenUpload1(event: any) {
    this.file1 = event.target.files[0];
  }

  public onImagenUpload2(event: any) {
    this.file2 = event.target.files[0];
  }

  public onImagenUpload3(event: any) {
    this.file3 = event.target.files[0];
  }


  onCreate(): void {
   
      const proyectoDto = new ProyectoDto(
      this.f.value.nombreProyecto, 
      this.f.value.fechaRealizacion,
      this.f.value.descripcion,
      this.f.value.urlProyecto,
      
    );

    const formData: FormData = new FormData();
    formData.append('file1', this.file1);
    formData.append('file2', this.file2);
    formData.append('file3', this.file3);
    formData.append('info', new Blob([JSON
      .stringify(proyectoDto )], {
      type: 'application/json'
    }));
    
    this.saveFormData(formData);
    
  } 


  saveFormData(formData: FormData) {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    this.proyectoServicio.crearProyecto(formData, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Proyecto guardado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Proyecto no guardada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
   }    

}
