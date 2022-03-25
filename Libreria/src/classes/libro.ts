import { Autor } from "./autor";
import { Categoria } from "./categoria";

export class Libro {
    id: number;
    titulo: string;
    edicion: number;
    autor: Autor;
    categoria: Categoria;
    
}
