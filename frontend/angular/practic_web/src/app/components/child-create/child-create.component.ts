import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ChildService } from '../../service/child.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-child-create',
    templateUrl: './child-create.component.html',
    styleUrls: ['./child-create.component.css'],
    standalone: true,
    imports: [FormsModule]
})
export class ChildCreateComponent {
    child = { name: '' };

    constructor(private childService: ChildService, private router: Router) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
    }

    createChild(): void {
        this.childService.createChild(this.child).subscribe(() => {
            this.router.navigate(['/child-list']);
        });
    }
}