package ro.utcn.fulgadan.BookStore.business.validator;

import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;

public class AgeValidator implements Validator<ShippingInfo> {

    private static final int MIN_AGE = 12;
    private static final int MAX_AGE = 90;

    @Override
    public boolean validate(ShippingInfo shippingInfo) {
        if (shippingInfo.getAge() >= MIN_AGE && shippingInfo.getAge() <= MAX_AGE)
            return true;
        else
            return false;
    }
}
