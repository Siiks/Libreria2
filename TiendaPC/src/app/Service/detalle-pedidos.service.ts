import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DetallePedidosService {

  private detallePedidos: string;
  constructor(private http: HttpClient) {
    this.detallePedidos = 'http://localhost:8080/detallePedidos'
   }
}
