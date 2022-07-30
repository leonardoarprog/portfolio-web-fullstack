import { TestBed } from '@angular/core/testing';

import { DataCompartidaService } from './data-compartida.service';

describe('DataCompartidaService', () => {
  let service: DataCompartidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataCompartidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
