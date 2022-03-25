import { Component, OnInit } from '@angular/core';
import { AutorService } from '../autor-service.service';
import { Autor } from 'src/classes/autor';
@Component({
  selector: 'app-autor-list',
  templateUrl: './autor-list.component.html',
  styleUrls: ['./autor-list.component.css']
})
export class AutorListComponent implements OnInit {

  autors: Autor[];
  autor: Autor;
  constructor(private autorService: AutorService) {
   }

  ngOnInit(): void {
    this.autorService.findAll().subscribe(data => {
      this.autors = data;
    });
  }

}
