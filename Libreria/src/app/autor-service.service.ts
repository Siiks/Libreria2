import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Autor } from 'src/classes/autor';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AutorService {
  private autorsUrl: string;

  constructor(private http: HttpClient) {
    this.autorsUrl = 'http://localhost:8080';
   }

   public findAll(): Observable<Autor[]>{
     return this.http.get<Autor[]>(`${this.autorsUrl}/autor/all`);
   }
   public findById(dni: string): Observable<Autor>{
      return this.http.get<Autor>(`${this.autorsUrl}/autor/getById/${dni}`)
   }
   public updateAutor(autor: Autor): Observable<Autor>{
     return this.http.put<Autor>(`${this.autorsUrl}/autor/update`, autor)
   }
   public addAutor(autor: Autor): Observable<Autor>{
     return this.http.post<Autor>(`${this.autorsUrl}/autor/add`, autor)
   }
   public deleteAutor(dni: string): Observable<Autor>{
     return this.http.delete<Autor>(`${this.autorsUrl}/autor/delete/${dni}`)
   }
}
