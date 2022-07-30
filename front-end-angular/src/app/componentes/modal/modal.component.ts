import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  showModal = false;

  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.showModal = true;
  }

  onClose() {
    this.showModal = false;
    setTimeout(
      () => this.router.navigate(
        [
          {
            outlets: {
              modal: null
            }
          }
        ],
        {
          relativeTo: this.activatedRoute.parent

        },
      ),
      100
    );
  }

  onCloseAfterUpdate() {
    this.showModal = false;
    setTimeout(
      () => this.router.navigate(
        [
          {
            outlets: {
              modal: null
            }
          }
        ],
        {
          relativeTo: this.activatedRoute.parent
        },
      ),
      75
    );
    setTimeout(
      () => window.location.reload(),
      75
    );
  }

  onDialogClick(event: UIEvent) {
    event.stopPropagation();
    event.cancelBubble = true;
  }
}