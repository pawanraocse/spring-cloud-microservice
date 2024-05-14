CREATE TABLE t_orders (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          order_number VARCHAR(255),
                          sku_code VARCHAR(255),
                          price DOUBLE,
                          quantity INT,
                          PRIMARY KEY (id)
);
