import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Autor } from 'src/classes/autor';
import { Categoria } from 'src/classes/categoria';
import { Libro } from 'src/classes/libro';
import { AutorService } from './autor-service.service';
import { CategoriaService } from './categoria-service.service';
import { LibroService } from './libro-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Libreria';
  autors: Autor[];
  categorias: Categoria[];
  libros: Libro[];
  constructor(private router: Router, private autorService: AutorService, private categoriaService: CategoriaService, private librosService: LibroService){}
    getAutors(){
      this.autorService.findAll().subscribe(data => {
        this.autors = data;
      });
    };
      getCategorias(){
        this.categoriaService.findAll().subscribe(data => {
          this.categorias = data;
        });
  };
      getLibros(){
        this.librosService.findAll().subscribe(data => {
          this.libros = data;
        });
      };
  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getAutors();
    })
    this.router.events.subscribe(value => {
      this.getCategorias();
    })
    this.router.events.subscribe(value => {
      this.getLibros();
    })
  }
}
