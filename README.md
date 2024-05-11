# Stock Market API Endpoints

## Prerequisites

Before using the Stock Market API, ensure you have Postman installed from [Postman's official website](https://www.postman.com/).

### Database Schema

To set up the database for the Stock Market API, create the following tables corresponding to the Java entity classes:

1. **Portfolio Table:**
    ```sql
    CREATE TABLE portfolio (
        portfolio_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT,
        stock_id BIGINT,
        quantity INT,
        avg_purchase_price DOUBLE,
        total_investment DOUBLE,
        current_price DOUBLE,
        total_value DOUBLE
    );
    ```

2. **Stocks Table:**
    ```sql
    CREATE TABLE stocks (
        stock_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        symbol VARCHAR(255),
        company_name VARCHAR(255),
        stock_type VARCHAR(255),
        price DOUBLE,
        change_in_price DOUBLE,
        stock_exchange VARCHAR(255),
        timezone VARCHAR(255),
        currency VARCHAR(255),
        change_percent DOUBLE,
        previous_close DOUBLE,
        last_update_utc TIMESTAMP,
        country_code VARCHAR(255),
        exchange_open TIMESTAMP,
        exchange_close TIMESTAMP,
        utc_offset_sec INT,
        google_mid VARCHAR(255)
    );
    ```

3. **Watchlist Table:**
    ```sql
    CREATE TABLE watchlist (
        watchlist_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT,
        stock_id BIGINT,
        FOREIGN KEY (user_id) REFERENCES users(user_id),
        FOREIGN KEY (stock_id) REFERENCES stocks(stock_id)
    );
    ```

4. **Transactions Table:**
    ```sql
    CREATE TABLE transactions (
        transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT,
        stock_id BIGINT,
        transaction_type VARCHAR(255),
        quantity INT,
        price DOUBLE,
        timestamp TIMESTAMP
    );
    ```

5. **Users Table:**
    ```sql
    CREATE TABLE users (
        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        email VARCHAR(255),
        firstname VARCHAR(255),
        lastname VARCHAR(255),
        phone VARCHAR(255),
        password VARCHAR(255)
    );
    ```

## Endpoints

1. **Login Auth**
    - **Description:** This endpoint is used to authenticate users by logging them into the system.
    - **Method:** POST
    - **URL:** `http://localhost:8080/auth/login`

2. **Auth Signup**
    - **Description:** Endpoint for user registration and signup.
    - **Method:** POST
    - **URL:** `http://localhost:8080/auth/signup`

3. **News**
    - **Description:** Retrieves news sentiment for a specific stock symbol (e.g., AAPL).
    - **Method:** GET
    - **URL:** `https://www.alphavantage.co/query?function=NEWS_SENTIMENT&tickers=AAPL&apikey=demo`

4. **Stock News**
    - **Description:** Fetches news related to a specific stock.
    - **Method:** GET
    - **URL:** `http://localhost:8080/user/news`
    - **Headers:** Authorization (JWT Token)

5. **Stocks**
    - **Description:** Retrieves information about user-owned stocks.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/stocks`
    - **Headers:** Authorization (JWT Token)

6. **Stock Search**
    - **Description:** Search for stocks based on their name or symbol.
    - **Method:** GET
    - **URL:** `http://localhost:8080/user/stocks/search?name=GOOG`
    - **Headers:** Authorization (JWT Token)

7. **Watchlist Add**
    - **Description:** Adds a stock to the user's watchlist.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/watchlist/add`
    - **Headers:** Authorization (JWT Token)

8. **Watchlist Remove**
    - **Description:** Removes a stock from the user's watchlist.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/watchlist/delete`
    - **Headers:** Authorization (JWT Token)

9. **Watchlist GET**
    - **Description:** Retrieves the user's watchlist.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/watchlist`
    - **Headers:** Authorization (JWT Token)

10. **Get Portfolio**
    - **Description:** Fetches the user's portfolio.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/portfolio/sell`
    - **Headers:** Authorization (JWT Token)

11. **Buy Stock**
    - **Description:** Allows the user to buy stocks.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/portfolio/buy`
    - **Headers:** Authorization (JWT Token)

12. **Sell Stock**
    - **Description:** Allows the user to sell stocks.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/portfolio/sell`
    - **Headers:** Authorization (JWT Token)

13. **Transactions**
    - **Description:** Retrieves user transaction details.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/portfolio/sell`
    - **Headers:** Authorization (JWT Token)

14. **Get Chart**
    - **Description:** Fetches a chart related to user's portfolio.
    - **Method:** POST
    - **URL:** `http://localhost:8080/user/portfolio/sell`
    - **Headers:** Authorization (JWT Token)

## Contributors

- [Aniket Verma](https://github.com/aniketverma-7)
- [Susmitha Kusuma](https://github.com/SushieeK)
- [Remon Roshdy](https://github.com/remonroshdy) 

