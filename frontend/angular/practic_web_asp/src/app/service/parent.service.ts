import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ParentService {
    private apiUrl = 'http://localhost:5286/api/ParentClass';

    constructor(private http: HttpClient) { }

    // Read
    getConfirmRequest(id: string): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}?UserId=${id}`);
    }
}