import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
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

  ) { }

  usermodal: boolean;
  user: any;
  position: string;

  ngOnInit(): void {
    this.position = "bottom";
    this.usermodal = false;
    this.user = {};
    this.getuser();
  }


  showprofile() {
    this.usermodal = true;
  }



  onlogout() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to log out?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //this.tokenService.destroy();
        //this.router.navigate(['/login']);
        this.logout();
      }
    });
  }

  getuser() {
    this.service.getusers().subscribe(res => {
      let list: any[] = res;
      this.user = list.find(i => { return i.username == this.tokenService.getUser(); });
    })
  }


  logout() {
    this.service.userlogout(this.tokenService.getUser()).subscribe(res => {
      if (res.flag == "success") {
        this.tokenService.destroy();
        this.router.navigate(['/login']);
      }
    }, err => {
      console.log(err);
    });
  }

}
