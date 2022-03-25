import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/classes/libro';
import { LibroService} from '../libro-service.service';
import { NgForm } from '@angular/forms';
import { Autor } from 'src/classes/autor';
import { HttpErrorResponse } from '@angular/common/http';
import { Categoria } from 'src/classes/categoria';
import { CategoriaService } from '../categoria-service.service';
import { AutorService } from '../autor-service.service';
@Component({
  selector: 'app-libro-list',
  templateUrl: './libro-list.component.html',
  styleUrls: ['./libro-list.component.css']
})
export class LibroListComponent implements OnInit {

  public libros: Libro[];
  public editLibro: Libro;
  public libro: Libro;
  public deleteLibroAtt: Libro;
  public categoria: Categoria;
  public categorias: Categoria[];
  public autor: Autor;
  public autores: Autor[];
  public categoriaActual = null;
  constructor(private libroService: LibroService, private categoriaService: CategoriaService, private autorService: AutorService) { }
  
  ngOnInit(): void {
    this.getLibros();
    this.getCategorias();
    this.getAutores();
  }
  getAutores(){
    this.autorService.findAll().subscribe(data =>{
      this.autores = data;
    })
  }
  getCategorias() {
    this.categoriaService.findAll().subscribe(data =>{
      this.categorias = data;
    })
  }
  getLibros(){
    this.libroService.findAll().subscribe(data => {
      this.libros = data;
    });
  };
  addLibro(addForm: NgForm): void{
    document.getElementById('add-libro-form').click();
    this.libroService.addLibro(addForm.value).subscribe(
      (response: Libro) => {
        console.log(response);
        this.getLibros();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  deleteLibro(libroId: number): void{
    this.libroService.deleteLibro(libroId).subscribe(
      (response) =>{
        console.log(response);
        this.getLibros();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public updateLibro(libro: Libro): void {
    this.libroService.updateLibro(libro).subscribe(
      (response: Libro) => {
        console.log(response);
        this.getLibros();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public findLibroByCategoriaId(categoria){ 
    this.libros = [];
    this.categoriaActual = categoria.descripcion;
    this.libroService.findLibroByCategoriaId(categoria.id).subscribe(
      (response: Libro[]) => {
        this.libros = response;
      })
  }
    public onOpenModal(libro: Libro, mode: string): void {
      const container = document.getElementById('main-container');
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if (mode === 'add') {
        button.setAttribute('data-target', '#addLibroModal');
      }
      if (mode === 'edit') {
        this.editLibro = libro;
        button.setAttribute('data-target', '#updateLibroModal');
      }
      if (mode === 'delete') {
        this.deleteLibroAtt = libro;
        button.setAttribute('data-target', '#deleteLibroModal');
      }
      container.appendChild(button);
      button.click();
    }
  
}
