export class Proyecto {

  id!: number;
  nombreProyecto!: string;
  fechaRealizacion!: Date;
  descripcion!: string;
  urlProyecto!: string;
  imgProyecto1!:any;
  imgProyecto2!:any;
  imgProyecto3!:any;

  constructor(
    nombreProyecto: string,
    fechaRealizacion: Date,
    descripcion: string,
    urlProyecto: string,
    imgProyecto1:any,
  imgProyecto2:any,
  imgProyecto3:any) {
    this.nombreProyecto = nombreProyecto;
    this.fechaRealizacion = fechaRealizacion;
    this.descripcion = descripcion;
    this.urlProyecto = urlProyecto;
    this.imgProyecto1 = imgProyecto1;
    this.imgProyecto2 = imgProyecto2;
    this.imgProyecto3 = imgProyecto3;
   }

}
