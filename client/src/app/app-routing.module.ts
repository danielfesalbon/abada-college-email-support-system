import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AudittrailComponent } from './pages/audittrail/audittrail.component';
import { ConfigurationComponent } from './pages/configuration/configuration.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';
import { SendpageComponent } from './pages/sendpage/sendpage.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { UseraccountComponent } from './pages/useraccount/useraccount.component';
import { AuthguardService } from './service/authguard.service';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "", redirectTo: "main", pathMatch: 'full' },
  {
    path: "main", component: MainComponent, canActivate: [AuthguardService], children: [
      { path: "", redirectTo: "emails", pathMatch: 'full' },
      { path: "emails", component: SendpageComponent },
      {
        path: "settings", component: SettingsComponent, children: [
          { path: "", redirectTo: "configuration", pathMatch: 'full' },
          { path: "user", component: UseraccountComponent },
          { path: "audittrail", component: AudittrailComponent },
          { path: "configuration", component: ConfigurationComponent },

        ]
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
