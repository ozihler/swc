import {Injectable} from '@angular/core';
import {webSocket, WebSocketSubject} from "rxjs/webSocket";
import {EMPTY, Subject} from "rxjs";
import {catchError, map, switchAll, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class DisasterFeedService {

  url = 'ws://localhost:8081/disasterfeed';
  socket$: WebSocketSubject<any>;
  messages: any[] = [];

  constructor() {
    this.socket$ = webSocket(this.url);
    this.socket$.
    subscribe(
      msg => {
        console.log("Received from server: ", msg);
        this.messages.push(msg);
      },
      err => console.error("Error: ", err)
    );
  }

  sendMessage(msg: any) {
    this.socket$.next(msg);
  }

  close() {
    this.socket$.complete();
  }
}
