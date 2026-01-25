package org.example;

import org.example.Api.Controllers.UserStaffController;
import org.example.Api.Models.Request.UserLoginRequest;
import org.example.Application.Service.UserStaffService;
import org.example.Core.models.UserStaff;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccessoAllaPiattaformaTest {

    private UserStaffController controller;
    private UserStaffService service;
    private UnitOfWork unitOfWork;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new SegnalazioneRepository(),
                new UserRepository(),
                new SottoMissioniRepository(),
                new RispostaRepository(),
                new ValutazioneRepository(),
                new UserStaffRepository()
        );

        service = new UserStaffService(unitOfWork);
        controller = new UserStaffController(service);
    }

    @Test
    public void testAccessoConSuccesso() {
        UserStaff userStaff = createUserStaff();
        unitOfWork.userStaffRepository().create(userStaff);
        unitOfWork.saveChanges();

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("staff@email.com");
        loginRequest.setPassword("Password123!");

        UserStaff result = controller.accesso(loginRequest);

        Assert.assertNotNull(result);
        Assert.assertEquals("staff@email.com", result.getEmail());
    }

    @Test
    public void testAccessoEmailNonTrovata() {
        UserStaff userStaff = createUserStaff();
        unitOfWork.userStaffRepository().create(userStaff);
        unitOfWork.saveChanges();

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("nonexistent@email.com");
        loginRequest.setPassword("Password123!");

        UserStaff result = controller.accesso(loginRequest);

        Assert.assertNull(result);
    }

    @Test
    public void testAccessoRepositoryVuoto() {
        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("staff@email.com");
        loginRequest.setPassword("Password123!");

        UserStaff result = controller.accesso(loginRequest);

        Assert.assertNull(result);
    }

    @Test
    public void testVisitaProfiloConSuccesso() {
        UserStaff userStaff = createUserStaff();
        userStaff.setId(1L);
        unitOfWork.userStaffRepository().create(userStaff);
        unitOfWork.saveChanges();

        UserStaff result = controller.visitaProfilo(1L);

        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1L), result.getId());
        Assert.assertEquals("Mario", result.getNome());
    }

    @Test
    public void testVisitaProfiloNonTrovato() {
        UserStaff result = controller.visitaProfilo(9999L);

        Assert.assertNull(result);
    }

    private UserStaff createUserStaff() {
        UserStaff userStaff = new UserStaff();
        userStaff.setId(1L);
        userStaff.setNome("Mario");
        userStaff.setCognome("Rossi");
        userStaff.setEmail("staff@email.com");
        userStaff.setPassword("Password123!");
        return userStaff;
    }
}