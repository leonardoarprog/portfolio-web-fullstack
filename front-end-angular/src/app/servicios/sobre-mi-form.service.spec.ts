import { TestBed } from '@angular/core/testing';

import { SobreMiFormService } from './sobre-mi-form.service';

describe('SobreMiFormService', () => {
  let service: SobreMiFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SobreMiFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
