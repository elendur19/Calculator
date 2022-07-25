import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ExpressionRequest } from '../model/ExpressionRequest';
import { CalculatorService } from '../service/calculator.service';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  expression: ExpressionRequest = new ExpressionRequest();
  result: string = '';
  errorHidden: boolean = true;
  invalidExpression = true;
  errorMessage: string = '';

  constructor(private calculatorService: CalculatorService) { }

  ngOnInit(): void {
  }

  calculateExpression() {
    //console.log("Expression ----> " + this.expression.expression);
    this.calculatorService.calculate(this.expression).subscribe(result => {
      console.log("Result is --> " + JSON.stringify(result));
      this.errorHidden = true;
      this.result = result;
      this.errorHidden = true;
      this.invalidExpression = false;
    }, (error: HttpErrorResponse) => {
      console.log("Error ocurred ---> " + error.error.message);
      this.errorMessage = error.error.message;
      this.invalidExpression = true;
      this.errorHidden = false;
    }
    )
  }

}
