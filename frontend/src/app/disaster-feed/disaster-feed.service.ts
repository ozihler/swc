import {Injectable} from '@angular/core';
import {webSocket, WebSocketSubject} from "rxjs/webSocket";
import {EMPTY, Subject} from "rxjs";
import {catchError, switchAll, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class DisasterFeedService {

  url = 'ws://localhost:8080/disasterfeed';
  socket$: WebSocketSubject<any>;
  private messagesSubject$ = new Subject();
  // @ts-ignore
  public messages$ = this.messagesSubject$.pipe(switchAll(), catchError(e => {
    throw e
  }));


  constructor() {
    this.socket$ = webSocket(this.url);
    this.socket$.subscribe(data => console.log(data));
    const messages = this.socket$.pipe(
      tap({
        error: error => console.log(error),
      }), catchError(_ => EMPTY));
    this.messagesSubject$.next(messages);
  }
  sendMessage(msg: any) {
    this.socket$.next(msg);
  }

  close() {
    this.socket$.complete();
  }
}
