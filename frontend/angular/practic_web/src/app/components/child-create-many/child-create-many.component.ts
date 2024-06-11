// child.component.ts
import { Component } from '@angular/core';
import { ChildService } from '../../service/child.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ParentService } from '../../service/parent.service';

@Component({
    selector: 'app-child',
    templateUrl: './child-create-many.component.html',
    styleUrls: ['./child-create-many.component.css'],
    standalone: true,
    imports: [CommonModule, FormsModule]
})
export class ChildCreateManyComponent {
    children: { name: string; p_id: string; }[] = [];
    childName = '';
    parentId = '';
    parents: any[] = [];


    constructor(private childrenService: ChildService, private router: Router, private parentService: ParentService) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.parentService.getParents().subscribe(parents => {
            this.parents = parents;
        });
    }

    saveChild() {
        this.children.push({ name: this.childName, p_id: this.parentId });
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