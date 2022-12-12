import { Component, DoCheck, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit, DoCheck {
  title = 'periodicTableApp';

  element!: any;
  addData!: FormGroup;
  submitted = false;
  anumberpattern = '^[0-9]{1,2}$';
  csymbolpattern = '^[A-Z][a-z]?$';
  cnamepattern = '^[A-Z][a-z]+$';
  aweigthpattern = '^[0-9][0-9]*(?:.[0-9]{1,4})?$';

  constructor(
    private ser: DataService,
    private formBuild: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addData = this.formBuild.group({
      atomicNumber: [
        null,
        [Validators.required, Validators.pattern(this.anumberpattern)],
      ],
      chemicalSymbol: [
        null,
        [Validators.required, Validators.pattern(this.csymbolpattern)],
      ],
      chemicalName: [
        null,
        [Validators.required, Validators.pattern(this.cnamepattern)],
      ],
      atomicWeight: [
        null,
        [Validators.required, Validators.pattern(this.aweigthpattern)],
      ],
    });
    this.ser.getData();

    // this.element = JSON.parse(localStorage.getItem('element') || '[]');
    // console.log(this.element);
  }

  ngDoCheck(): void {
    this.element = this.ser.getElement();
  }
  get $elementNumber() {
    return this.element.length;
  }

  sorted(data: Array<any>) {
    return data.sort((a, b) => a['atomicNumber'] - b['atomicNumber']);
  }
  logout() {
    sessionStorage.removeItem('user');
    return this.router.navigate(['/login']);
  }
  add() {
    this.submitted = true;
    this.ser
      .sendData(
        this.addData.controls['atomicNumber'].value,
        this.addData.controls['chemicalSymbol'].value,
        this.addData.controls['chemicalName'].value,
        this.addData.controls['atomicWeight'].value
      )
      .subscribe(
        (result) => {
          console.log(result);
          if (typeof result === 'object') {
            this.element.push({
              atomicNumber: this.addData.controls['atomicNumber'].value,
              symbol: this.addData.controls['chemicalSymbol'].value,
              name: this.addData.controls['chemicalName'].value,
              weight: this.addData.controls['atomicWeight'].value,
            });
            // localStorage.setItem('element', JSON.stringify(this.element));
          }
        },
        (error) => {
          if (error['status'] === 400) {
            alert(error['error']['error']);
          } else if (error['status'] === 404) {
            console.log(error);
            alert(error['error']['error']);
          } else if (error['status'] === 409) {
            console.log(error);
            alert(error['error']['error']);
          } else {
          }
        }
      );
  }
}
