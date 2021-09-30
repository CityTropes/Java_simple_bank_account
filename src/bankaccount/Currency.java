package bankaccount;


public enum Currency {
    EUR,
    USD,
    GBP,
    BTC;

    protected static boolean enumContainsValue(String curr) {
        for (Currency item : Currency.values()) {
            if (item.name().equals(curr)) {
                return true;
            }
        }
        return false;
    }
}


