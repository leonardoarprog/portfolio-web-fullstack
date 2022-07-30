import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonaHabilidadComponent } from './persona-habilidad.component';

describe('PersonaHabilidadComponent', () => {
  let component: PersonaHabilidadComponent;
  let fixture: ComponentFixture<PersonaHabilidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonaHabilidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonaHabilidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
