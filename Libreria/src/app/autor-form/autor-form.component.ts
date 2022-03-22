import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Autor } from 'src/classes/autor';
import { AutorService } from '../autor-service.service';

@Component({
  selector: 'app-autor-form',
  templateUrl: './autor-form.component.html',
  styleUrls: ['./autor-form.component.css']
})
export class AutorFormComponent implements OnInit {

  autor: Autor;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private autorService: AutorService) {
          this.autor = new Autor();
     }
     
     gotoAutorList(){
       this.router.navigate(['/autor/all'])
     }
  ngOnInit(): void {
  }

}
