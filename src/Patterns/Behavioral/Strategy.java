package Patterns.Behavioral;

/*
Intent: choose an algorithm at runtime with minial client changes

Components:
- Strategy interface
- Concrete strategies
- Context: uses strategy interface and not any concrete strategy directly
- Client

 */

import java.io.IOException;

class Strategy {






    interface PaymentStrategy {
        void pay(int amount);
    }

    static class PhonePe implements PaymentStrategy {
        public void pay(int amt) {
            System.out.println(amt+" amount paid via Phonepe");
        }
    }

    static class GPay implements PaymentStrategy {
        public void pay(int amt) {
            System.out.println(amt+" amount paid via Gpay");
        }
    }


    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;

        ShoppingCart(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void checkout(int amt) {
            paymentStrategy.pay(amt);
        }
    }


    public static void main(String args[]) throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart(new PhonePe());
        shoppingCart.checkout(1000);
        shoppingCart.setPaymentStrategy(new GPay());
        shoppingCart.checkout(215);
    }





}