import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from  '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/header/header.component';
import { FooterComponent } from './componentes/footer/footer.component';
import { SobreMiComponent } from './componentes/sobre-mi/sobre-mi.component';
import { ExperienciaEducacionComponent } from './componentes/experiencia-educacion/experiencia-educacion.component';
import { SplitStringArrayPipe } from './pipes/split-string-array.pipe';
import { NgApexchartsModule } from "ng-apexcharts";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { KeyvalueFilterArrayPipe } from './pipes/keyvalue-filter-array.pipe';
import { HomeComponent } from './componentes/home/home.component';
import { MainComponent } from './componentes/main/main.component';
import { LoginComponent } from './auth/login.component';
import { interceptorProvider } from './interceptors/portfolio-interceptor.service';
import { RegistracionComponent } from './auth/registracion.component';
import { EditarSobreMiComponent } from './componentes/sobre-mi/editar-sobre-mi.component';
import { NuevaExperienciaComponent } from './componentes/experiencia-educacion/nueva-experiencia.component';
import { EditarExperienciaComponent } from './componentes/experiencia-educacion/editar-experiencia.component';
import { PersonaHabilidadComponent } from './componentes/persona-habilidad/persona-habilidad.component';
import { NuevaPersonaHabilidadComponent } from './componentes/persona-habilidad/nueva-persona-habilidad.component';
import { ModalComponent } from './componentes/modal/modal.component';
import { TokenService } from './servicios/token.service';
import { ImagenComponent } from './componentes/imagen/imagen.component';
import { SafeHtmlPipe } from './pipes/safe-html.pipe';
import { EditarUnValorComponent } from './componentes/sobre-mi/editar-un-valor.component';
import { FirstElementPipe } from './pipes/first-element.pipe';
import { LastElementPipe } from './pipes/last-element.pipe';
import { DeletePropertyPipe } from './pipes/delete-property.pipe';
import { MapFieldPipe } from './pipes/map-field.pipe';
import { ArrayToObjPipe } from './pipes/array-to-obj.pipe';
import { EditarPersonaHabilidadComponent } from './componentes/persona-habilidad/editar-persona-habilidad.component';
import { NuevaEducacionComponent } from './componentes/experiencia-educacion/nueva-educacion.component';
import { EditarEducacionComponent } from './componentes/experiencia-educacion/editar-educacion.component';
import { ProyectoComponent } from './componentes/proyecto/proyecto.component';
import { NuevoProyectoComponent } from './componentes/proyecto/nuevo-proyecto.component';
import { EditarProyectoComponent } from './componentes/proyecto/editar-proyecto.component';




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SobreMiComponent,
    ExperienciaEducacionComponent,
    SplitStringArrayPipe,
    KeyvalueFilterArrayPipe,
    HomeComponent,
    MainComponent,
    LoginComponent,
    RegistracionComponent,
    EditarSobreMiComponent,
    NuevaExperienciaComponent,
    EditarExperienciaComponent,
    PersonaHabilidadComponent,
    NuevaPersonaHabilidadComponent,
    ModalComponent,
    ImagenComponent,
    SafeHtmlPipe,
    EditarUnValorComponent,
    FirstElementPipe,
    LastElementPipe,
    DeletePropertyPipe,
    MapFieldPipe,
    ArrayToObjPipe,
    EditarPersonaHabilidadComponent,
    NuevaEducacionComponent,
    EditarEducacionComponent,
    ProyectoComponent,
    NuevoProyectoComponent,
    EditarProyectoComponent,
    



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgApexchartsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [interceptorProvider, TokenService],
  bootstrap: [AppComponent]
})
export class AppModule { }
