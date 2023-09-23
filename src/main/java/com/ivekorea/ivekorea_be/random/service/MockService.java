//package com.ivekorea.ivekorea_be.random.service;
//
//import com.ivekorea.ivekorea_be.random.crawling.KakaoGiftCrawler;
//import com.ivekorea.ivekorea_be.random.entity.Benefit;
//import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
//import com.ivekorea.ivekorea_be.random.entity.Category;
//import com.ivekorea.ivekorea_be.random.level.Level;
//import com.ivekorea.ivekorea_be.random.repository.BenefitInfoRepository;
//import com.ivekorea.ivekorea_be.random.repository.BenefitRepository;
//import com.ivekorea.ivekorea_be.random.repository.CategoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MockService {
//
//    private final CategoryRepository categoryRepository;
//    private final BenefitRepository benefitRepository;
//    private final BenefitInfoRepository benefitInfoRepository;
//
//    private final KakaoGiftCrawler kakaoGiftCrawler;
//
//    private final List<String> categoryList = Arrays.asList("카페", "베이커리", "편의점");
//    private final List<String> cafeList = Arrays.asList("공차", "메가MGC커피", "서촌핫플패스", "설빙", "성수핫플패스", "스타벅스", "엔제리너스", "이디야커피", "익선핫플패스", "컴포즈커피", "텐퍼센트커피", "투썸플레이스", "파스쿠찌", "할리스");
//    private final List<String> bakeryList = Arrays.asList("던킨", "뚜레쥬르", "파리바게뜨", "한스케익");
//    private final List<String> convenienceStoreList = Arrays.asList("세븐일레븐", "이마트24", "CU", "GS25");
//
//    @Transactional
//    public String saveMock() {
//        List<Category> categories = saveCategory();
//        List<Benefit> benefits = saveBenefit(categories);
//        saveBenefitInfo(benefits);
//
//        return "ok";
//    }
//
//
//    private List<Category> saveCategory() {
//        List<Category> list = new ArrayList<>();
//
//        for (String category : categoryList) {
//            list.add(new Category(category));
//        }
//
//        return categoryRepository.saveAll(list);
//    }
//
//    private List<Benefit> saveBenefit(List<Category> categories) {
//        List<Benefit> list = new ArrayList<>();
//
//        for (Category category : categories) {
//            switch (category.getName()) {
//                case "카페":
//                    addBenefitList(list, category, cafeList);
//                    break;
//                case "베이커리":
//                    addBenefitList(list, category, bakeryList);
//                    break;
//                case "편의점":
//                    addBenefitList(list, category, convenienceStoreList);
//                    break;
//                default:
//            }
//        }
//
//        return benefitRepository.saveAll(list);
//    }
//
//    private void saveBenefitInfo(List<Benefit> benefits) {
//        List<BenefitInfo> benefitInfos = new ArrayList<>();
//
//        int categoryListSize = categoryList.size();
//        for (int i = 0; i < categoryListSize; i++) {
//
//            switch (i) {
//                case 0:
//                    getBenefitInfoList(benefits, benefitInfos, cafeList, i);
//                    break;
//                case 1:
//                    getBenefitInfoList(benefits, benefitInfos, bakeryList, i);
//                    break;
//                case 2:
//                    getBenefitInfoList(benefits, benefitInfos, convenienceStoreList, i);
//                    break;
//                default:
//            }
//
//        }
//
//        benefitInfoRepository.saveAll(benefitInfos);
//    }
//
//
//    private void addBenefitList(List<Benefit> list, Category category, List<String> brandList) {
//        for (String brand : brandList) {
//            list.add(new Benefit(Level.HIGH, category, brand));
//            list.add(new Benefit(Level.MIDDLE, category, brand));
//            list.add(new Benefit(Level.LOW, category, brand));
//        }
//    }
//
//    private void getBenefitInfoList(List<Benefit> benefits, List<BenefitInfo> benefitInfos, List<String> brandList, int categoryIdx) {
//        int listSize = brandList.size();
//        for (int j = 0; j < listSize; j++) {
//            List<List<String>> productList = kakaoGiftCrawler.getProductList(kakaoGiftCrawler.webDriver(categoryIdx, j));
//
//
//            for (List<String> product : productList) {
//                int benefitIdx = -99;
//
//                Level level = setLevel(Integer.parseInt(product.get(2)));
//
//                switch (categoryIdx) {
//                    case 0:
//                        assert level != null;
//                        benefitIdx = j * 3 + level.getLev();
//                        break;
//                    case 1:
//                        assert level != null;
//                        benefitIdx = (cafeList.size() + j) * 3 + level.getLev();
//                        break;
//                    case 2:
//                        assert level != null;
//                        benefitIdx = (cafeList.size() + bakeryList.size() + j) * 3 + level.getLev();
//                        break;
//                    default:
//                }
//
//                benefitInfos.add(new BenefitInfo(benefits.get(benefitIdx), product.get(0), product.get(1), Integer.parseInt(product.get(2))));
//            }
//        }
//
//    }
//
//
//    private Level setLevel(int salePrice) {
//        if (Level.HIGH.getMinPrice() < salePrice) {
//            return Level.HIGH;
//        } else if (Level.MIDDLE.getMinPrice() < salePrice) {
//            return Level.MIDDLE;
//        } else if (Level.LOW.getMinPrice() < salePrice) {
//            return Level.LOW;
//        }
//
//        return null;
//    }
//
//}
