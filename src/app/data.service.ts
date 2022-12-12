import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
const api = 'http://10.5.49.159:8080/';
@Injectable({
  providedIn: 'root',
})

// export default to home fields are filled found using the default settings in the config file
export class DataService {
  private element1: any = new Array();
  constructor(private http: HttpClient) {}

  getData() {
    return this.http.get(api + 'all').subscribe((value) => {
      console.log('hello : ', value);
      this.element1 = value;
    });
  }
  getElement() {
    return this.element1;
  }
  // DataService constructor to home fields are filled found using the constructor function
  sendData(aNumber: number, cSymbol: string, cName: string, aWeight: number) {
    return this.http.post(api + 'post', {
      atomicNumber: aNumber,
      weight: aWeight,
      symbol: cSymbol,
      name: cName,
    });
  }
}

// if (value != null) {
//   localStorage.setItem('element', JSON.stringify(value));
// }
