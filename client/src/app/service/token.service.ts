import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(
    private confirmationService: ConfirmationService,
    private router: Router
  ) { }


  public storeToken(token: string) {
    localStorage.removeItem('token');
    localStorage.setItem('token', token);
  }

  public getToken(): string | null {
    return localStorage.getItem('token');
  }

  public storeUser(user: any) {
    localStorage.removeItem('user');
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUser() {
    const user = localStorage.getItem('user');
    if (user) {
      return JSON.parse(user);
    }
    return null
  }

  destroy(): void {
    localStorage.clear();
  }

  checkSession(err) {
    if (err.error.status == 401) {
      this.confirmationService.confirm({
        icon: 'pi pi-exclamation-triangle',
        acceptLabel: 'Okay',
        key: 'sessiondlg',
        message: 'Session Unavailable. Please try again',
        accept: () => {
          this.destroy();
          this.router.navigate(['/login']);
        },
        reject: () => {
          this.destroy();
          this.router.navigate(['/login']);
        }
      });

    }
  }

}
