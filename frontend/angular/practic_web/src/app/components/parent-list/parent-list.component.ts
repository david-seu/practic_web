import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParentService } from '../../service/parent.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-parent-list',
    templateUrl: './parent-list.component.html',
    styleUrls: ['./parent-list.component.css'],
    standalone: true,
    imports: [CommonModule]
})
export class ParentListComponent implements OnInit {
    parents: any[] = [];

    constructor(private parentService: ParentService, private router: Router) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.refresh('');
        this.parentService.getParents().subscribe(parents => {
            this.parents = parents;
        });
    }

    refresh(name: string): void {
        this.parentService.getParentsByName(name).subscribe(parents => {
            this.parents = parents;
        });
    }

    refreshFrontend(name: string): void {
        this.parentService.getParents().subscribe(parents => {
            this.parents = parents;

            if (name) {
                this.parents = this.parents.filter(parent => parent.name === name);
            }
        });
    }

    createParent(): void {
        this.router.navigate(['/create-parent']);
    }

    updateParent(id: string): void {
        this.router.navigate(['/update-parent', id]);
    }

    deleteParent(id: string): void {
        this.parentService.deleteParent(id).subscribe(() => {
            this.parents = this.parents.filter(parent => parent.id !== id);
        });
    }
}