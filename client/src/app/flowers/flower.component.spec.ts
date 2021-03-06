// /*
// /!**
//  * Created by holma198 on 3/31/17.
//  *!/
// /!**
//  * Created by holma198 on 3/28/17.
//  *!/
// import { ComponentFixture, TestBed, async } from "@angular/core/testing";
// import { Flower } from "./flower";
// import { FlowerComponent } from "./flower.component";
// import { FlowerService } from "./flower.service";
// import { Observable } from "rxjs";
// import { PipeModule } from "../../pipe.module";
// import { FormsModule , ReactiveFormsModule} from '@angular/forms';
// import { HttpModule} from "@angular/http";
//
// describe("Flower component", () => {
//
//     let flowerComponent: FlowerComponent;
//     let fixture: ComponentFixture<FlowerComponent>;
//
//
//     let flowerListServiceStub: {
//         getFlowerById: (flowerId: string) => Observable<Flower>
//     };
//
//     beforeEach(() => {
//         // stub UserService for test purposes
//         flowerListServiceStub = {
//             getFlowerById: (flowerId: string) => Observable.of([
//                 {
//                     "_id" : "58b8f2565fbad0fc7a89f700" ,
//                     "commonName" : "Angelonia",
//                     "cultivar" : "Angelface Perfectly Pink",
//                     "source" :"source 1",
//                     "gardenLocation" : "GL1",
//                     "year" : 2016
//                 },
//                 {
//                     "_id" : "58b8f2565fbad0fc7a89f701",
//                     "commonName" : "Angelonia",
//                     "cultivar" : "Angelface Super Blue",
//                     "source" :"source 1",
//                     "gardenLocation" : "GL1",
//                     "year" : 2016
//                 },
//                 {
//                     "_id" : "58b8f2565fbad0fc7a89f701" ,
//                     "commonName" : "Angelonia",
//                     "cultivar" : "Angelface Super Pink",
//                     "source" :"source 1",
//                     "gardenLocation" : "GL1",
//                     "year" : 2016
//                 }
//             ].find(flower => flower._id === flowerId))
//         };
//
//         TestBed.configureTestingModule({
//             imports: [HttpModule],
//             declarations: [ FlowerComponent, FormsModule, ReactiveFormsModule],
//             providers:    [ { provide: FlowerService, useValue: flowerListServiceStub } ]
//         })
//         //FormsModule, ReactiveFormsModule,
//     });
//
//     beforeEach(async(() => {
//         TestBed.compileComponents().then(() => {
//             fixture = TestBed.createComponent(FlowerComponent);
//             flowerComponent = fixture.componentInstance;
//         });
//     }));
//
//     it("can retrieve Angelonia by ID", () => {
//         flowerComponent.setId("58b8f2565fbad0fc7a89f700");
//         expect(flowerComponent.flower).toBeDefined();
//         expect(flowerComponent.flower.commonName).toBe("Angelonia");
//         expect(flowerComponent.flower.cultivar).toBe("Angelface Perfectly Pink");
//     });
//
//     it("returns undefined for Spruce", () => {
//         flowerComponent.setId("Spruce");
//         expect(flowerComponent.flower).not.toBeDefined();
//     });
//
// });*/
