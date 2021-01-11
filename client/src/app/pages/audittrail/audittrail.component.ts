import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { BackendService } from 'src/app/service/backend.service';

@Component({
  selector: 'app-audittrail',
  templateUrl: './audittrail.component.html',
  styleUrls: ['./audittrail.component.css']
})
export class AudittrailComponent implements OnInit {

  constructor(
    private service: BackendService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router
  ) { }


  list: any[];
  total: any;
  row: any;
  options: any[];

  ngOnInit(): void {
    this.getpages(10);
  }

  getpages(row) {
    this.service.geauditpage(row).subscribe(res => {
      this.total = res.count;
      this.row = res.row;
      this.options = res.rowoptions;
      this.getactivity(this.row, 0);
    }, err => {
      console.log(err);
    });
  }

  getactivity(row, page) {
    this.list = [];
    this.service.getactivity(row, page).subscribe(res => {
      this.list = res;
      this.list.forEach(r => {
        if (r.eventtime != null && r.eventdate != null) {
          let datetime: any = new Date(r.eventdate);
          datetime.setHours(r.eventtime.split(':')[0], r.eventtime.split(':')[1], r.eventtime.split(':')[2]);
          r.eventdate = datetime;
        }
      });
    }, err => {
      console.log(err);
    });
  }

  paginate(event) {
    this.getactivity(event.rows, event.page);
    //event.first = Index of the first record
    //event.rows = Number of rows to display in new page
    //event.page = Index of the new page
    //event.pageCount = Total number of pages
  }

}
