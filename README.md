This project automates specific tasks on the Flipkart website using Selenium WebDriver, TestNG, and Java. The goal is to extract information such as star ratings, prices, discounts, titles, and image URLs based on specific conditions across three test scenarios.

Test Cases:-

testCase01: 
Search "Washing Machine".
Navigate to Flipkart.
Search for "Washing Machine".
Sort results by "Popularity".
Print the count of items that have a rating ≤ 4 stars.

testCase02: 
Search "iPhone".
Search for "iPhone".
Print the Titles and Discount % of items having more than 17% discount.

testCase03: 
Search "Coffee Mug".
Search for "Coffee Mug".
Apply filter: 4 stars and above.
Print the Title and Image URL of the top 5 items with the highest number of reviews.

Technologies Used:-
Java 21,
Selenium WebDriver,
TestNG,
Gradle(for dependency management)

