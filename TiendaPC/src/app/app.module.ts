import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductosComponent } from './Components/productos/productos.component';
import { UsuariosComponent } from './Components/usuarios/usuarios.component';
import { CategoriasComponent } from './Components/categorias/categorias.component';
import { PedidosComponent } from './Components/pedidos/pedidos.component';
import { DetallePedidosComponent } from './Components/detalle-pedidos/detalle-pedidos.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductosComponent,
    UsuariosComponent,
    CategoriasComponent,
    PedidosComponent,
    DetallePedidosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
