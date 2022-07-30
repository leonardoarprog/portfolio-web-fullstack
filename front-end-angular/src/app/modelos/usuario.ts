import { Persona } from "./persona";

export class Usuario {
    id!: number;
    nombreUsuario: string;
    password: string;
    persona!: Persona;

    constructor(nombreUsuario: string, password: string) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
}