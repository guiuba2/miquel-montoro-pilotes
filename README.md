# miquel-montoro-pilotes
Miquel Montoro API - Place, find, or update Pilotes orders, the famous Majorcan recipe
This is a Jagaad Java Engineer technical test.
All rest requests can be accessed via Swagger with the following description and instructions:

* POST "/api/placeOrder"
To place an order please choose between 5, 10 or 15 pilotes. Also inform client phone number and delivery address.

* PUT "/api/updateOrder/{orderId}" 
Update order using order id"

* DELETE "/api/deleteClient/{phoneNumber}"
Find and delete client by phone number.

* GET "/api/findClient/{name}"
Find clients by name. Partial searches allowed: e.g. all orders of clients whose name contains an “a” in their name.
(This search operation is secured, so 
User: admin@admin.com and password: admin are required to access this rest endpoint)

* POST "/api/register"
Register a client. Please ignore order fields in this step
-------------------------------------------------------

