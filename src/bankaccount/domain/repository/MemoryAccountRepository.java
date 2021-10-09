package bankaccount.domain.repository;

import bankaccount.domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MemoryAccountRepository implements AccountRepository {

    private final Map<Integer, Account> accounts = new HashMap<>();



    @Override
    public boolean hasAccountNumber(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    @Override
    public Account getByAccountNumber(int accountNumber) {
        if (!hasAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Account does not exists.");
        }
        return accounts.get(accountNumber);
    }

    @Override
    public Account[] getByAccountOwner(String accountOwner) {
        List<Account> results = new ArrayList<>();
        for (Account account : accounts.values()) {     // use .values or .keySet on Map to loop over the elements
            if (account.getAccountOwner().equals(accountOwner)) {
                results.add(account);
            }
        }
        return results.toArray(new Account[0]); // convert List<Account> to Account[]  (list to array)
    }



    @Override
    public void save(Account account) {
        if (hasAccountNumber(account.getAccountNumber())) {
            if (accounts.get(account.getAccountNumber()) != account) {
                throw new IllegalStateException("Duplicate entity!");
            }
        } else {
            accounts.put(account.getAccountNumber(), account);
        }
    }

}
