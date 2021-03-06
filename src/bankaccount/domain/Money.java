package bankaccount.domain;

/**
 * Immutable object Money. Cannot be changed internally. Create a new instance for every new value.
 */
public final class Money {

    private final int cent;
    private final Currency currency;

    // Factory method pattern (that creates instances)
    public static Money createFromCent(int value, Currency currency) {
        return new Money(value, currency);
    }

    public static Money createFromBase(double value, Currency currency) {
        return new Money((int) Math.floor(value * 100), currency);
    }

    // Constructor is private, so you're forced to use the static factory methods.
    private Money(int value, Currency currency) {
        if (value < 0) {
            throw new IllegalArgumentException("Money cannot be below zero.");
        }
        this.cent = value;
        this.currency = currency;
    }



    public double getBase() {
        return cent / 100d;
    }

    public int getCent() {
        return cent;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isNotZero() {
        return cent != 0;
    }

    public boolean isSameCurrency(Money other) {
        return other.currency == currency;
    }

    public Money add(Money money) {
        assertSameCurrency(money);
        return new Money(cent + money.cent, currency);
    }

    public Money sub(Money money) {
        assertSameCurrency(money);
        return new Money(cent - money.cent, currency);
    }

    public Money multiply(double multiplier) {
        return new Money((int) Math.floor(cent * multiplier), currency);
    }

    public boolean greaterThan(Money other) { // gt()
        assertSameCurrency(other);
        return cent > other.cent;
    }

    public boolean greaterThanOrEqual(Money other) { // gte()
        assertSameCurrency(other);
        return cent >= other.cent;
    }

    public boolean lessThan(Money other) { // lt()
        assertSameCurrency(other);
        return cent < other.cent;
    }

    public boolean lessThanOrEqual(Money other) { // lte()
        assertSameCurrency(other);
        return cent <= other.cent;
    }

    private void assertSameCurrency(Money other) {
        if (other.currency != currency) {
            throw new IllegalArgumentException("Can only add in same currency (" + currency + " and " + other.currency + ")");
        }
    }


    @Override
    public String toString() {
        return String.format("%.2f %s", getBase(), currency);
    }

}

        /*
        int five = 5;
        Integer five = new Integer(5);

        int sum = five + 5;
        Integer sum = new Integer(five + 5);
        */
