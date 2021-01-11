import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendpageComponent } from './sendpage.component';

describe('SendpageComponent', () => {
  let component: SendpageComponent;
  let fixture: ComponentFixture<SendpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendpageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
