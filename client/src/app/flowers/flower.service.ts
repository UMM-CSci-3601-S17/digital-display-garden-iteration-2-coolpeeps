import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs";
import { Flower } from "./flower";

@Injectable()
export class FlowerService {
    private plantUrl: string = API_URL + "plant";
    constructor(private http:Http) { }

    // getPlant(): Observable<Plant[]> {
    //     return this.http.request(this.plantUrl).map(res => res.json());
    // }

    getFlowerById(id: string): Observable<Flower> {
        return this.http.request(this.plantUrl + "/" + id).map(res => res.json());
    }

    rateFlower(id: string, rating: string): Observable<Boolean> {
        return this.http.request(this.plantUrl + "/" + id + "/" + rating).map(res => res.json());
    }

    commentFlower(id: string, comment: string): Observable<Boolean> {
        let returnObject = {
            plantId: id,
            comment: comment
        };
        return this.http.post(this.plantUrl + "/" + "leaveComment", JSON.stringify(returnObject)).map(res => res.json());
    }

    uploadFile(event): boolean{
        console.log("Uploading file");
        let fileList: FileList = event.target.files;
        if(fileList.length > 0){
            console.log("sup");
            this.http.post(this.plantUrl, event);
            return true;
        }

    }
}




































// @Injectable()
// export class FlowerService {
//     private bedUrl: string = API_URL + "beds";
//     private flowerUrl: string = API_URL + "flowers";
//     constructor(private http:Http) { }
//
//     getFlower(garden: string, cultivar:string): Observable<any> {
//         return this.http.request(this.flowerUrl + "?gardenLocation=" + garden + "&cultivar=" + cultivar).map(res => res.json());
//     }
//
//     getFlowerNames(garden: string): Observable<Flower[]> {
//         return this.http.request(this.flowerUrl + "?gardenLocation=" + garden).map(res => res.json());
//     }
//
//     getBedNames(): Observable<any> {
//         return this.http.request(this.bedUrl).map(res => res.json());
//     }
//
//     ratePlant(id: string, rating: string): Observable<Boolean> {
//         return this.http.request(this.flowerUrl + "/" + id + "/" + rating).map(res => res.json());
//     }
// }