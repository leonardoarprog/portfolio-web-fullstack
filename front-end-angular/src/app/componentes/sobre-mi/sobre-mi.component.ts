import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Persona } from 'src/app/modelos/persona';
import { DataCompartidaService } from 'src/app/servicios/data-compartida.service';
import { DatosPortfolioService } from 'src/app/servicios/datos-portfolio.service';
import { TokenService } from 'src/app/servicios/token.service';

@Component({
  selector: 'app-sobre-mi',
  templateUrl: './sobre-mi.component.html',
  styleUrls: ['./sobre-mi.component.css']
})

export class SobreMiComponent implements OnInit {

  sobreMi!: Persona;
  isLogged = false;
  postResponse: any;
  fotoPerfil: any;
  imgBg: any;
  svgImgBg: string = "";
  dataCompartida: string[] = [];


  constructor(
    private tokenService: TokenService,
    private personaServicio: DatosPortfolioService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dataCompartidaServicio: DataCompartidaService
  ) { }

  ngOnInit(): void {

    const nombreUsuario = this.activatedRoute.snapshot.params['nombreUsuario']

    if (this.tokenService.getToken() && this.tokenService.getUserName() === nombreUsuario) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }

    this.personaServicio.obtenerDatosPersonalesPorUsuario(nombreUsuario).subscribe({
      next: data => {
        this.sobreMi = data;
        this.dataCompartida.push(this.sobreMi.email, this.sobreMi.urlRepositorio, this.sobreMi.urlTwitter, this.sobreMi.urlFacebook)
        this.enviarNuevaData(this.dataCompartida);
      },
      error: _err => {
        this.router.navigate(['']);
      },
    });


    this.personaServicio.obtenerFotoPerfilPorUsuario(nombreUsuario).subscribe(data => {
      if (data == null) {
        this.fotoPerfil = null;
      } else {
        this.fotoPerfil = data;
        const reader = new FileReader();
        reader.onload = (e) => this.fotoPerfil = e.target?.result;
        reader.readAsDataURL(new Blob([data]));
      }
    });


    this.personaServicio.obtenerImgBgPorUsuario(nombreUsuario).subscribe(data => {
      if (data == null) {
        this.imgBg = null;
      } else {
        this.imgBg = data;
        const reader = new FileReader();
        reader.onload = (e) => this.imgBg = e.target?.result;
        reader.readAsDataURL(new Blob([data]));
      }
    });





  }




  borrarSobreMi(): void {
    const nombreUsuario = this.activatedRoute.snapshot.params['nombreUsuario']
    this.personaServicio.borrarSobreMi(nombreUsuario).subscribe({
      next: data => {
        window.location.reload();
      },
      error: _err => {

      },
    });
  }

  enviarNuevaData(data: string[]) {
    this.dataCompartidaServicio.enviarDataByEvent(data);

  }

}
