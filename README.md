need database mysql library.sql and kafka
java 17

how to install
1. git clone the code
2. export the library.sql
3. run the code (using intellij)

endpoint
1. get http://localhost:8080/api/v1/book
2. post http://localhost:8080/api/v1/book/add
request "bookName" : "",
	"author" : "",
	"publicationYear" : ""
3. get http://localhost:8080/api/v1/loan
4. post http://localhost:8080/api/v1/loan/add
request "bookId" : "",
	"userId" :"",
	"loanDate" : ""
5. get http://localhost:8080/api/v1/loan/count-rank
6. post http://localhost:8080/api/v1/loan/return
request "loanId": ""

example response 
{
  "code": 200,
  "data": "",
  "message": "Success",
  "timestamp": ""
}
