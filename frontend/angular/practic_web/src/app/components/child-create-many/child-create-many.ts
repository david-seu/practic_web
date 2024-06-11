// child.component.ts
import { Component } from '@angular/core';
import { ChildService } from '../../service/child.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-child',
    templateUrl: './child.component.html',
    styleUrls: ['./child.component.css']
})
export class ChildComponent {
    children: { name: string; parentId: string; }[] = [];
    childName = '';
    parentId = '';

    constructor(private childrenService: ChildService, private router: Router) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
    }

    saveChild() {
        this.children.push({ name: this.childName, parentId: this.parentId });
        this.childName = '';
        this.parentId = '';
    }

    submitChildren() {
        this.childrenService.addManyChildren(this.children).subscribe((response: any) => {
            console.log(response);
            this.children = [];
        });
    }
}