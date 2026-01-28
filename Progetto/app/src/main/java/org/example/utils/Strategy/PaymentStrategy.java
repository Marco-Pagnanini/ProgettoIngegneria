package org.example.utils.Strategy;

import org.example.Api.Models.Request.PaymentRequest;
import org.example.Core.enums.PaymentType;

public interface PaymentStrategy {
    boolean pay(PaymentRequest request);
    PaymentType getType();
}
