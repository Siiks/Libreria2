import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorFormComponent } from './autor-form.component';

describe('AutorFormComponent', () => {
  let component: AutorFormComponent;
  let fixture: ComponentFixture<AutorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutorFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
