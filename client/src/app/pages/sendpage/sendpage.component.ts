import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';
import { TokenService } from 'src/app/service/token.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-sendpage',
  templateUrl: './sendpage.component.html',
  styleUrls: ['./sendpage.component.css']
})
export class SendpageComponent implements OnInit {

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private tokenService: TokenService
  ) { }

  list: any[];
  uploadedFiles: any[] = [];
  subject: string;
  message: string;
  blocked: boolean;
  attachments: any[] = [];
  files: any[];

  ngOnInit(): void {
    this.files = [];
    this.blocked = false;
    this.list = [];
    this.subject = '';
    this.message = '';
  }

  handleFileInput(ev) {
    this.list = [];

    let workBook = null;
    let jsonData = null;
    const reader = new FileReader();
    const file = ev.target.files[0];
    reader.onload = (event) => {
      const data = reader.result;
      workBook = XLSX.read(data, { type: 'binary' });
      jsonData = workBook.SheetNames.reduce((initial, name) => {
        const sheet = workBook.Sheets[name];
        initial[name] = XLSX.utils.sheet_to_json(sheet);
        return initial;
      }, {});
      this.list = jsonData.RECIPIENTS;
    }
    reader.readAsBinaryString(file);

  }

  sendEmail() {
    let req: any = {};
    req.email = this.list.map(i => i.email);
    req.message = this.message;
    req.subject = this.subject;
    req.files = this.files;
    this.blocked = true;
    this.service.sendemail(req).subscribe(res => {
      if (res.flag == "success") {
        this.ngOnInit();
        this.messageService.add({ key: 'bc', severity: 'success', summary: 'Success', detail: res.event });
        //toast success
      }
    }, err => {
      this.ngOnInit();
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: err.message });
      this.tokenService.checkSession(err);
    });
  }


  confirmSendingEmail() {
    this.confirmationService.confirm({
      message: 'Sending email. Please confirm.',
      accept: () => {
        this.sendEmail();
      }
    });
  }


  onUpload(event) {
    for (let file of event.files) {
      this.attachments.push(file);
      let f: File = file;
      this.pushFile(f);
    }
  }


  async pushFile(f: File) {
    const file = f;
    const result = await toBase64(file).catch(e => Error(e));
    this.files.push({ base64: result, filename: file.name });
    console.log(result);
    if (result instanceof Error) {
      this.messageService.add({ key: 'bc', severity: 'error', summary: 'Failed', detail: result.message });
      return;
    }
  }

}

const toBase64 = file => new Promise((resolve, reject) => {
  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = () => resolve(reader.result);
  reader.onerror = error => reject(error);
});
