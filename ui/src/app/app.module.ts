import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AgGridModule} from "ag-grid-angular/main";
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DisplayComponent } from './display/display.component';
import { Routes } from '@angular/router';
import {RegistrationComponent} from './register/register.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {UserService} from './user.service';



const appRoutes: Routes = [
  {path:'',component:HomeComponent},
  {path:'home',component:HomeComponent},
  {path:'display',component:DisplayComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DisplayComponent,
    RegistrationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AgGridModule.withComponents([]),
    AppRoutingModule,HttpClientModule,
    FormsModule,ReactiveFormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
