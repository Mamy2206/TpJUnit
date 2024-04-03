package fr.emse.test;

public interface IMoney {
    public IMoney add(IMoney aMoney);
    public IMoney addMoney(Money aMoney);
    public IMoney addMoneyBag(MoneyBag aMoneyBag);
    public String currency();
    public int amount();

}
