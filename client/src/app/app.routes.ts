// Imports
import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {FlowerComponent} from "./flowers/flower.component"

// Route Configuration
export const routes: Routes = [
    { path: '', component: FlowerComponent },
    { path: 'flowers', component: FlowerComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);