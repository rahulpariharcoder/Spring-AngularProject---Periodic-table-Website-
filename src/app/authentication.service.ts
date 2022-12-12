import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
const api = 'http://10.5.49.159:8080/';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  isloggedIn = false;

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string) {
    return this.http
      .post(api + 'login', {
        userName: username,
        password: password,
      })
      .subscribe(
        () => {
          let user = 'true';
          sessionStorage.setItem('user', user);
          this.isloggedIn = true;
          this.router.navigate(['/homepage']);
        },
        (error) => {
          if (error['status'] === 400) {
            alert(error['error']['error']);
          } else if (error['status'] === 404) {
            console.log(error);
            alert(error['error']['error']);
          } else {
            alert('User Not Found');
          }
        }
      );
  }

  isUserLoggedIn(): boolean {
    console.log('rahul', this.isloggedIn);
    return this.isloggedIn;
  }

  register(username: string, password: string) {
    return this.http.post(api + 'register', {
      userName: username,
      password: password,
    });
    // .subscribe(() => {
    //   alert('value');
    // });
  }
}
