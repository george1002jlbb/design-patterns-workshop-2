package com.example.patterns_banking.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountFactoryProvider {
  private final CheckingAccountFactory checkingAccountFactory;
  private final SavingsAccountFactory savingsAccountFactory;
  private final LowAmountAccountFactory lowAmountAccountFactory;

  public AccountFactoryProvider(CheckingAccountFactory checkingAccountFactory, SavingsAccountFactory savingsAccountFactory, LowAmountAccountFactory lowAmountAccountFactory) {
    this.checkingAccountFactory = checkingAccountFactory;
    this.savingsAccountFactory = savingsAccountFactory;
      this.lowAmountAccountFactory = lowAmountAccountFactory;
  }

  public AccountFactory getFactory(AccountType type) {
    Map<AccountType, AccountFactory> factories = Map.of(
      AccountType.CHECKING, checkingAccountFactory,
      AccountType.SAVINGS, savingsAccountFactory,
      AccountType.LOW_AMOUNT, lowAmountAccountFactory
    );

    AccountFactory factory = factories.get(type);
    if (factory == null) {
      throw new IllegalArgumentException("No factory found for account type: " + type);
    }
    return factory;
  }

  public enum AccountType {
    CHECKING,
    SAVINGS,
    LOW_AMOUNT,
  }
}
