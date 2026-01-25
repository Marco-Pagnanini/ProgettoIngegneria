package org.example;

import org.example.Api.Controllers.UserController;
import org.example.Api.Models.Request.UserRequest;
import org.example.Application.Service.UserService;
import org.example.Application.Validator.UserValidator;
import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Invito;
import org.example.Core.models.User;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConsultaInvitiUtenti {

    private UserController userController;
    private UserService userService;
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
                new ValutazioneRepository()
        );

        userService = new UserService(unitOfWork, new UserValidator(unitOfWork));
        userController = new UserController(userService);
    }

    @Test
    public void testConsultaInvitiListaVuota() {
        UserRequest userRequest = createValidUserRequest();
        User user = userController.registerUser(userRequest);

        Assert.assertNotNull(user);

        List<Invito> inviti = userController.consultaInviti(user.getId());

        Assert.assertNotNull(inviti);
        Assert.assertTrue(inviti.isEmpty());
    }

    @Test
    public void testConsultaInvitiConInviti() {
        UserRequest userRequest = createValidUserRequest();
        User user = userController.registerUser(userRequest);

        Assert.assertNotNull(user);

        Invito invito = createInvito(user);
        unitOfWork.invitoRepository().create(invito);
        user.getInviti().add(invito);
        unitOfWork.userRepository().update(user);
        unitOfWork.saveChanges();

        List<Invito> inviti = userController.consultaInviti(user.getId());

        Assert.assertNotNull(inviti);
        Assert.assertEquals(1, inviti.size());
    }

    @Test
    public void testConsultaInvitiMultipleInviti() {
        UserRequest userRequest = createValidUserRequest();
        User user = userController.registerUser(userRequest);

        Invito invito1 = createInvito(user);

        Invito invito2 = createInvito(user);

        unitOfWork.invitoRepository().create(invito1);
        unitOfWork.invitoRepository().create(invito2);

        user.getInviti().add(invito1);
        user.getInviti().add(invito2);
        unitOfWork.userRepository().update(user);
        unitOfWork.saveChanges();

        List<Invito> inviti = userController.consultaInviti(user.getId());

        Assert.assertNotNull(inviti);
        Assert.assertEquals(2, inviti.size());
    }


    private UserRequest createValidUserRequest() {
        UserRequest request = new UserRequest();
        request.setNome("Mario");
        request.setCognome("Rossi");
        request.setEmail("mario.rossi@email.com");
        request.setPassword("Password123!");
        request.setCellulare("1234567890");
        return request;
    }

    private Invito createInvito(User destinatario) {
        Invito invito = new Invito();
        invito.setPerUtente(destinatario);
        invito.setStato(StatoInvito.PENDENTE);
        return invito;
    }
}