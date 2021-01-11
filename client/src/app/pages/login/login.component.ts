import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private tokenService: TokenService,
  ) { }

  ngOnInit(): void {
    this.onsign();
    this.credentials = {};
  }

  tosignin: boolean;
  toregister: boolean;
  credentials: any;
  user: any;
  confpassword: any;


  onreg() {
    this.toregister = true;
    this.tosignin = false;
    this.user = {};
    this.confpassword = '';
  }

  onsign() {
    this.toregister = false;
    this.tosignin = true;
  }


  usersignin() {
    this.service.userlogin(this.credentials.username, this.credentials.password).subscribe(res => {
      if (res.flag == 'Accepted') {
        this.tokenService.storeToken(res.token);
        this.tokenService.storeUser(res.username);
        this.router.navigate(['/main']);
      }
    }, err => {
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.error.message });
    });
  }


  registernew() {
    this.service.registeruser(this.user).subscribe(res => {
      if (res.flag == "success") {
        this.user = {};
        this.confpassword = '';
        this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
      }
    }, err => {
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: 'Problem creating user account.' });
    });
  }

  forgotpassword() {

  }

  validateresetpass() {

  }

  resetpassword() {

  }

}
