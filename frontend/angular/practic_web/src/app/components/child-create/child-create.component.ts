import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ChildService } from '../../service/child.service';
import { FormsModule } from '@angular/forms';
import { ParentService } from '../../service/parent.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-child-create',
    templateUrl: './child-create.component.html',
    styleUrls: ['./child-create.component.css'],
    standalone: true,
    imports: [FormsModule, CommonModule]
})
export class ChildCreateComponent {
    child = { name: '', p_id: '' };
    parents: any[] = [];

    constructor(private childService: ChildService, private router: Router, private parentService: ParentService) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.parentService.getParents().subscribe(parents => {
            this.parents = parents;
        });
    }

    createChild(): void {
        this.childService.createChild(this.child).subscribe(() => {
            this.router.navigate(['/child-list']);
        });
    }
}