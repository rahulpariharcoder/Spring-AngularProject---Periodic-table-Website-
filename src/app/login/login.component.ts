import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;

  constructor(
    private router: Router,
    private authentication: AuthenticationService,
    private formBuild: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuild.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    //True if all the fields are filled
    if (this.submitted) {
    }
    this.authentication.login(
      this.loginForm.controls['username'].value,
      this.loginForm.controls['password'].value
    );
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
