import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private tokenService: TokenService,
    private router: Router
  ) { }


  properties: any;

  ngOnInit(): void {
    this.properties = {};
    this.service.getproperties().subscribe(res => {
      this.properties = res;
    }, err => {
      this.tokenService.checkSession(err);
    });
  }

}
