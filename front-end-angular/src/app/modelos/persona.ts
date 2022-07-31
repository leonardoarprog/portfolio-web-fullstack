import { Ubicacion } from "./ubicacion";


export class Persona {

  id!: number;
  nombre!: string;
  apellido!: string;
  fechaNacimiento!: Date;
  email!: string;
  direccion!: string;
  ubicacion!: Ubicacion;
  posicionLaboral!: string;
  sobreMi!: string;
  fotoPerfil!: any;
  imgBg!:any;
  urlRepositorio!: string;
  urlFacebook!: string;
  urlTwitter!: string;
  constructor() {
  }

}