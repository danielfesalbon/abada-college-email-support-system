import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
import { Themeservice } from 'src/app/service/theme.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(
    private service: BackendService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private router: Router,
    private tokenService: TokenService,
    private themeService: Themeservice
  ) { }

  usermodal: boolean;
  user: any;
  position: string;
  themeicon: string;
  mode: string;
  confpassword: string;

  ngOnInit(): void {
    this.confpassword = '';
    this.mode = this.themeService.getCurrentmode();
    if (this.mode == 'dark') {
      this.themeicon = 'pi pi-sun';
    } else {
      this.themeicon = 'pi pi-moon';
    }
    this.position = "bottom";
    this.usermodal = false;
    this.user = {};
    this.getuser();
  }


  showprofile() {
    this.usermodal = true;
    this.confpassword = '';
    this.user.password = '';
  }

  switchTheme() {
    this.mode = this.themeService.switch();
    if (this.mode == 'dark') {
      this.themeicon = 'pi pi-sun';
    } else {
      this.themeicon = 'pi pi-moon';
    }
  }

  onlogout() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to log out?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.logout();
      }
    });
  }

  getuser() {
    this.service.getusers().subscribe(res => {
      let list: any[] = res;
      this.user = list.find(i => { return i.username == this.tokenService.getUser(); });
    }, err => {
      this.tokenService.checkSession(err);
    })
  }

  changepassword() {
    this.confirmationService.confirm({
      message: 'Update password',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.service.changepassword(this.user).subscribe(res => {
          if (res.flag == "success") {
            this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
            this.ngOnInit();
          }
        }, err => {
          this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.message });
          this.tokenService.checkSession(err);
        })
      }
    });
  }


  logout() {
    this.service.userlogout(this.tokenService.getUser()).subscribe(res => {
      if (res.flag == "success") {
        this.tokenService.destroy();
        this.router.navigate(['/login']);
      }
    }, err => {
      this.tokenService.checkSession(err);
    });
  }

}
