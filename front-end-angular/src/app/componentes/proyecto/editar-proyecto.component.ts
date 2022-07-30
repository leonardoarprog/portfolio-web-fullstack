import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Proyecto } from 'src/app/modelos/proyecto';
import { ProyectoDto } from 'src/app/modelos/proyecto-dto';
import { ProyectoService } from 'src/app/servicios/proyecto.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-editar-proyecto',
  templateUrl: './editar-proyecto.component.html',
  styleUrls: ['./editar-proyecto.component.css']
})
export class EditarProyectoComponent implements OnInit {

  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  
  proyecto!: Proyecto;
  file1!: File;
  file2!: File;
  file3!: File;
  errorMsj: any;

  constructor(
    private proyectoServicio: ProyectoService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    
    const proyectoId = this.activatedRoute.snapshot.params['id'];

    this.proyectoServicio.obtenerProyectosPorId(proyectoId).subscribe(
      data => {
        this.proyecto = data;
      }
    );
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




  onUpdateProyecto(): void {

    const proyectoDto = new ProyectoDto(
      this.f.value.nombreProyecto, 
      this.f.value.fechaRealizacion,
      this.f.value.descripcion,
      this.f.value.urlProyecto
    );

  const formData: FormData = new FormData();
  formData.append('file1', this.file1);
  formData.append('file2', this.file2);
  formData.append('file3', this.file3);
  formData.append('info', new Blob([JSON
    .stringify(proyectoDto)], {
    type: 'application/json'
  }));
  
  this.updateFormData(formData);
  
  }

  updateFormData(formData: FormData) {

    const proyectoId = this.activatedRoute.snapshot.params['id'];
    this.proyectoServicio.actualizarProyecto(formData, proyectoId).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Proyecto actualizado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Proyecto no actualizado debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  
  }

}
