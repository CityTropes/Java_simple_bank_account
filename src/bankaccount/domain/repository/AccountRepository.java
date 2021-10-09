package bankaccount.domain.repository;

import bankaccount.domain.Account;

import java.util.List;

public interface AccountRepository {

    boolean hasAccountNumber(int accountNumber);


    Account getByAccountNumber(int accountNumber);

    Account[] getByAccountOwner(String accountOwner);



    // UUID createId();

    void save(Account account);


}
