# DBGroupProject

TODO: 
1. WRITE SQL QUERIES USING BEST PRACTICES.
2. DISCUSS WITH GROUP EACH SQL FUNCTION (Why,When and How the functions are being used).

=====
1. Write a query in SQL to display all the data of branches.
2. Write a query in SQL to display all the data of employees.
3. Write a query in SQL to display all the data of items.
4. Write a query in SQL to display all the data of positions.
5. Write a query in SQL to display all the data of territory.
6. Write a query in SQL to display all the data of branches with territory 2127
7. Write a query in SQL to display all the data of branches that have a date_opened from 2012-01-12
8. Write a query in SQL to display all the data of branches that have a date_opened between 2002-08-10 - 2012-01-12
9. Write a query in SQL to display first_name of employees that has a salary more than or equal to 1538.
10. Write a query in SQL to display first and last name of employees that has a salary less than 2118.
11. Write a query in SQL to display all the data of employees that has a salary more than 1750 and less than or equal to 2116 order by first name in asc.
12. Write a query in SQL to display all the data of employees that has started from 2013-05-11 and has a commission more than 0.06
13. Write a query in SQL to display all the data of items that has the highest sale price
14. Write a query in SQL to display sale price, item_name of items that has the second highest sale price
15. Write a query in SQL to display item's name, category and cost of items that has the third lowest cost 
16. Write a query in SQL to display all Sales Representative whose branch opened date is after 2004-07-23
17. Write a query to display unique item key, name, brand and category that belongs to Electronics category
18. Write a query to display unique state and city data with city in alphabetical order 
19. Write a query to display unique states that has a population more than 1782
20. Write a query to display  grouped rows of first name of employees that has a salary more than 1500 and ordered in desc order
21. Find the name and salary of the employees whose salary is greater than the average salary of all positions
22. Write SQL statement that lists the number of cities in each state. Only include state with more than 5 cities.
23. List the number of items of each brand, except the Apple, sorted high to low.Only include brands with 3 or less items.
24.How to find Third highest salary in Employee table using self-join (sub-query)?
25. Write an SQL query to find the 10th highest employee salary from an Employee table.
26. Given a table 'branch' where the column id is a unique numeric identifier, how can you efficiently select the first 100 odd id values from the table?
27. Write a query to fetch the number of employees working in the position ‘SR’.
28. Write a query to find the first_names of employees that begin with ‘S’
29. Write a query find number of employees whose started date is between 2007-06-11 to 2017-09-14 and are grouped according to position
30. Write a query to retrieve two minimum salaries from the employee table.
31. Write a query to retrieve two maximum salaries from the employee table.




====== Answers needs to be deleted========
```db2
# 1
select * from branch;
# 2
select * from employee;
# 3
select * from item;
# 4
select * from positions;
# 5
select * from territory;
# 6
select * from branch where teritory=2127;
# 7
select * from branch where date_opened > '2012-01-12';
# 8
select * from branch where date_opened > '2002-08-10' and date_opened < '2012-01-12';
# 9
select first_name from employee where salary >=1538;
# 10
select first_name,last_name from employee where salary<2118;
# 11
select * from employee where salary<1750 and salary<=2116 order by first_name;
# 12
select * from employee where date_started>'2013-05-11' and commission>0.06;
# 13
select sale_price
from item where sale_price = (SELECT max(sale_price) FROM item);
# 14
SELECT MAX(sale_price) FROM item WHERE sale_price NOT IN ( SELECT Max(sale_price) FROM item);
# 15
SELECT item_name,category,cost FROM item a1 WHERE 3-1= (SELECT COUNT(DISTINCT cost) FROM item a2 WHERE a2.cost < a1.cost) ;
# 16
select * from employee where position='SR' and date_started>='2004-07-23';
# 17
select distinct item_key,item_name,brand,category from item where category='Electronics';
# 18
select distinct state,city from territory order by city;
# 19
select distinct city from territory where population>1782;
# 20
select COUNT(first_name),first_name from employee where salary > 5381 group by first_name;
# 21
SELECT * FROM employee
WHERE salary >
ALL(SELECT avg(salary)FROM employee GROUP BY position);
# 22
SELECT COUNT(city), state
FROM territory
GROUP BY state
HAVING COUNT(city) > 5;
# 23
SELECT COUNT(item_name), brand
  FROM item
 WHERE brand <> 'Apple'
 GROUP BY brand
HAVING COUNT(item_name) <= 4
 ORDER BY COUNT(item_name) DESC
# 24
select distinct salary from employee e1 where 2 = (select count(distinct salary) from employee e2 where  e1.salary <= e2.salary);
# 25
SELECT Salary FROM
( SELECT DISTINCT Salary FROM employee ORDER BY Salary DESC LIMIT 10
) AS Emp ORDER BY Salary LIMIT 1;
# 26
SELECT id FROM branch WHERE id % 2 = 1 ORDER BY id limit 100
# 27
SELECT COUNT(*) FROM employee WHERE position = 'SR';
# 28
SELECT * FROM employee WHERE first_name LIKE 'S%';
# 29
SELECT COUNT(*), position FROM employee WHERE date_started BETWEEN '2007-06-11' AND '2017-09-14' GROUP BY position;
# 30
SELECT DISTINCT Salary FROM employee E1
 WHERE 2 >= (SELECT COUNT(DISTINCT Salary)FROM employee E2
  WHERE E1.Salary >= E2.Salary) ORDER BY E1.Salary DESC;
# 31
SELECT DISTINCT Salary FROM employee E1
 WHERE 2 >= (SELECT COUNT(DISTINCT Salary) FROM employee E2
  WHERE E1.Salary <= E2.Salary) ORDER BY E1.Salary DESC;


```