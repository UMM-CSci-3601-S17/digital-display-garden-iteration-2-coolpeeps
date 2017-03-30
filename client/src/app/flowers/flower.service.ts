import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers} from '@angular/http';
import { Observable } from "rxjs";
import { Flower } from "./flower";

@Injectable()
export class FlowerService {
    private bedUrl: string = API_URL + "beds";
    private flowerUrl: string = API_URL + "flowers";
    private uploadUrl: string = API_URL + "flowers/upload";
    constructor(private http:Http) { }

    getFlower(garden: string, cultivar:string): Observable<any> {
        return this.http.request(this.flowerUrl + "?gardenLocation=" + garden + "&cultivar=" + cultivar).map(res => res.json());
    }

    getFlowerNames(garden: string): Observable<Flower[]> {
        return this.http.request(this.flowerUrl + "?gardenLocation=" + garden).map(res => res.json());
    }

    getBedNames(): Observable<any> {
        return this.http.request(this.bedUrl).map(res => res.json());
    }

    uploadFile(event) {
        let files = event.target.files;
        if (files.length > 0) {
            let formData: FormData = new FormData();
            for (let file of files) {
                formData.append('files', file, file.name);
            }
            console.log(formData);
            let headers = new Headers();
            headers.set('Accept', 'application/json');
            let options = new RequestOptions({ headers: headers });
            this.http.post(this.uploadUrl, formData, options)
                .map(res => res.json())
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        // Consume Files
                        // ..
                        console.log('uploaded and processed files');
                    },
                    error => console.log(error),
                );
            console.log("file sent");
        }
    }

}