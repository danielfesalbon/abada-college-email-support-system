import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BackendService {


  servicelink = environment.rest_url;

  constructor(private http: HttpClient) { }


  userlogin(username, password) {
    return this.http.post<any>(this.servicelink + '/authenticate/user', { username: username, password: password });
  }

  getusers() {
    return this.http.get<any>(this.servicelink + '/user/list');
  }

  saveuser(user) {
    return this.http.post<any>(this.servicelink + '/user/save', user);
  }

  deleteuser(id) {
    return this.http.delete<any>(this.servicelink + '/user/delete/' + id)
  }

  sendemail(req) {
    return this.http.post<any>(this.servicelink + '/email/send', req);
  }

  getproperties() {
    return this.http.get<any>(this.servicelink + '/system/properties');
  }

  userlogout(user) {
    return this.http.post<any>(this.servicelink + '/user/logout/' + user, {});
  }

  getactivity(row, page) {
    return this.http.get<any>(this.servicelink + '/system/activity?row=' + row + '&page=' + page);
  }

  registeruser(user) {
    return this.http.post<any>(this.servicelink + '/user/register', user);
  }


  geauditpage(row) {
    return this.http.get<any>(this.servicelink + '/system/audit/page/' + row);
  }


  validateresetpassword(user) {
    return this.http.post<any>(this.servicelink + '/system/validate/reset', user);
  }

  resetpassword(user) {
    return this.http.post<any>(this.servicelink + '/system/reset/password', user);
  }

}
