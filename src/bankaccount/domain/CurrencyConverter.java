package bankaccount.domain;

import java.util.Map;


public final class CurrencyConverter {

    //public static final Currency BASE_CURRENCY = Currency.EUR;

    public static final Map<Currency, Double> conversions = Map.of(
        Currency.EUR, 1d,
        Currency.USD, 1.17,
        Currency.GBP, 0.86
    );


    public static Money convert(Money money, Currency targetCurrency) {
        if (money.getCurrency() == targetCurrency) {
            return money;
        }
        if (!conversions.containsKey(targetCurrency)) {
            throw new IllegalArgumentException("No conversion for currency " + targetCurrency);
        }

        double conversionRate = conversions.get(targetCurrency) / conversions.get(money.getCurrency());
        int cent = (int) Math.floor(money.getCent() * conversionRate);

        return Money.fromCent(cent, targetCurrency);
    }

}
