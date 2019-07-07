package ro.utcn.fulgadan.BookStore.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.fulgadan.BookStore.business.validator.AgeValidator;
import ro.utcn.fulgadan.BookStore.business.validator.PhoneValidator;
import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;
import ro.utcn.fulgadan.BookStore.data.repository.ShippingJpaRepository;

@Service
public class ShippingService {

    @Autowired
    ShippingJpaRepository shippingJpaRepository;

    public void addNewShipping(ShippingInfo info) {
        shippingJpaRepository.save(info);
    }

    public boolean checkAge(ShippingInfo info) {
        AgeValidator validator = new AgeValidator();
        return validator.validate(info);
    }

    public boolean checkPhone(ShippingInfo info) {
        PhoneValidator validator = new PhoneValidator();
        return validator.validate(info);
    }

    public ShippingInfo findByPhone(String phone) {
        return shippingJpaRepository.findByPhone(phone);
    }

    public void updateShippingInfo(ShippingInfo info, String address, String phone, Integer age) {
        info.setAddress(address);
        info.setPhone(phone);
        info.setAge(age);
        shippingJpaRepository.save(info);
    }
}
