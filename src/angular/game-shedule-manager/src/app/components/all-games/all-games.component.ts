import { Component, OnInit } from '@angular/core';
import { GameData } from '../../models/game-data'
import { GameTimesService } from '../../services/game-times.service'

@Component({
  selector: 'app-all-games',
  templateUrl: './all-games.component.html',
  styleUrls: ['./all-games.component.css']
})
export class AllGamesComponent implements OnInit {

  dates: GameData[] = [];
  
  constructor(private gameTimesService: GameTimesService) { }

  ngOnInit(): void {
    this.gameTimesService.findAll().subscribe( data => {
      console.log("Test");
      this.dates = data;
      console.log(this.dates);
    })
  }

}
