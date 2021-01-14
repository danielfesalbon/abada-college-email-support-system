import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Themeservice {

  constructor() { }


  switch() {
    let theme = <HTMLLinkElement>document.getElementById('stylelink');
    let body = <HTMLElement>document.getElementById('body');
    let header = <HTMLElement>document.getElementById('header');
    let page = <HTMLElement>document.getElementById('page');
    let menubar = <HTMLElement>document.getElementById('ul-list');
    let setting = <HTMLElement>document.getElementById('setting');
    let nav = <HTMLElement>document.getElementById('loginnav');
    let loginform = <HTMLElement>document.getElementById('loginform');

    if (sessionStorage.getItem('mode') == 'light') {
      sessionStorage.setItem('mode', 'dark');
      if (body != null) {
        body.style.backgroundColor = 'rgb(32,38,46)';
        body.style.color = 'white';
      }
      if (menubar != null) {
        menubar.style.backgroundColor = 'rgb(42,50,61)';
        menubar.childNodes.forEach(e => {
          let element = <HTMLElement>e;
          element.childNodes.forEach(i => {
            let elementch = <HTMLElement>i;
            if (elementch.classList.length == 0) {
              elementch.style.color = 'white';
            }
          });
        });
      }
      if (nav != null) { nav.style.backgroundColor = 'rgb(42,50,61)'; }
      if (page != null) { page.style.backgroundColor = 'rgb(42,50,61)'; }
      if (setting != null) { setting.style.backgroundColor = 'rgb(194,152,216)'; }
      if (theme != null) { theme.href = window.origin + '/assets/dark.css'; }
      if (header != null) { header.style.backgroundColor = 'rgb(32,38,46)'; }
      return 'light';
    } else {
      window.sessionStorage.setItem('mode', 'light');
      if (body != null) {
        body.style.backgroundColor = 'rgb(245,245,245)';
        body.style.color = 'black';
      }
      if (menubar != null) {
        menubar.style.backgroundColor = 'white';
        menubar.childNodes.forEach(e => {
          let element = <HTMLElement>e;
          element.childNodes.forEach(i => {
            let elementch = <HTMLElement>i;
            if (elementch.classList.length == 0) {
              elementch.style.color = 'black';
            }
          });
        });
      }
      if (page != null) { page.style.backgroundColor = 'white'; }
      if (nav != null) { nav.style.backgroundColor = 'white'; }
      if (setting != null) { setting.style.backgroundColor = '#883cae'; }
      if (theme != null) { theme.href = window.origin + '/assets/light.css'; }
      if (header != null) { header.style.backgroundColor = 'rgb(245,245,245)'; }
      return 'dark';
    }
  }

  getCurrentmode() {
    let theme = <HTMLLinkElement>document.getElementById('stylelink');
    let body = <HTMLElement>document.getElementById('body');
    let header = <HTMLElement>document.getElementById('header');
    let page = <HTMLElement>document.getElementById('page');
    let menubar = <HTMLElement>document.getElementById('ul-list');
    let setting = <HTMLElement>document.getElementById('setting');
    let nav = <HTMLElement>document.getElementById('loginnav');
    let loginform = <HTMLElement>document.getElementById('loginform');

    if (sessionStorage.getItem('mode') == 'light') {
      if (body != null) {
        body.style.backgroundColor = 'rgb(245,245,245)';
        body.style.color = 'black';
      }
      if (menubar != null) { menubar.style.backgroundColor = 'white'; }
      if (page != null) { page.style.backgroundColor = 'white'; }
      if (theme != null) { theme.href = window.origin + '/assets/light.css'; }
      if (header != null) { header.style.backgroundColor = 'rgb(245,245,245)'; }
      if (nav != null) { nav.style.backgroundColor = 'white'; }
      if (loginform != null) { loginform.style.backgroundColor = 'whitesmoke'; }
      if (setting != null) { setting.style.backgroundColor = '#883cae'; }
      return 'dark';
    } else {
      if (body != null) {
        body.style.backgroundColor = 'rgb(32,38,46)';
        body.style.color = 'white';
      }
      if (menubar != null) { menubar.style.backgroundColor = 'rgb(42,50,61)'; }
      if (header != null) { header.style.backgroundColor = 'rgb(32,38,46)'; }
      if (nav != null) { nav.style.backgroundColor = 'rgb(42,50,61)'; }
      if (loginform != null) { loginform.style.backgroundColor = 'rgb(42,50,61)'; }
      if (theme != null) { theme.href = window.origin + '/assets/dark.css'; }
      if (page != null) { page.style.backgroundColor = 'rgb(42,50,61)'; }
      if (setting != null) { setting.style.backgroundColor = 'rgb(194,152,216)'; }
      return 'light';
    }
  }
}
