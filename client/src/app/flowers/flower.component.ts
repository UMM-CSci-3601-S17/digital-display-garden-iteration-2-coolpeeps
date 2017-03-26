import { FlowerService } from "./flower.service";
import { Flower } from "./flower";
import {ActivatedRoute, Params} from '@angular/router';
import { Component, Input, Output, EventEmitter, ElementRef, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import 'rxjs/add/operator/switchMap';
import { Feedback } from './feedback';



@Component({
    templateUrl: 'flower.component.html',
    /*styles: ['button.responseButton { border: 2px solid #4CAF50; width: 49%; height: 60px}',
        'span.glyphicon {font-size: 40px;}', 'input.commentBox {width: 99%; font-size: 200%; height: 80px;border: 2px solid #4CAF50;}',
        'button.submitButton {width: 60%; height:60px; border: 2px solid #4CAF50; font-size: 40px;' +
        ' display: block; margin: auto;}', 'div.fullComment {width: 99%; margin: auto;}', 'hr.flowerPageHR {border: 1px solid #4CAF50;}',
        'li.active {font-size: 30px; padding-bottom: 15px; border-bottom: solid green;}'],*/
    selector: 'my-app',
})

export class FlowerComponent implements OnInit {
    private rated: Boolean = false;
    private commented: Boolean = false;
    private currentQuery: string = "";
    private flower: Flower = {_id: "", commonName: "", cultivar: "", gardenLocation: ""};
    constructor(private flowerService: FlowerService,
                private route: ActivatedRoute,
    ){ }


    ngOnInit() :void{
        this.route.params
            .switchMap((params:Params) => this.flowerService.getFlowerById(params['_id']))
            .subscribe(flower => this.flower = flower);
    }

    private rate(rating: string): void {
        if(!this.rated){
            this.flowerService.rateFlower(this.flower["_id"]["$oid"], rating)
                .subscribe(succeeded => this.rated = succeeded);
            console.log(rating);
        }
    }

    private comment(comment: string): void {
        if(!this.commented){
            console.log(comment);
            if(comment != null) {
                this.flowerService.commentFlower(this.flower["_id"]["$oid"], comment)
                    .subscribe(succeeded => this.commented = succeeded);
            }
        }


    }
}

























































//
// // Component class
// export class FlowerComponent implements OnInit{
//     public bedNames: string[];
//     public flowerNames: string[];
//     public currentBed: string;
//     public currentFlower: string;
//     public flower: Flower;
//     public text: string;
//     public myForm: FormGroup; // our model driven formflowerUrl
//     public submitted: boolean; // keep track on whether form is submitted
//     public events: any[] = []; // use later to display form changes
//     public rated: boolean = false;
//
//     constructor(private flowerService: FlowerService,
//                 private route: ActivatedRoute,
//     ){ }
//
//     private parseFlowers(flowers: Flower[]) {
//         var tempNames: string[] = [];
//         for (let each of flowers) {
//             tempNames.push(each.cultivar);
//         }
//         return tempNames;
//
//     }
//
//     ngOnInit(): void {
//         this.flowerService.getBedNames().subscribe(
//             beds => this.bedNames = Object.keys(beds),
//             err => {
//                 console.log(err);
//             }
//         );
        // this.myForm = this._fb.group({
        //     comment: ['', [<any>Validators.required]],
        //     like: [0, [<any>Validators.required]],
        //     dislike: [0, [<any>Validators.required]],
        // });
//     }
//
//     onSelectBed(currentBed: string): void {
//         this.currentBed = currentBed;
//         this.flowerService.getFlowerNames(currentBed).subscribe(
//             flowers => this.flowerNames = this.parseFlowers(flowers),
//             err => {
//                 console.log(err);
//             }
//         );
//     }
//
//     onSelectFlower(currentFlower: string): void {
//         this.currentFlower = currentFlower;
//         this.flowerService.getFlower(this.currentBed, currentFlower).subscribe(
//             flower => this.flower = flower[0],
//             err => {
//                 console.log(err);
//             }
//         );
//     }
//
//     save(model: Feedback, isValid: boolean) {
//         this.submitted = true; // set form submit to true
//
//         // check if model is valid
//         // if valid, call API to save customer
//         console.log(model, isValid);
//     }
//     private rate(rating: string): void {
//         if(!this.rated){
//             this.flowerService.rateFlower(this.flower["_id"]["$oid"], rating)
//                 .subscribe(succeeded => this.rated = succeeded);
//         }
//     }
// }
//
//
//
