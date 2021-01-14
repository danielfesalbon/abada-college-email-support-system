import { TestBed } from '@angular/core/testing';

import { Themeservice } from './theme.service';

describe('ThemeService', () => {
  let service: Themeservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Themeservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
