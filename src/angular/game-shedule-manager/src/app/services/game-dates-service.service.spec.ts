import { TestBed } from '@angular/core/testing';

import { GameDatesServiceService } from './game-dates-service.service';

describe('GameDatesServiceService', () => {
  let service: GameDatesServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameDatesServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
