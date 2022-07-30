export class ProyectoDto {

    id!: number;
    nombreProyecto!: string;
    fechaRealizacion!: Date;
    descripcion!: string;
    urlProyecto!: string;

    constructor(
      nombreProyecto: string,
      fechaRealizacion: Date,
      descripcion: string,
      urlProyecto: string )
    {
      this.nombreProyecto = nombreProyecto;
      this.fechaRealizacion = fechaRealizacion;
      this.descripcion = descripcion;
      this.urlProyecto = urlProyecto;
     }
  
  }
  