import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ParentService {
    private apiUrl = 'http://localhost/practic_web'; // replace with your API endpoint

    constructor(private http: HttpClient) { }

    // Create
    createParent(parent: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/addParent.php`, parent);
    }

    // Read
    getParent(id: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/getParentById.php?p_id=${id}`);
    }

    // Update
    updateParent(parent: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/updateParent.php`, parent);
    }

    // Delete
    deleteParent(id: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/removeParent.php?p_id=${id}`);
    }

    // List
    getParents(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl + '/showParents.php');
    }

    // List by name
    getParentsByName(name: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/getAllParentsByName.php?name=${name}`);
    }

    // Add many parents
    addManyParents(parents: any[]): Observable<any> {
        console.log(parents);
        return this.http.post<any>(`${this.apiUrl}/addManyParents.php`, parents);
    }
}