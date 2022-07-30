import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarUnValorComponent } from './editar-un-valor.component';

describe('EditarUnValorComponent', () => {
  let component: EditarUnValorComponent;
  let fixture: ComponentFixture<EditarUnValorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarUnValorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarUnValorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
