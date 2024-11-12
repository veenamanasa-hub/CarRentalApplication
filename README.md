User logs in with his/her creds. 
Spring Security is used for authentication and authorization. 
After creds are validated in User table in DB, he is routed to Dashboard where list of cars 
are listed. On load of this dashboard page, "getCars" API is called to fetch data from Cars Table in DB. 
Once user click on Book button and he is asked to select start date and end date. 
Once dates are selected user clicks on "Confirm Booking" button,
there will be an API call "bookCar" and an entry is made in the booking table in 
DB and the count of cars is reduced in Cars Table in DB. After user clicks confirm button,
he is shown a message that booking is successful.

Attached flow Diagram.
CarRentalsFlowDiagram.png
