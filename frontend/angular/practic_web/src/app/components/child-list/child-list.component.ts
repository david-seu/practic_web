import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChildService } from '../../service/child.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-child-list',
    templateUrl: './child-list.component.html',
    styleUrls: ['./child-list.component.css'],
    standalone: true,
    imports: [CommonModule]
})
export class ChildListComponent implements OnInit {
    children: any[] = [];

    constructor(private childService: ChildService, private router: Router) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.childService.getChildren().subscribe(children => {
            this.children = children;
        });
    }

    refresh(name: string): void {
        this.childService.getChildrenByName(name).subscribe(children => {
            this.children = children;
        });
    }

    refreshFrontend(name: string): void {
        this.childService.getChildren().subscribe(children => {
            this.children = children;
            console.log(this.children);
            console.log(name);
            if (name) {
                //to contain only the child that contain the name
                this.children = this.children.filter(child => child.name.toLowerCase().includes(name.toLowerCase()));
            }
        });
    }



    createChild(): void {
        this.router.navigate(['/child-create']);
    }

    createChildMany(): void {
        this.router.navigate(['/child-create-many']);
    }

    updateChild(id: string): void {
        this.router.navigate(['/child-update', id]);
    }

    deleteChild(id: string): void {
        this.childService.deleteChild(id).subscribe(() => {
            this.refreshFrontend('');
        });
    }

    logout(): void {
        sessionStorage.removeItem("user");
        this.router.navigate(['']);
    }
}