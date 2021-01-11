import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { BackendService } from './backend.service';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  canActivate(route: import("@angular/router").ActivatedRouteSnapshot, state: import("@angular/router").RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
    if (this.loggedin()) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }

    //throw new Error("Method not implemented.");
  }

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private tokenService: TokenService,
  ) { }

  loggedin(): boolean {
    let user = this.tokenService.getUser();
    return !(user === null)
  }
}