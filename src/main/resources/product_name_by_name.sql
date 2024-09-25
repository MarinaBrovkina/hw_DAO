SELECT DISTINCT O.product_name
FROM schema.orders AS O
         JOIN schema.customers AS C ON O.customer_id = C.id
WHERE LOWER(C.name) = LOWER(:name);
