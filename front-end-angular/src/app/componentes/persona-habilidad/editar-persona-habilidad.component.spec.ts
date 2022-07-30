import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPersonaHabilidadComponent } from './editar-persona-habilidad.component';

describe('EditarPersonaHabilidadComponent', () => {
  let component: EditarPersonaHabilidadComponent;
  let fixture: ComponentFixture<EditarPersonaHabilidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarPersonaHabilidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarPersonaHabilidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
