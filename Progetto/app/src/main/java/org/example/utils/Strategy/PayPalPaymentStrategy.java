package org.example.utils.Strategy;

import org.example.Api.Models.Request.PaymentRequest;
import org.example.Core.models.Team;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {
    private final IUnitOfWork unitOfWork;
    public PayPalPaymentStrategy(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public boolean pay(PaymentRequest request) {
        Team team = unitOfWork.teamRepository().getById(request.getTeam().getId());
        team.setSaldo(team.getSaldo() + request.getAmount());
        unitOfWork.teamRepository().update(team);
        unitOfWork.saveChanges();
        return true;
    }

}