package com.wecare.userservice.utility;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserIdGenerator implements IdentifierGenerator {
    private static int counter = 1001;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        int id = counter++;
        LocalDateTime now = LocalDateTime.now();
        String value = "U" + now.getDayOfMonth() + now.getMonthValue() + now.getYear() + now.getHour()
                + now.getMinute();
        return value + id;
    }
}
