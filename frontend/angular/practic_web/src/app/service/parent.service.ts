import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ParentService {
    private apiUrl = 'http://localhost:3000/parents'; // replace with your API endpoint

    constructor(private http: HttpClient) { }

    // Create
    createParent(parent: any): Observable<any> {
        return this.http.post<any>(this.apiUrl, parent);
    }

    // Read
    getParent(id: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/${id}`);
    }

    // Update
    updateParent(id: string, parent: any): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/${id}`, parent);
    }

    // Delete
    deleteParent(id: string): Observable<any> {
        return this.http.delete<any>(`${this.apiUrl}/${id}`);
    }

    // List
    getParents(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }

    // List by name
    getParentsByName(name: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}?name=${name}`);
    }

    // Add many parents
    addManyParents(parents: any[]): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/addMany`, parents);
    }
}