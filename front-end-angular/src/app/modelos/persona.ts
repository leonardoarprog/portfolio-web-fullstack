import { ExperienciaLaboral } from "./experiencia-laboral";
import { Localidad } from "./localidad";
import { Provincia } from "./provincia";
import { Pais } from "./pais";
import { Ubicacion } from "./ubicacion";


export class Persona {

  id!: number;
  nombre!: string;
  apellido!: string;
  fechaNacimiento!: Date;
  email!: string;
  direccion!: string;
  ubicacion!: Ubicacion;
 // localidad!: Localidad;
 // provincia!: Provincia;
 // pais!: Pais;
  posicionLaboral!: string;
  sobreMi!: string;
  urlFoto!: string;
  urlImgBg!: string;
  urlRepositorio!: string;
  urlFacebook!: string;
  urlTwitter!: string;
  //experienciaLaboral!: Array<ExperienciaLaboral>;
  data: any;

  constructor() {
  }

}