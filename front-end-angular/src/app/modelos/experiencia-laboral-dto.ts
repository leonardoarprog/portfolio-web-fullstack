export class ExperienciaLaboralDto {

    id!: number;
    tipoEmpleo!: number;
    nombreEmpresa!: string;
    esTrabajoActual!: boolean;
    fechaIngreso!: Date;
    fechaEgreso!: Date;
    puesto!: string;
    descripcion!: string;

  
    constructor(
      tipoEmpleo: number,
      nombreEmpresa: string,
      esTrabajoActual: boolean,
      fechaIngreso: Date,
      fechaEgreso: Date,
      puesto: string,
      descripcion: string) {
      this.tipoEmpleo = tipoEmpleo;
      this.nombreEmpresa = nombreEmpresa;
      this.esTrabajoActual = esTrabajoActual;
      this.fechaIngreso = fechaIngreso;
      this.fechaEgreso = fechaEgreso;
      this.puesto = puesto;
      this.descripcion = descripcion;

    }

}
