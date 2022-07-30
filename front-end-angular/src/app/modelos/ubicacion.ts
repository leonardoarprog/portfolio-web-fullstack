import { Localidad } from "./localidad";
import { Pais } from "./pais";
import { Provincia } from "./provincia";

export interface Ubicacion {

    localidad: Localidad;
    provincia: Provincia;
    pais: Pais;

}
