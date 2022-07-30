import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaPersonaHabilidadComponent } from './nueva-persona-habilidad.component';

describe('NuevaPersonaHabilidadComponent', () => {
  let component: NuevaPersonaHabilidadComponent;
  let fixture: ComponentFixture<NuevaPersonaHabilidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevaPersonaHabilidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevaPersonaHabilidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
