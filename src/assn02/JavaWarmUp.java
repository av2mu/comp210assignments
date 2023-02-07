package assn02;

import java.util.Scanner;

public class JavaWarmUp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        Product[] products = new Product[amount];
        for (int i = 0; i < amount; i++) {
            products[i] = new Product();
            products[i].set_date(input.next());
            products[i].set_assembleTime(input.next());
            products[i].set_category(input.next());
            products[i].set_fee(input.nextFloat());
            products[i].set_quantity(input.nextInt());
            products[i].set_time(input.nextFloat());
            products[i].set_cost(input.nextFloat());
        }

        Product highestFee = new Product();
        highestFee = products[0];
        for (int i = 0; i < amount; i++) {
            if (products[i].get_fee() > highestFee.get_fee()) {
                highestFee = products[i];
            }
        }
        System.out.printf("Highest per unit assembling fee:\n\tWhen: %s %s\n\tCategory: %s\n\tPrice: %.2f\n", highestFee.get_date(), highestFee.get_assembleTime(), highestFee.get_category(), highestFee.get_fee());

        Product lowestFee = new Product();
        lowestFee = products[0];
        for (int i = 0; i < amount; i++) {
            if (products[i].get_fee() < lowestFee.get_fee()) {
                lowestFee = products[i];
            }
        }
        System.out.printf("Lowest per unit assembling fee:\n\tWhen: %s %s\n\tCategory: %s\n\tPrice: %.2f\n", lowestFee.get_date(), lowestFee.get_assembleTime(), lowestFee.get_category(), lowestFee.get_fee());

        Product totalPhone = new Product();
        float averagePhoneFee = 0;
        float averagePhoneProfit = 0;
        int phoneCounter = 0;
        for (int i = 0; i < amount; i++) {
            if (products[i].get_category().equals("phone")) {
                totalPhone.set_quantity(totalPhone.get_quantity() + products[i].get_quantity());
                totalPhone.set_fee(totalPhone.get_fee() + ((products[i].get_fee())*(products[i].get_quantity())));
                totalPhone.set_time(totalPhone.get_time() + products[i].get_time());
                totalPhone.set_cost(totalPhone.get_cost() + products[i].get_cost());
                phoneCounter++;
            }
        }
        if (phoneCounter > 0) {
            averagePhoneFee = totalPhone.get_fee() / totalPhone.get_quantity();
            averagePhoneProfit = (totalPhone.get_fee() - (totalPhone.get_cost() + (16 * totalPhone.get_time()))) / totalPhone.get_quantity();
        }
        System.out.printf("Statistic of phone\n\tQuantity: %s\n\tAverage Assembling fee: %.2f\n\tAverage Net Profit: %.2f\n" , totalPhone.get_quantity(),  averagePhoneFee, averagePhoneProfit);

        Product totalLaptop = new Product();
        float averageLaptopFee = 0;
        float averageLaptopProfit = 0;
        int laptopCounter = 0;
        for (int i = 0; i < amount; i++) {
            if (products[i].get_category().equals("laptop")) {
                totalLaptop.set_quantity(totalLaptop.get_quantity() + products[i].get_quantity());
                totalLaptop.set_fee(totalLaptop.get_fee() + ((products[i].get_fee())*(products[i].get_quantity())));
                totalLaptop.set_time(totalLaptop.get_time() + products[i].get_time());
                totalLaptop.set_cost(totalLaptop.get_cost() + products[i].get_cost());
                laptopCounter++;
            }
        }
        if (laptopCounter > 0) {
            averageLaptopFee = totalLaptop.get_fee() / totalLaptop.get_quantity();
            averageLaptopProfit = (totalLaptop.get_fee() - (totalLaptop.get_cost() + (16 * totalLaptop.get_time()))) / totalLaptop.get_quantity();
        }
        System.out.printf("Statistic of laptop\n\tQuantity: %s\n\tAverage Assembling fee: %.2f\n\tAverage Net Profit: %.2f\n", totalLaptop.get_quantity(), averageLaptopFee, averageLaptopProfit);

        Product totalSmartWatch = new Product();
        float averageSmartWatchFee = 0;
        float averageSmartWatchProfit = 0;
        int smartWatchCounter = 0;
        for (int i = 0; i < amount; i++) {
            if (products[i].get_category().equals("smart_watch")) {
                totalSmartWatch.set_quantity(totalSmartWatch.get_quantity() + products[i].get_quantity());
                totalSmartWatch.set_fee(totalSmartWatch.get_fee() + (products[i].get_fee())*(products[i].get_quantity()));
                totalSmartWatch.set_time(totalSmartWatch.get_time() + products[i].get_time());
                totalSmartWatch.set_cost(totalSmartWatch.get_cost() + products[i].get_cost());
                smartWatchCounter++;
            }
        }
        if (smartWatchCounter > 0) {
            averageSmartWatchFee = totalSmartWatch.get_fee() / totalSmartWatch.get_quantity();
            averageSmartWatchProfit = (totalSmartWatch.get_fee() - (totalSmartWatch.get_cost() + (16 * totalSmartWatch.get_time()))) / totalSmartWatch.get_quantity();
        }
        System.out.printf("Statistic of smart_watch\n\tQuantity: %s\n\tAverage Assembling fee: %.2f\n\tAverage Net Profit: %.2f", totalSmartWatch.get_quantity(), averageSmartWatchFee, averageSmartWatchProfit);
    }
}

