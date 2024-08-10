

-- Insert 5 Users
INSERT INTO users (id, username, password, name, dob) VALUES
                                                          (1, 'johndoe', 'password123', 'John Doe', '1990-01-15'),
                                                          (2, 'janedoe', 'password456', 'Jane Doe', '1992-02-20'),
                                                          (3, 'mikesmith', 'password789', 'Mike Smith', '1988-03-30'),
                                                          (4, 'sarahconnor', 'passwordabc', 'Sarah Connor', '1985-07-25'),
                                                          (5, 'brucewayne', 'passwordxyz', 'Bruce Wayne', '1983-11-18');

-- Insert 10 Addresses
INSERT INTO addresses (id, street, city, state, postal_code, user_id) VALUES
                                                                          (1, '123 Maple Street', 'Springfield', 'IL', '62701', 1),
                                                                          (2, '456 Oak Avenue', 'Springfield', 'IL', '62702', 1),
                                                                          (3, '789 Pine Lane', 'Springfield', 'IL', '62703', 2),
                                                                          (4, '321 Elm Street', 'Metropolis', 'NY', '10001', 2),
                                                                          (5, '654 Cedar Court', 'Metropolis', 'NY', '10002', 3),
                                                                          (6, '987 Birch Road', 'Gotham', 'NJ', '07001', 3),
                                                                          (7, '111 Aspen Way', 'Gotham', 'NJ', '07002', 4),
                                                                          (8, '222 Redwood Circle', 'Gotham', 'NJ', '07003', 4),
                                                                          (9, '333 Willow Drive', 'Gotham', 'NJ', '07004', 5),
                                                                          (10, '444 Spruce Terrace', 'Gotham', 'NJ', '07005', 5);
