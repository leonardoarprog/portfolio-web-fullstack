import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DatosPortfolioService } from 'src/app/servicios/datos-portfolio.service';
import { ModalComponent } from '../modal/modal.component';


@Component({
  selector: 'app-imagen',
  templateUrl: './imagen.component.html',
  styleUrls: ['./imagen.component.css']
})
export class ImagenComponent implements OnInit {
  @ViewChild('f') f!: NgForm;
  @ViewChild(ModalComponent) modalUpdate!: ModalComponent;

  constructor(
    private personaServicio: DatosPortfolioService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
  ) { }

  uploadedImagen!: File;
  dbImagen: any;
  currentImagen!: string | null;
  errorMsj: any;

  ngOnInit(): void {

    this.activatedRoute.fragment.subscribe(fragment => {
      this.currentImagen = fragment;
    });

  }

  public onImagenUpload(event: any) {
    this.uploadedImagen = event.target.files[0];
  }

  uploadImagen() {

    if (this.currentImagen == 'foto_perfil') this.uploadFotoPerfil();
    else if (this.currentImagen == 'img_bg') this.uploadImgBg();
  }


  uploadFotoPerfil() {

    const imagenFormData = new FormData();
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    imagenFormData.append('imagen', this.uploadedImagen);
    this.personaServicio.uploadFotoPerfil(imagenFormData, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Imagen guardada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Imagen no cargada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  }


  uploadImgBg() {

    const imagenFormData = new FormData();
    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];
    imagenFormData.append('imagen', this.uploadedImagen);
    this.personaServicio.uploadImgBg(imagenFormData, nombre_usuario).subscribe({
      next: () => {
        this.modalUpdate.onCloseAfterUpdate();
        this.toastr.success('Imagen guardada correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, '¡Imagen no cargada debido a algún error!', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  }

}