import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChildService } from '../../service/child.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-child-update',
    templateUrl: './child-update.component.html',
    styleUrls: ['./child-update.component.css'],
    standalone: true,
    imports: [FormsModule]
})
export class ChildUpdateComponent implements OnInit {
    child = { name: '', parentId: '' };
    id = "";

    constructor(
        private childService: ChildService,
        private router: Router,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        if (sessionStorage.getItem("user") == null) {
            this.router.navigate([' ']);
        }
        this.id = this.route.snapshot.paramMap.get('id')!;
        this.childService.getChild(this.id).subscribe(child => {
            this.child = child;
        });
    }

    updateChild(): void {
        this.childService.updateChild(this.id, this.child).subscribe(() => {
            this.router.navigate(['/child-list']);
        });
    }
}