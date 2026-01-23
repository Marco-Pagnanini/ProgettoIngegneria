package org.example;

import org.example.Api.Controllers.UserController;
import org.example.Api.Models.Request.UserRequest;
import org.example.Application.Service.UserService;
import org.example.Application.Validator.UserValidator;
import org.example.Core.models.User;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test suite per la registrazione degli utenti
 *
 * @author Marco Pagnanini
 */
public class RegistrazioneUserTest {

    private UserController controller;
    private UserValidator validator;
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
        validator = new UserValidator(
            unitOfWork
        );
        controller = new UserController(
                new UserService(
                        unitOfWork,
                        validator
                )
        );
    }

    @Test
    public void testRegistrazioneUtenteValido() {
        UserRequest request = createValidRequest();

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("Marco", response.getNome());
        Assert.assertEquals("Rossi", response.getCognome());
        Assert.assertEquals("marco.rossi@example.com", response.getEmail());
    }

    @Test
    public void testRegistrazioneEmailDuplicata() {
        UserRequest request1 = createValidRequest();
        UserRequest request2 = createValidRequest();
        request2.setNome("Giovanni");
        request2.setCognome("Bianchi");
        request2.setCellulare("3339876543");

        User firstUser = controller.registerUser(request1);
        User duplicateUser = controller.registerUser(request2);

        Assert.assertNotNull(firstUser);
        Assert.assertNull(duplicateUser);
    }

    @Test
    public void testRegistrazioneEmailVuota() {
        UserRequest request = createValidRequest();
        request.setEmail("");

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazionePasswordVuota() {
        UserRequest request = createValidRequest();
        request.setPassword("");

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazionePasswordTroppoCorta() {
        UserRequest request = createValidRequest();
        request.setPassword("12345"); // 5 caratteri

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazionePasswordTroppoLunga() {
        UserRequest request = createValidRequest();
        request.setPassword("12345678901234567"); // 17 caratteri

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazionePasswordLimiteMinimo() {
        UserRequest request = createValidRequest();
        request.setPassword("123456"); // Esattamente 6 caratteri

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("123456", response.getPassword());
    }

    @Test
    public void testRegistrazionePasswordLimiteMassimo() {
        UserRequest request = createValidRequest();
        request.setEmail("test.limite@example.com");
        request.setPassword("1234567890123456"); // Esattamente 16 caratteri

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("1234567890123456", response.getPassword());
    }

    @Test
    public void testRegistrazioneCellulareNoveCaratteri() {
        UserRequest request = createValidRequest();
        request.setCellulare("123456789"); // 9 cifre

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneCellulareUndiciCaratteri() {
        UserRequest request = createValidRequest();
        request.setCellulare("12345678901"); // 11 cifre

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneCellulareValido() {
        UserRequest request = createValidRequest();
        request.setCellulare("3331234567"); // Esattamente 10 cifre

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("3331234567", response.getCellulare());
    }

    @Test
    public void testRegistrazioneNomeVuoto() {
        UserRequest request = createValidRequest();
        request.setNome("");

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneNomeNull() {
        UserRequest request = createValidRequest();
        request.setNome(null);

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneCognomeVuoto() {
        UserRequest request = createValidRequest();
        request.setCognome("");

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneCognomeNull() {
        UserRequest request = createValidRequest();
        request.setCognome(null);

        User response = controller.registerUser(request);

        Assert.assertNull(response);
    }

    @Test
    public void testRegistrazioneMultipliUtenti() {
        UserRequest request1 = createValidRequest();

        UserRequest request2 = createValidRequest();
        request2.setEmail("giovanni.bianchi@example.com");
        request2.setNome("Giovanni");
        request2.setCognome("Bianchi");
        request2.setCellulare("3339876543");

        UserRequest request3 = createValidRequest();
        request3.setEmail("anna.verdi@example.com");
        request3.setNome("Anna");
        request3.setCognome("Verdi");
        request3.setCellulare("3338765432");

        User user1 = controller.registerUser(request1);
        User user2 = controller.registerUser(request2);
        User user3 = controller.registerUser(request3);

        Assert.assertNotNull(user1);
        Assert.assertNotNull(user2);
        Assert.assertNotNull(user3);
    }

    @Test
    public void testUserNull() {
        boolean isValid = validator.validate(null);

        Assert.assertFalse(isValid);
    }

    @Test
    public void testPasswordConCaratteriSpeciali() {
        UserRequest request = createValidRequest();
        request.setPassword("Pass@123!");

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testEmailFormatoParticolare() {
        UserRequest request = createValidRequest();
        request.setEmail("user+tag@domain.co.uk");

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("user+tag@domain.co.uk", response.getEmail());
    }

    @Test
    public void testNomeConAccenti() {
        UserRequest request = createValidRequest();
        request.setNome("José");

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("José", response.getNome());
    }

    @Test
    public void testDataNascitaSalvataCorrettamente() {
        UserRequest request = createValidRequest();
        LocalDate dataNascita = LocalDate.of(1990, 3, 15);
        request.setDataDiNascita(dataNascita);

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(dataNascita, response.getDataNascita());
    }

    @Test
    public void testCampiAlLimite() {
        UserRequest request = createValidRequest();
        request.setPassword("1234567890123456"); // 16 caratteri
        request.setCellulare("1234567890"); // 10 cifre

        User response = controller.registerUser(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(16, response.getPassword().length());
        Assert.assertEquals(10, response.getCellulare().length());
    }

    private UserRequest createValidRequest() {
        UserRequest request = new UserRequest();

        request.setEmail("marco.rossi@example.com");
        request.setPassword("password123");
        request.setNome("Marco");
        request.setCognome("Rossi");
        request.setCellulare("3331234567");
        request.setDataDiNascita(LocalDate.of(1995, 5, 15));

        return request;
    }
}