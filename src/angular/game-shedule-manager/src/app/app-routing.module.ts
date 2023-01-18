import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TestComponentComponent } from './components/test-component/test-component.component';
import { AllGamesComponent } from './components/all-games/all-games.component';

const routes: Routes = [
  { path: 'users',  component: TestComponentComponent},
  { path: 'all-games',  component: AllGamesComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
