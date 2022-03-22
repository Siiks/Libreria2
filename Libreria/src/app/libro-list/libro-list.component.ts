import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/classes/libro';
import { LibroService} from '../libro-service.service';

@Component({
  selector: 'app-libro-list',
  templateUrl: './libro-list.component.html',
  styleUrls: ['./libro-list.component.css']
})
export class LibroListComponent implements OnInit {

  libros: Libro[];
  constructor(private libroService: LibroService) { }

  ngOnInit(): void {
    this.libroService.findAll().subscribe(data => {
      this.libros = data;
    });
  }

}
