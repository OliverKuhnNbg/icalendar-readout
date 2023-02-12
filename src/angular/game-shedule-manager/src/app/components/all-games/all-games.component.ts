import { Component, OnInit } from '@angular/core';
import { GameData } from '../../models/game-data'
import { GameTimesService } from '../../services/game-times.service'

@Component({
  selector: 'app-all-games',
  templateUrl: './all-games.component.html',
  styleUrls: ['./all-games.component.scss']
})
export class AllGamesComponent implements OnInit {

  dates: GameData[] = [];

  constructor(private gameTimesService: GameTimesService) {

  }

  ngOnInit(): void {
    this.gameTimesService.findAll().subscribe( data => {
      this.dates = data;
    })
  }

  formatApiDate(baseString: string): string {
    let point = ".";
    let formattedString =  baseString.split("T")[0];

    formattedString = formattedString.split("-")[2] + point +  formattedString.split("-")[1] + point + formattedString.split("-")[0];
    return formattedString;
  }

  formatApiTime(baseString: string): string {
    let formattedTime =  baseString.split("T")[1];

    formattedTime = formattedTime.split(":")[0] + ":" + formattedTime.split(":")[1] + " Uhr"; 
    return formattedTime;
  }
}
