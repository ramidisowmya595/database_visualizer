import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 private gridApi;
 private gridColumnApi;          
 private columnDefs;
 private sortingOrder;
 constructor(private http: HttpClient)
 {
    this.columnDefs=[
    {
      headerName:"Type",
      field:"type_nm",
      width:90,   
      sortingOrder: ["desc","asc"]
    },
    {
      headerName:"Schema",
      field:"schema_nm",
      width:120,
      sortingOrder: ["desc",null]
    },
    {
      headerName:"Table",
      field:"table_nm",
      width:90,
      sortingOrder: ["asc"]
    },
    {
      headerName:"Column",
      field:"column_nm",
        width: 100
    }
   ];
   this.sortingOrder=["desc","asc",null]
 }
 onGridReady(params){
   this.gridApi=params.api;
   this.gridColumnApi=params.columnApi;
   this.http
   .get("http://localhost:8084/insertdb")
   .subscribe(data=>{ 
    params.api.setRowData(data['_embedded']['insertdb']);
   })
 
   
 }
}
