import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;
  loading = false;
  error!: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authentication: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.authentication
      .register(
        this.registerForm.controls['username'].value,
        this.registerForm.controls['password'].value
      )
      .subscribe(
        (data: any) => {
          this.router.navigate(['/login']);
        },
        (error: any) => {
          this.error = error;
          this.loading = false;
          alert(error['error']['error']);
        }
      );
  }
}
