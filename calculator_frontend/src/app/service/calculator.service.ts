import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExpressionRequest } from '../model/ExpressionRequest';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {

  private calculatorUrl: string;

  constructor(private http: HttpClient) {
    this.calculatorUrl = 'http://localhost:8080/api/calculate';
  }

  public calculate(mathPhrase: ExpressionRequest): Observable<any> {
    return this.http.post<ExpressionRequest>(this.calculatorUrl, mathPhrase);
  }

}