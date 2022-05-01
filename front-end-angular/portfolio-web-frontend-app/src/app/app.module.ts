import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from  '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/header/header.component';
import { FooterComponent } from './componentes/footer/footer.component';
import { SobreMiComponent } from './componentes/sobre-mi/sobre-mi.component';
import { SkillsComponent } from './componentes/skills/skills.component';
import { ProyectosComponent } from './componentes/proyectos/proyectos.component';
import { ExperienciaEducacionComponent } from './componentes/experiencia-educacion/experiencia-educacion.component';
import { SplitStringArrayPipe } from './pipes/split-string-array.pipe';
import { NgApexchartsModule } from "ng-apexcharts";
import { KeyvalueFilterArrayPipe } from './pipes/keyvalue-filter-array.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SobreMiComponent,
    SkillsComponent,
    ProyectosComponent,
    ExperienciaEducacionComponent,
    SplitStringArrayPipe,
    KeyvalueFilterArrayPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgApexchartsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
