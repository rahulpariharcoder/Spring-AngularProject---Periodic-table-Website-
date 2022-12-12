import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root',
})
export class LoginGuard implements CanActivate {
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}
  canActivate(): boolean {
    if (this.authService.isUserLoggedIn()) {
      return true;
    } else if (sessionStorage.getItem('user') === 'true') {
      this.authService.isloggedIn = true;
      this.router.navigate(['/homepage']);
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
