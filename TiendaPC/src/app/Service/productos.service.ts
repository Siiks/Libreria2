import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private productoUrl: string;
  constructor(private http: HttpClient) {
    this.productoUrl = 'http://localhost:8080/productos'
   }
}
