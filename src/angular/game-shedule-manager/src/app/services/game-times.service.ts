import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GameData } from '../models/game-data';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameTimesService {

  	private gamesUrl: string;
  	
	constructor(private httpClient: HttpClient) {
		this.gamesUrl = 'http://localhost:8083/get-all-game-dates';
	}
	
	public findAll(): Observable<GameData[]> {
		return this.httpClient.get<GameData[]>(this.gamesUrl);
	}
}
