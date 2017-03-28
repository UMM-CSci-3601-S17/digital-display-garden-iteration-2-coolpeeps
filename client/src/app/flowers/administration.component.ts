import { Component, OnInit } from '@angular/core';
import {FlowerService} from "./flower.service";

@Component({
    selector: 'administration-component',
    templateUrl: 'administration.component.html'
})
export class AdministrationComponent{


    username: string;
    password: string;
    showConfirmation: boolean;

    constructor(private FlowerService: FlowerService) {}

    setUserPass(user, pass) : void{
        if(user != null && pass != null) {
            this.username = user;
            this.password = pass;
        }
    }

    fileChange(event) {
        this.FlowerService.uploadFile(event);
        this.showConfirmation = true;
    }

}