export class NuevoUsuario {
    nombreUsuario: string;
    roles: string[];
    password: string;

    constructor(nombreUsuario: string, password: string) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.roles = ['admin'];
    }
}