import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegistracionComponent } from './auth/registracion.component';
import { EditarEducacionComponent } from './componentes/experiencia-educacion/editar-educacion.component';
import { EditarExperienciaComponent } from './componentes/experiencia-educacion/editar-experiencia.component';
import { NuevaEducacionComponent } from './componentes/experiencia-educacion/nueva-educacion.component';
import { NuevaExperienciaComponent } from './componentes/experiencia-educacion/nueva-experiencia.component';
import { HomeComponent } from './componentes/home/home.component';
import { ImagenComponent } from './componentes/imagen/imagen.component';
import { MainComponent } from './componentes/main/main.component';
import { EditarPersonaHabilidadComponent } from './componentes/persona-habilidad/editar-persona-habilidad.component';
import { NuevaPersonaHabilidadComponent } from './componentes/persona-habilidad/nueva-persona-habilidad.component';
import { EditarProyectoComponent } from './componentes/proyecto/editar-proyecto.component';
import { NuevoProyectoComponent } from './componentes/proyecto/nuevo-proyecto.component';
import { EditarSobreMiComponent } from './componentes/sobre-mi/editar-sobre-mi.component';
import { EditarUnValorComponent } from './componentes/sobre-mi/editar-un-valor.component';
//import { NuevoUsuarioComponent } from './componentes/usuario/nuevo-usuario.component';
import { GuardService as guard } from './guards/guard.service';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'username/:nombreUsuario', component: MainComponent},
  {path: 'editar_datos_personales', outlet: 'modal', component: EditarSobreMiComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_imagen', outlet: 'modal', component: ImagenComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_valor', outlet: 'modal', component: EditarUnValorComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'nueva_exp_lab', outlet: 'modal', component: NuevaExperienciaComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_exp_lab/:id', outlet: 'modal', component: EditarExperienciaComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'nueva_habilidad', outlet: 'modal', component: NuevaPersonaHabilidadComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_habilidad/:id', outlet: 'modal', component: EditarPersonaHabilidadComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'nueva_educacion', outlet: 'modal', component: NuevaEducacionComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_educacion/:id', outlet: 'modal', component: EditarEducacionComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'nuevo_proyecto', outlet: 'modal', component: NuevoProyectoComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar_proyecto/:id', outlet: 'modal', component: EditarProyectoComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  // login y registracion
  {path: 'login', outlet: 'modal',component: LoginComponent},
  {path: 'registracion', outlet: 'modal', component: RegistracionComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
