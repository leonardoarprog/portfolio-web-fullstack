


export class Educacion {

    id!: number;
    tipoEducacion!: number;
    nombreEstablecimiento!: string;
    enCursoActual!: boolean;
    fechaIngreso!: Date;
    fechaEgreso!: Date;
    tituloObtenido!: string;
    descripcion!: string;
    isologoEstablecimiento!: any;
  
    constructor(
      tipoEducacion: number,
      nombreEstablecimiento: string,
      enCursoActual: boolean,
      fechaIngreso: Date,
      fechaEgreso: Date,
      tituloObtenido: string,
      descripcion: string,
      isologoEstablecimiento: any) {
      this.tipoEducacion = tipoEducacion;
      this.nombreEstablecimiento = nombreEstablecimiento;
      this.enCursoActual = enCursoActual;
      this.fechaIngreso = fechaIngreso;
      this.fechaEgreso = fechaEgreso;
      this.tituloObtenido = tituloObtenido;
      this.descripcion = descripcion;
      this.isologoEstablecimiento = isologoEstablecimiento;
    }
  
  
  }