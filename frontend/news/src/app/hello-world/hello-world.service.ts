import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";

interface WelcomeMessage {
  message: string
}

@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {

  constructor(private http: HttpClient) {

  }


  fetchHelloWorld(): Observable<string> {
    return this.http.get<WelcomeMessage>(environment.backendBaseUrl + "/hello-world")
      .pipe(
        map(response => response.message)
      );
  }
}
