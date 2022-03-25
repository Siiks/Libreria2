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
  constructor(){}

  ngOnInit(): void {
}
}