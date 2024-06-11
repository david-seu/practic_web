import { Component } from '@angular/core';
import { ParentService } from '../../service/parent.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-parent',
    templateUrl: './parent.component.html',
    styleUrls: ['./parent.component.css']
})
export class ParentCreateManyComponent {
    parents: { name: string, user_id: string }[] = [];
    parentName = '';
    userId = '';

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.userId = sessionStorage.getItem("user")!;
    }

    constructor(private parentsService: ParentService, private router: Router) { }

    saveParent() {
        this.parents.push({ name: this.parentName, user_id: this.userId });
        this.parentName = '';
    }

    submitParents() {
        this.parentsService.addManyParents(this.parents).subscribe(response => {
            console.log(response);
            this.parents = [];
        });
    }
}