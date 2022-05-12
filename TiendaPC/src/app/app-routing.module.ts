import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetallePedidos } from './Classes/detalle-pedidos';
import { CategoriasComponent } from './Components/categorias/categorias.component';
import { PedidosComponent } from './Components/pedidos/pedidos.component';
import { ProductosComponent } from './Components/productos/productos.component';
import { UsuariosComponent } from './Components/usuarios/usuarios.component';

const routes: Routes = [
  { path: 'categorias/all', component: CategoriasComponent },
  { path: 'detallePedidos/all', component: DetallePedidos },
  { path: 'pedidos/all', component: PedidosComponent },
  { path: 'productos/all', component: ProductosComponent },
  { path: 'usuarios/all', component: UsuariosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
