# DBGroupProject

TODO: 
1. WRITE SQL QUERIES USING BEST PRACTICES.
2. DISCUSS WITH GROUP EACH SQL FUNCTION (Why,When and How the functions are being used).
````

1. Write an SQL query to fetch the employees whose first name begins with any one characters, followed by a text “ar” and ending with any sequence of characters.
2.Write an SQL query to fetch all the orderNumbers which are present in either of the tables – ‘orderdetails’ and ‘orders’.
3.Write an SQL query to fetch common records between two tables - ‘orderdetails’ and ‘orders’.
4.Write an SQL query to fetch the customernames and replace the space with ‘-’.
5.Write an SQL query to display both the customernumber and contactFirstName together.
6.Fetch all the customer data who are not assigned with any state.
7.Write an SQL query to find the current date-time.
8.Write an SQL query to fetch all Orders records from orders table who do not have a comments and shippedDate records in Orders table.
9. Write an SQL query to fetch all the Employees with officeCode from Offices table.
10.Write an SQL query to fetch duplicate first name records from an Employee table.
11.Write an SQL query to create an empty table named newProductLines with the same structure as productlines table.
12.Write an SQL query to fetch top n records from products table?
13.There are products table which contains two columns productName and quantityInStock, you need to find all the products, whose quantity are greater than average quantity i.e. list of above average products.
14. Write an SQL query to fetch the no. of employees for each job title in the descending order.
15. Write an SQL query to print details of the order number and priceEach whose product code is "S18_2432".
16. Write an SQL query to determine the 5th highest MSRP from products table without using TOP or limit method.
17. Write an SQL query to print the city of customers having the highest payments amount for each city.
18. Write an SQL query to print the employee's name, office address line1,territory
19. Write a query to get the list of customers who made the payment more than once in the same day, grouped by customer and payment details, each ordered from the most recent payment date to oldest date.
20. Write a query to get the list of customers, and quantity of ordered product for the most recent shipped date, and most the highest quantity of orders. 
21. Write SQL query to fetch customers  having a payment amount greater than or equal to 5000 and less than or equal 10000, ordered by desc.
22. Write SQL query to fetch number of customers having the most popular sales representative.
23. Write SQL query to fetch Customer name, Third highest Customer's payment amount, where customers employer office code is 4
24. Write SQL query to fetch Employer first name, last name whose customers have the lowest credit limit. 
25. Write SQL query to fetch Employer first name, last name whose customers have the average credit limit. 
````

=================ANSWERS (TO BE DELETED)=================

```mysql
# 1
SELECT firstname
FROM classicmodels.employees
WHERE firstName LIKE '_ar%';
# 2
SELECT orderNumber FROM classicmodels.orderdetails
UNION
SELECT orderNumber FROM classicmodels.orders;
# 3
SELECT *
FROM classicmodels.orders
WHERE orderNumber IN
(SELECT orderNumber from classicmodels.orderdetails);
# 4
SELECT REPLACE(customername, ' ', '-')
FROM classicmodels.customers;
# 5
SELECT CONCAT(customerNumber,contactFirstName) as customerInfo
FROM classicmodels.customers;
# 6
SELECT *
FROM classicmodels.customers
WHERE state IS NULL;
# 7
SELECT NOW();
# 8
SELECT * FROM classicmodels.orders
where comments is null and shippedDate is null;
# 9
SELECT distinct E.firstName,E.officeCode
FROM classicmodels.employees E
  JOIN classicmodels.offices M
ON E.officeCode = M.officeCode;
# 10
SELECT firstName, COUNT(*)
FROM classicmodels.employees
GROUP BY  firstName
HAVING COUNT(*) > 1;
# 11
CREATE TABLE classicmodels.newProductLines
SELECT * FROM classicmodels.productlines where 1=0;
# 12
SELECT *
FROM classicmodels.products
ORDER BY productCode DESC LIMIT 4;
# 13
SELECT productName, quantityInStock from classicmodels.products where quantityInStock > (SELECT AVG(quantityInStock) from classicmodels.products);
# 14
SELECT jobTitle, count(employeeNumber) No_Of_Workers
FROM classicmodels.employees
GROUP BY jobTitle
ORDER BY No_Of_Workers DESC;
# 15
SELECT DISTINCT W.orderNumber, T.priceEach
FROM classicmodels.orders W
INNER JOIN classicmodels.orderdetails T
ON W.orderNumber = T.orderNumber
AND T.productCode in ('S18_2432');
# 16
SELECT MSRP
FROM classicmodels.products W1
WHERE 5 = (
 SELECT COUNT( DISTINCT ( W2.MSRP ) )
 FROM classicmodels.products W2
 WHERE W2.MSRP >= W1.MSRP
 );
# 17
SELECT city,max(amount)as TotalAmount from classicmodels.payments p
join classicmodels.customers c
on c.customerNumber=p.customerNumber
group by c.city;
# 18
SELECT firstName, addressLine1,phone
from classicmodels.employees e
join classicmodels.offices
on e.officeCode = offices.officeCode;
# 20
select distinct customerName,quantityOrdered,shippedDate
from classicmodels.customers c
join classicmodels.orders o on c.customerNumber = o.customerNumber
join classicmodels.orderdetails d on o.orderNumber=d.orderNumber
order by o.shippedDate and d.quantityOrdered desc limit 1;
# 21
select distinct customerName,amount
from classicmodels.customers c
JOIN classicmodels.payments o on c.customerNumber = o.customerNumber
where amount >=5000 and amount<=10000
order by amount desc ;
# 22
select e.firstName as empName, COUNT(*) as numberOfCustomers
from classicmodels.customers c
join classicmodels.employees e
on c.salesRepEmployeeNumber = e.employeeNumber
group by e.firstName
order by numberOfCustomers desc
limit 1;
# 23
SELECT distinct customerName,p.amount,officeCode FROM
( SELECT c.customerName,p.amount,e.officeCode from
classicmodels.customers c
join classicmodels.payments p
on c.customerNumber = p.customerNumber
join classicmodels.employees e
on c.salesRepEmployeeNumber = e.employeeNumber
where officeCode=4
order by amount desc limit 3
)  AS p
ORDER BY amount LIMIT 1;
# 24
select firstName,lastName,creditLimit
from classicmodels.employees e
join classicmodels.customers c
on e.employeeNumber = c.salesRepEmployeeNumber
order by c.creditLimit asc limit 1;
# 25
select firstName,lastName,avg(creditLimit) as avgSalary
from classicmodels.employees e
join classicmodels.customers c
on e.employeeNumber = c.salesRepEmployeeNumber
group by firstName, lastName;
```