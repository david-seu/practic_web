import { Routes } from '@angular/router';
import { ChildListComponent } from './components/child-list/child-list.component';
import { ChildCreateComponent } from './components/child-create/child-create.component';
import { LoginComponent } from './components/login/login.component';
import { ChildUpdateManyComponent } from './components/child-update-many/child-update-many.component';
import { ConfirmRequestComponent } from './components/confirm-request/confirm-request.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'child-list', component: ChildListComponent },
    { path: 'child-create', component: ChildCreateComponent },
    { path: 'child-update-many', component: ChildUpdateManyComponent },
    { path: 'confirm-request', component: ConfirmRequestComponent },
    { path: '**', redirectTo: '' }
];
