import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable
    ({
        providedIn: 'root'
    }
    )
export class LoginService {
    private apiUrl = 'http://localhost/practic_web/'; // replace with your API endpoint

    constructor(private http: HttpClient) { }

    // login
    login(username: string, password: string): Observable<any> {
        return this.http.post<any>(this.apiUrl + `login.php`, {
            username: username,
            password: password
        });
    }

    // register
    register(username: string, password: string): Observable<any> {
        return this.http.post<any>(this.apiUrl + `register.php`, {
            username: username,
            password: password
        });
    }
}