import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorFormComponent } from './autor-form/autor-form.component';
import { AutorListComponent } from './autor-list/autor-list.component';
import { CategoriaListComponent } from './categoria-list/categoria-list.component';
import { LibroListComponent } from './libro-list/libro-list.component';

const routes: Routes = [
  {path: 'autor/all', component: AutorListComponent},
  {path: 'categoria/all', component: CategoriaListComponent},
  {path: 'libro/all', component: LibroListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
