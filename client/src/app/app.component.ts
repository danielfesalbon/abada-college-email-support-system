import { Component, ElementRef, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { Themeservice } from './service/theme.service';
import { TokenService } from './service/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(
    private elRef: ElementRef,
    private primengConfig: PrimeNGConfig,
    private tokenService: TokenService,
    private themeService: Themeservice,
  ) {
  }

  ngOnInit() {
    this.primengConfig.ripple = true;
    this.setMode();
  }

  setMode() {
    if (sessionStorage.getItem('mode') == undefined || sessionStorage.getItem('mode') == null) {
      sessionStorage.setItem('mode', 'light');
    }
  }

}
