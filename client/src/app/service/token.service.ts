import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }


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
}
