//package com.ivekorea.ivekorea_be.random.crawling;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class KakaoGiftCrawler {
//
//    private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    private final String WEB_DRIVER_PATH = "/Users/jeondabeen/Downloads/chromedriver_mac_arm64/chromedriver";
//
//    private final List<Integer> categoryNumberList = Arrays.asList(1, 2, 8);
//    private final List<Integer> cafeNumberList = Arrays.asList(1924, 37492, 84572, 1994, 84145, 2003, 2015, 84741, 84729, 37235, 82727, 2071, 2077, 41960);
//    private final List<Integer> bakeryNumberList = Arrays.asList(1939, 1945, 2075, 2091);
//    private final List<Integer> convenienceStoreNumberList = Arrays.asList(1389, 37232, 2117, 1919);
//
//
//    public WebDriver webDriver(int categoryIdx, int brandIdx) {
//        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("headless");
//
//        WebDriver webDriver = new ChromeDriver(chromeOptions);
//
//        int brandNumber = switch (categoryIdx) {
//            case 0 -> cafeNumberList.get(brandIdx);
//            case 1 -> bakeryNumberList.get(brandIdx);
//            case 2 -> convenienceStoreNumberList.get(brandIdx);
//            default -> -99;
//        };
//
//        webDriver.get("https://giftbiz.kakao.com/category/list?itemType=VOUCHER&categoryIds=" +
//                categoryNumberList.get(categoryIdx) +
//                "&giftProductBrandIds=" +
//                brandNumber +
//                "&price=%7B%22from%22:%22%22,%22to%22:%22%22%7D&page=0&size=20&sortProperty=price&sortDirection=desc&listSort=score");
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.getStackTrace();
//        }
//
//        return webDriver;
//    }
//
//    public List<List<String>> getProductList(WebDriver webDriver) {
//        List<List<String>> productLists = new ArrayList<>();
//        List<WebElement> webElementList = webDriver.findElements(By.tagName("shop-view-product-item"));
//
//        for (WebElement element : webElementList) {
//            List<String> productList = new ArrayList<>();
//
//            productList.add(element.findElement(By.tagName("a")).getAttribute("data-tiara-name"));
//            productList.add(element.findElement(By.tagName("a")).getAttribute("data-tiara-image"));
//            productList.add(element.findElement(By.className("area_price")).findElement(By.className("txt_number")).getText().replace(",", ""));
//
//            productLists.add(productList);
//        }
//
//        return productLists;
//    }
//
//}
