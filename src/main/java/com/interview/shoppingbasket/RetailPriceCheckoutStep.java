package com.interview.shoppingbasket;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;
    private double basketTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;
        basketTotal = 0.0;

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity*price;
            basketTotal += quantity*price;
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
        checkoutContext.setBasketTotal(basketTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        if(promotion != null && promotion.getProductCode().equals(item.getProductCode())) {
            if(promotion.getType().equals("2for1")) {
                int discountedQuantity = item.getQuantity() / 2;
                price = discountedQuantity * price;
            }
            if(promotion.getType().equals("50%off")) {
                price = price * 0.5;
            }
            if(promotion.getType().equals("10%off")) {
                price = price * 0.9;
            }
        }
        return price;
    }
}
