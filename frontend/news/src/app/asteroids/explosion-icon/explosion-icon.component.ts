import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'nw-explosion-icon',
  template: `
    <img src="../../../assets/icons/nuclear.ico"
         width="24px"
         alt="explosion"/>
  `,
  styles: []
})
export class ExplosionIconComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
