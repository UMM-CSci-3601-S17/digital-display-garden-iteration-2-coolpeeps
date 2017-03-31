import { Component, OnInit } from '@angular/core';
import {FlowerService} from "./flower.service";
import { FilterBy } from "./filter.pipe";
import { Flower } from "./flower";

@Component({
    selector: 'displayingData-component',
    templateUrl: 'displayingData.component.html',
    providers: [FilterBy],
})
export class DisplayingDataComponent{


    username: string;
    password: string;
    showConfirmation: boolean;
    public flowers: Flower[];

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