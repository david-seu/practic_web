import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ChildService {
    private apiUrl = 'http://localhost:3000/children'; // replace with your API endpoint

    constructor(private http: HttpClient) { }

    // Create
    createChild(child: any): Observable<any> {
        return this.http.post<any>(this.apiUrl, child);
    }

    // Read
    getChild(id: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/${id}`);
    }

    // Update
    updateChild(id: string, child: any): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/${id}`, child);
    }

    // Delete
    deleteChild(id: string): Observable<any> {
        return this.http.delete<any>(`${this.apiUrl}/${id}`);
    }

    // List
    getChildren(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }

    // List by name
    getChildrenByName(name: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}?name=${name}`);
    }

    // List by parent id
    getChildrenByParentId(parentId: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}?parentId=${parentId}`);
    }

    // List by parent id and name
    getChildrenByParentIdAndName(parentId: string, name: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}?parentId=${parentId}&name=${name}`);
    }

    // Add many children
    addManyChildren(children: any[]): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/addMany`, children);
    }
}