import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  private pedidosUrl: string;
  constructor(private http: HttpClient) {
    this.pedidosUrl = 'http://localhost:8080/pedidos'
   }
}
