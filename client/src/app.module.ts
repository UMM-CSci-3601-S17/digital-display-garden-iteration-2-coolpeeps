import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule, JsonpModule } from '@angular/http';

import { AppComponent }         from './app/app.component';
import { NavbarComponent } from './app/navbar/navbar.component';
import { HomeComponent} from './app/home/home.component';
import { HomeService } from './app/home/home.service';
import { routing } from './app/app.routes';
import { FormsModule } from '@angular/forms';

import { PipeModule } from './pipe.module';
import {FlowerComponent} from "./app/flowers/flower.component";
import { FlowerService } from './app/flowers/flower.service';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        JsonpModule,
        routing,
        FormsModule,
        PipeModule,
        ReactiveFormsModule,
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        NavbarComponent,
        FlowerComponent,

    ],
    providers: [ HomeService, FlowerService],
    bootstrap: [ AppComponent ]
})

export class AppModule {}
