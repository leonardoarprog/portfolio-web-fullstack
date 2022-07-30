import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Persona } from 'src/app/modelos/persona';
import { DataCompartidaService } from 'src/app/servicios/data-compartida.service';
import { TokenService } from 'src/app/servicios/token.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})


export class FooterComponent implements OnInit {
  info: any = {};
  isLogged = false;
  datosFooter!:string[];

constructor(
  private dataCompartidaServicio: DataCompartidaService, 
  private tokenService: TokenService, 
  private activatedRoute: ActivatedRoute,
  public router: Router
  ) { }

  ngOnInit(): void {

    this.info = {
    token: this.tokenService.getToken(),
    nombreUsuario: this.tokenService.getUserName(),
    authorities: this.tokenService.getAuthorities()
  };


    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    this.obtenerData();


  }

  obtenerData() {
    this.dataCompartidaServicio.dataByEvent.subscribe(response => {
      this.datosFooter = response;
    });
  }
}
