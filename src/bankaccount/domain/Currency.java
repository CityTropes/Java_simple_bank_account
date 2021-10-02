package bankaccount.domain;


public enum Currency {
    EUR,
    USD,
    GBP,
    BTC;

    public static boolean isValid(String curr) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(curr)) {
                return true;
            }
        }
        return false;
    }
}


