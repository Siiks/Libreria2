import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  private categoriasUrl: string;

  constructor(private http: HttpClient) {
    this.categoriasUrl = 'http://localhost:8080/categorias'
   }
}
