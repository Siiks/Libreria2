import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private usuariosUrl: string;
  constructor(private http: HttpClient) {
    this.usuariosUrl = 'http://localhost:8080/usuarios'
   }
}
