package demo.wrappers;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Wrappers {
    public void clk(WebElement ele) {
        try {
            ele.click();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void sendString(WebElement ele, String s) {

        try {
            ele.clear();
            ele.sendKeys(s);
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    public void pressEnter(WebElement ele) {

        try {
            ele.sendKeys(Keys.ENTER);
            ele.clear();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public int getPages(WebElement pages) {

        try {
            int count = Integer
                    .parseInt(pages.getText().substring(pages.getText().length() - 2, pages.getText().length()));
            return count;
        } catch (Exception E) {
            E.printStackTrace();
            return -1;
        }

    }

    public void moveToEle(WebElement ele, WebDriver driver) {

        try {
            Actions action = new Actions(driver);
            action.moveToElement(ele);
        } catch (Exception E) {
            E.printStackTrace();

        }
    }

    public void rating(int size, WebDriver driver/* , int pages_count */) throws InterruptedException {

        // for(int j=2;j<=pages_count;j++){

        // System.out.println(product.size());
        int count = 0;
        for (int i = 1; i <= size; i++) {

            WebElement ele = driver.findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12']["
                    + (i) + "]//a//span[contains(@id,'productRating')]/div"));

            // System.out.println(ele.getText());

            Thread.sleep(500);

            Float rating = Float.parseFloat(ele.getText());
            if (rating <= 4.0) {
                count++;

            }
        }
        System.out.println("Number of washing machine with rating <=4.0:" + count);
        /*
         * Thread.sleep(1000);
         * driver.findElement(By.
         * xpath("//div[@class='_1G0WLw']/nav[@class='WSL9JP']/a[contains(@class,'cn++Ap') and text()='"
         * +(j)+"']")).click();
         * FluentWait<WebDriver> wait = new FluentWait<>(driver)
         * .withTimeout(Duration.ofSeconds(5))
         * .pollingEvery(Duration.ofMillis(100))
         * .ignoring(Exception.class);
         * 
         * wait.until(ExpectedConditions.urlContains("washing"));
         * }
         */
    }

    public void iphone(int size, WebDriver driver) throws InterruptedException {
        for (int i = 1; i <= size; i++) {

            String title = driver.findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12']["
                    + (i) + "]//div[@class='yKfJKb row']/div/div[@class='KzDlHZ']")).getText();

            WebElement discount = driver
                    .findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12'][" + (i)
                            + "]//div[@class='col col-5-12 BfVC2z']/div/div[1]//div[@class='UkUFwK']/span"));

            Thread.sleep(500);
            int disc = 0;
            try {
                String dis = discount.getText().replaceAll("[^0-9]", "");
                disc = Integer.parseInt(dis);
            } catch (Exception E) {
                // disc = Integer.parseInt(discount.getText().substring(0, 1));
            }

            if (disc > 17)
                System.out.println("Title: " + title + " with discount: " + disc);
        }
    }

    public void coffee_mug(int size, WebDriver driver) throws InterruptedException {
        WebElement title;
        WebElement image_url;
        WebElement ratings;
        int review_count;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            List<WebElement> product = driver.findElements(By.xpath(
                    "//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12'][" + (i)
                            + "]/div[@class='_75nlfW']/div"));
            Thread.sleep(500);
            for (int j = 1; j <= product.size(); j++) {
                 title = driver
                        .findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12'][" + (i)
                                + "]/div[@class='_75nlfW']/div[" + (j) + "]//div/a[2]"));
                Thread.sleep(500);
                 image_url = driver
                        .findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12'][" + (i)
                                + "]/div[@class='_75nlfW']/div[" + (j) + "]//div/a//img"));
                Thread.sleep(500);
                 ratings = driver
                        .findElement(By.xpath("//div[@class='DOjaWF gdgoEp']/div[@class='cPHDOP col-12-12'][" + (i)
                                + "]/div[@class='_75nlfW']/div[" + (j) + "]//div[@class='_5OesEi afFzxY']/span[2]"));
                Thread.sleep(500);
                 review_count = Integer.parseInt(ratings.getText().replaceAll("[^0-9]", ""));
                /*System.out.println(review_count);
                System.out.println("Title of product " + i * j + " " + title.getText());
                System.out.println("Image URL of product " + i * j + " " + image_url.getAttribute("src"));
                System.out.println();*/
                if (review_count != 0)
                map.put("Title of product " + " " + title.getText() + " " + "Image URL of product " + " "
                        + image_url.getAttribute("src"), review_count);
            }
        
               
        }

                PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
                    public int compare(String w1, String w2) {
                        int f1 = map.get(w1);
                        int f2 = map.get(w2);

                        if (f1 == f2) {
                            return w1.compareTo(w2);
                        }
                        return f2 - f1;
                    }
                });
                for (String word : map.keySet()) {
                    pq.add(word);
                }

                for (int k = 0; k <5 && !pq.isEmpty(); k++) {
                    System.out.println(pq.poll());
                    Thread.sleep(1000);
                }

               
            }
        
        }
    


