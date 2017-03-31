// Imports
import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FlowerComponent } from "./flowers/flower.component";
import { AdministrationComponent } from "./flowers/administration.component";
import { DisplayingDataComponent } from "./flowers/displayingData.component";

// Route Configuration
export const routes: Routes = [
    { path: '', component: FlowerComponent },
    { path: 'flowers', component: FlowerComponent },
    { path: 'admin', component: AdministrationComponent },
    { path: 'data' , component: DisplayingDataComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);