-- Insert 10 records into the category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics', 'Electronics'),
    (nextval('category_seq'), 'Books', 'Books'),
    (nextval('category_seq'), 'Clothing', 'Clothing'),
    (nextval('category_seq'), 'Home Appliances', 'Home Appliances'),
    (nextval('category_seq'), 'Sports', 'Sports'),
    (nextval('category_seq'), 'Toys', 'Toys'),
    (nextval('category_seq'), 'Furniture', 'Furniture'),
    (nextval('category_seq'), 'Health & Beauty', 'Health & Beauty'),
    (nextval('category_seq'), 'Automotive', 'Automotive'),
    (nextval('category_seq'), 'Groceries', 'Groceries');

-- Insert 20 records into the product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 128GB storage', 'Smartphone', 100, 699.99, (SELECT id FROM category WHERE name='Electronics')),
    (nextval('product_seq'), 'Bestselling novel', 'Novel', 200, 15.99, (SELECT id FROM category WHERE name='Books')),
    (nextval('product_seq'), 'Men''s T-shirt', 'T-shirt', 150, 19.99, (SELECT id FROM category WHERE name='Clothing')),
    (nextval('product_seq'), 'Blender with multiple speeds', 'Blender', 50, 89.99, (SELECT id FROM category WHERE name='Home Appliances')),
    (nextval('product_seq'), 'Basketball', 'Basketball', 120, 29.99, (SELECT id FROM category WHERE name='Sports')),
    (nextval('product_seq'), 'Action figure', 'Action Figure', 80, 24.99, (SELECT id FROM category WHERE name='Toys')),
    (nextval('product_seq'), 'Wooden dining table', 'Dining Table', 10, 299.99, (SELECT id FROM category WHERE name='Furniture')),
    (nextval('product_seq'), 'Organic face cream', 'Face Cream', 70, 49.99, (SELECT id FROM category WHERE name='Health & Beauty')),
    (nextval('product_seq'), 'Car tire', 'Car Tire', 30, 99.99, (SELECT id FROM category WHERE name='Automotive')),
    (nextval('product_seq'), 'Organic apples', 'Apples', 250, 3.99, (SELECT id FROM category WHERE name='Groceries')),
    (nextval('product_seq'), 'Laptop with 16GB RAM', 'Laptop', 50, 999.99, (SELECT id FROM category WHERE name='Electronics')),
    (nextval('product_seq'), 'Cookbook', 'Cookbook', 100, 25.99, (SELECT id FROM category WHERE name='Books')),
    (nextval('product_seq'), 'Women''s jeans', 'Jeans', 120, 49.99, (SELECT id FROM category WHERE name='Clothing')),
    (nextval('product_seq'), 'Microwave oven', 'Microwave Oven', 40, 199.99, (SELECT id FROM category WHERE name='Home Appliances')),
    (nextval('product_seq'), 'Yoga mat', 'Yoga Mat', 60, 39.99, (SELECT id FROM category WHERE name='Sports')),
    (nextval('product_seq'), 'Dollhouse', 'Dollhouse', 30, 79.99, (SELECT id FROM category WHERE name='Toys')),
    (nextval('product_seq'), 'Office chair', 'Office Chair', 20, 149.99, (SELECT id FROM category WHERE name='Furniture')),
    (nextval('product_seq'), 'Shampoo', 'Shampoo', 100, 9.99, (SELECT id FROM category WHERE name='Health & Beauty')),
    (nextval('product_seq'), 'Car battery', 'Car Battery', 15, 129.99, (SELECT id FROM category WHERE name='Automotive')),
    (nextval('product_seq'), 'Organic milk', 'Milk', 200, 2.99, (SELECT id FROM category WHERE name='Groceries'));
