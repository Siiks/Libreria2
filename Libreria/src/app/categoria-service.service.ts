import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from 'src/classes/categoria';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService{

  private categoriasUrl: string;

  constructor(private http: HttpClient) {
    this.categoriasUrl = environment.apiBaseUrl;
   }
   public findAll(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>(`${this.categoriasUrl}/categoria/all`);
  }
  public findById(id: number): Observable<Categoria>{
     return this.http.get<Categoria>(`${this.categoriasUrl}/categoria/getById/${id}`)
  }
  public updateCategoria(categoria: Categoria): Observable<Categoria>{
    return this.http.put<Categoria>(`${this.categoriasUrl}/categoria/update`, categoria)
  }
  public addCategoria(categoria: Categoria): Observable<Categoria>{
    return this.http.post<Categoria>(`${this.categoriasUrl}/categoria/add`, categoria)
  }
  public deleteCategoria(id: number): Observable<Categoria>{
    return this.http.delete<Categoria>(`${this.categoriasUrl}/categoria/delete/${id}`)
  }
}
