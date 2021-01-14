import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Themeservice } from 'src/app/service/theme.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor(
    private themeService: Themeservice
  ) { }

  items: MenuItem[];

  ngOnInit(): void {
    this.themeService.getCurrentmode();
    this.items = [{
      label: 'Options',
      items: [{
        label: 'Update',
        icon: 'pi pi-refresh',
      },
      {
        label: 'Delete',
        icon: 'pi pi-times',
      }
      ]
    },
    {
      label: 'Navigate',
      items: [{
        label: 'Angular Website',
        icon: 'pi pi-external-link',
        url: 'http://angular.io'
      },
      {
        label: 'Router',
        icon: 'pi pi-upload',
        routerLink: '/fileupload'
      }
      ]
    }
    ];
  }

}
