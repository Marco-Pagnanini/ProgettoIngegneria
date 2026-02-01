package org.example.utils.Strategy;

import org.example.Api.Models.Request.PaymentRequest;
import org.example.Core.enums.PaymentType;

/**
 * Pattern Strategy : usato per gestire i vari metodi di pagamento a runtime
 * il sistema prevede diversi tipi di pagamento, ma cambiano a runtime
 * col pattern strategy possiamo cambiare dinamicamente la strategia per pagare
 *
 * Nelle implementazioni si gestisce una semplice transazione a database per il saldo
 */
public interface PaymentStrategy {
    boolean pay(PaymentRequest request);
    PaymentType getType();
}
