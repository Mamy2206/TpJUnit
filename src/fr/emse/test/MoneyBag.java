package fr.emse.test;

import java.util.Vector;

class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<Money>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money[] moneys) {
        for (Money m : moneys)
            appendMoney(m);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while ((i < fMonies.size())
                    && (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                fMonies.set(i, new Money(fMonies.get(i).amount() +
                        m.amount(),
                        m.currency()));
            }
        }
    }

    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    public IMoney addMoney(Money m) {
        MoneyBag newBag = new MoneyBag(this);
        newBag.appendMoney(m);
        return newBag.simplify();
    }

    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag newBag = new MoneyBag(this);
        for (Money m : mb.fMonies)
            newBag.appendMoney(m);
        return newBag.simplify();
    }

    public int amount() {
        int total = 0;
        for (Money m : fMonies)
            total += m.amount();
        return total;
    }

    public String currency() {
        if (fMonies.size() == 0)
            return null;
        return fMonies.get(0).currency();
    }

    private boolean isSimpleBag() {
        if (fMonies.size() == 0)
            return false;
        String currency = fMonies.get(0).currency();
        for (Money m : fMonies)
            if (!m.currency().equals(currency))
                return false;
        return true;
    }

    private Money extractSingleMoney() {
        if (fMonies.size() == 0)
            throw new RuntimeException("MoneyBag is empty");
        return new Money(amount(), currency());
    }

    private IMoney simplify() {
        if (isSimpleBag())
            return extractSingleMoney();
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        MoneyBag other = (MoneyBag)obj;
        if (fMonies.size() != other.fMonies.size()) return false;
        for (int i = 0; i < fMonies.size(); i++)
            if (!fMonies.get(i).equals(other.fMonies.get(i)))
                return false;
        return true;
    }
}