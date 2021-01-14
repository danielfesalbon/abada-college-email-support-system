import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-useraccount',
  templateUrl: './useraccount.component.html',
  styleUrls: ['./useraccount.component.css']
})
export class UseraccountComponent implements OnInit {

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private tokenService: TokenService
  ) { }

  users: any[];
  usermodal: boolean;
  user: any;

  ngOnInit(): void {
    this.user = {};
    this.usermodal = false;
    this.getusers();
  }



  viewuser(data) {
    this.user = data;
    data.bday != null && data.bday != undefined ? this.user.bday = new Date(data.bday) : this.user.bday = null;
    this.usermodal = true;
  }


  getusers() {
    this.service.getusers().subscribe(res => {
      this.users = res;
    }, err => {
      this.tokenService.checkSession(err);
    });
  }


  getstatus(status) {
    if (status) {
      return 'Yes';
    } else {
      return 'No';
    }
  }

  saveuser() {
    this.confirmationService.confirm({
      message: 'Save user details.',
      accept: () => {
        this.service.saveuser(this.user).subscribe(res => {
          if (res.flag == "success") {
            this.getusers();
            this.usermodal = false;
            this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
          }
        }, err => {
          this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.message });
          this.tokenService.checkSession(err);
        });
      }
    });
  }


  disableaccount(user) {
    let m = '';
    if (user.disabled) {
      m = 'Enable user.';
    } else {
      m = 'Disable user.';
    }
    this.confirmationService.confirm({
      message: m,
      accept: () => {
        if (user.disabled) {
          user.disabled = false;
        } else {
          user.disabled = true;
        }
        this.service.saveuser(user).subscribe(res => {
          if (res.flag == "success") {
            this.getusers();
            this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
          }
        }, err => {
          this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.message });
          this.tokenService.checkSession(err);
        });
      }
    });
  }


  deleteuser(user) {
    this.confirmationService.confirm({
      message: "Delete user",
      accept: () => {
        this.service.deleteuser(user.userid).subscribe(res => {
          if (res.flag == "success") {
            this.getusers();
            this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
          }
        }, err => {
          this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.message });
          this.tokenService.checkSession(err);
        });
      }
    });
  }





}
