import { Habilidad } from "./habilidad";


export class PersonaHabilidad {


    habilidad!: Habilidad;
    score!: number;

    constructor(habilidad: Habilidad, score: number) {

        this.habilidad = habilidad;
        this.score = score;
    }

    
}
