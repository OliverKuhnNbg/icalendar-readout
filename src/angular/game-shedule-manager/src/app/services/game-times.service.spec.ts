import { TestBed } from '@angular/core/testing';

import { GameTimesService } from './game-times.service';

describe('GameTimesService', () => {
  let service: GameTimesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameTimesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
