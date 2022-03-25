import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Libro } from 'src/classes/libro';

@Injectable({
  providedIn: 'root'
})
export class LibroService {
  private librosUrl: string;

  constructor(private http: HttpClient) {
    this.librosUrl = 'http://localhost:8080';
   }
   public findAll(): Observable<Libro[]>{
    return this.http.get<Libro[]>(`${this.librosUrl}/libro/all`);
  }
  public findById(id: number): Observable<Libro>{
     return this.http.get<Libro>(`${this.librosUrl}/libro/getById/${id}`)
  }
  public updateLibro(libro: Libro): Observable<Libro>{
    return this.http.put<Libro>(`${this.librosUrl}/libro/update`, libro)
  }
  public addLibro(libro: Libro): Observable<Libro>{
    return this.http.post<Libro>(`${this.librosUrl}/libro/add`, libro)
  }
  public deleteLibro(id: number): Observable<Libro>{
    return this.http.delete<Libro>(`${this.librosUrl}/libro/delete/${id}`)
  }
  public findLibroByCategoriaId(id: any): Observable<Libro[]>{
    return this.http.get<Libro[]>(`${this.librosUrl}/libro/categoria/${id}`)
  }
}
