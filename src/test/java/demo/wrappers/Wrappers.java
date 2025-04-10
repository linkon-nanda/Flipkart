package demo.wrappers;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    /*
     * Write your selenium wrappers here
     */

    // wrapper method for opening and printing the url
    public static void openUrl(ChromeDriver driver, String url) {

        try {

            driver.get(url);
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current Page URL: " + currentUrl);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // wrapper method for explicitly waiting for an element
    public static void waitForAnElement(ChromeDriver driver, By locator, int timeOutInSec) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // wrapper method to enter a text
    public static void enterText(ChromeDriver driver, By locator, String textToEnter) {

        try {

            WebElement element = driver.findElement(locator);
            Thread.sleep(500);
            element.clear();
            Thread.sleep(500);
            element.sendKeys(textToEnter);
            Thread.sleep(500);
            element.sendKeys(Keys.ENTER);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // wrapper method to click element when it is displayed and enabled
    public static void clickElement(ChromeDriver driver, By locator) {

        try {

            WebElement element = driver.findElement(locator);
            if (element.isDisplayed() && element.isEnabled()) {

                element.click();
                Thread.sleep(1000);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // wrapper method for click using Actions class
    public static void clickUsingActionsClass(ChromeDriver driver, By locator) {

        try {

            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // wrapper method to print the count of products having less than 0r equal to 4
    // star rating
    public static void checkCountFourStarOrLessRating(ChromeDriver driver, By locator) {

        try {

            int ratingCount = 0;
            List<String> allRatings = new ArrayList<>();
            List<WebElement> ratings = driver.findElements(locator);
            for (WebElement rating : ratings) {

                String ratingText = rating.getText();
                double ratingValue = Double.parseDouble(ratingText);

                allRatings.add(ratingText);

                if (ratingValue <= 4) {

                    ratingCount++;
                }
            }

            System.out.println("All Ratings available are:" + allRatings);
            System.out.println("The count of items with rating less than or equal to 4 stars:" + ratingCount);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // wrapper method to print the title when discount is more than 17%
    public static void fetchTitleWithDiscMoreThan17Perc(ChromeDriver driver, By discPercLocator, By titlesLocator2) {

        try {

            boolean value = false;
            List<WebElement> discPercElement = driver
                    .findElements(discPercLocator);
            List<WebElement> titlesElement = driver.findElements(titlesLocator2);

            for (int i = 0; i < discPercElement.size(); i++) {

                String discText = discPercElement.get(i).getText();
                String titleText = titlesElement.get(i).getText();
                String onlyDisc = discText.replace("% off", "");
                int discValue = Integer.parseInt(onlyDisc);

                if (discValue > 17) {

                    value = true;
                    System.out.println("The discount % is:" + discText);
                    System.out.println("The titles with more than 17% discount is:" + titleText);
                }
            }

            if (!value) {

                System.out.println("No titles available having discount more than 17%");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // wrapper class to print title,imageurls and reviews of top 5 rated products
    public static void fetchDetailsOfHighestRatedProduct(ChromeDriver driver, By productsLocator, By titlesLocator,
            By imageUrlsLocator, By reviewsLocator) {

        try {

            List<String> titles = new ArrayList<>();
            List<String> imageUrls = new ArrayList<>();
            List<Integer> reviews = new ArrayList<>();

            List<WebElement> products = driver.findElements(productsLocator);

            for (WebElement product : products) {

                try {
                    WebElement productTitle = product.findElement(titlesLocator);
                    String title = productTitle.getAttribute("title");

                    WebElement productImageUrl = product.findElement(imageUrlsLocator);
                    String imageUrl = productImageUrl.getAttribute("href");

                    WebElement productReview = product.findElement(reviewsLocator);
                    String reviewText = productReview.getText().replaceAll("[^\\d]", "");
                    int review = Integer.parseInt(reviewText);

                    titles.add(title);
                    imageUrls.add(imageUrl);
                    reviews.add(review);

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            for (int i = 0; i < reviews.size(); i++) {

                for (int j = i + 1; j < reviews.size(); j++) {

                    if (reviews.get(j) > reviews.get(i)) {

                        int tempReview = reviews.get(i);
                        reviews.set(i, reviews.get(j));
                        reviews.set(j, tempReview);

                        String tempTitle = titles.get(i);
                        titles.set(i, titles.get(j));
                        titles.set(j, tempTitle);

                        String tempUrl = imageUrls.get(i);
                        imageUrls.set(i, imageUrls.get(j));
                        imageUrls.set(j, tempUrl);

                    }

                }
            }

            for (int i = 0; i < 5; i++) {

                System.out.println("Top 5 titles based on reivew: " + titles.get(i));
                System.out.println("Top 5 imageUrls based on reivew: " + imageUrls.get(i));
                System.out.println("Top 5 reviews: " + reviews.get(i));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
