import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
// import { Plant } from './plant';
import { Observable } from "rxjs";

@Injectable()
export class PlantListService {
    private plantUrl: string = API_URL + "flowers";
    constructor(private http:Http) { }

/*
    getPlants(): Observable<Plant[]> {
        return this.http.request(this.plantUrl).map(res => res.json());
    }
*/

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