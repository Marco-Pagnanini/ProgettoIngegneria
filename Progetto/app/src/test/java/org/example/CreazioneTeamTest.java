package org.example;

import org.example.Api.Controllers.TeamController;
import org.example.Api.Models.Request.TeamRequest;
import org.example.Application.Service.TeamService;
import org.example.Application.Validator.TeamValidator;
import org.example.Core.models.Team;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreazioneTeamTest {
    private TeamController controller;
    private TeamService service;
    private UnitOfWork unitOfWork;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new SegnalazioneRepository(),
                new UserRepository()
                );
        service = new TeamService(unitOfWork, new TeamValidator(unitOfWork));
        controller = new TeamController(service);
    }

    @Test
    public void testCreazioneTeamValido() {
        TeamRequest request = createValidRequest();

        Team response = controller.addTeam(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("Team Alpha", response.getNome());
        Assert.assertNotNull(response.getTeamLeader());
        Assert.assertFalse(response.getMembriTeam().isEmpty());
    }

    @Test
    public void testCreazioneTeamConNomeDuplicato() {
        TeamRequest request1 = createValidRequest();
        TeamRequest request2 = createValidRequest();

        Team response1 = controller.addTeam(request1);
        Team response2 = controller.addTeam(request2);

        Assert.assertNotNull(response1);
        Assert.assertNull(response2);
    }

    @Test
    public void testCreazioneTeamSenzaNome() {
        TeamRequest request = createValidRequest();
        request.setTeamName(null);

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamConNomeVuoto() {
        TeamRequest request = createValidRequest();
        request.setTeamName("");

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamConNomeSpazi() {
        TeamRequest request = createValidRequest();
        request.setTeamName("   ");

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamSenzaLeader() {
        TeamRequest request = createValidRequest();
        request.setIdTeamLeader(null);

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamSenzaMembri() {
        TeamRequest request = createValidRequest();
        request.setIdMembriDelTeam(Arrays.asList());

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamConMembriNull() {
        TeamRequest request = createValidRequest();
        request.setIdMembriDelTeam(null);

        Team response = controller.addTeam(request);

        Assert.assertNull(response);
    }

    @Test
    public void testCreazioneTeamConUnSoloMembro() {
        TeamRequest request = createValidRequest();
        request.setIdMembriDelTeam(Arrays.asList(2L));

        Team response = controller.addTeam(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getMembriTeam().size());
    }

    @Test
    public void testCreazioneTeamConMultipliMembri() {
        TeamRequest request = createValidRequest();
        request.setIdMembriDelTeam(Arrays.asList(2L, 3L, 4L, 5L));

        Team response = controller.addTeam(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(4, response.getMembriTeam().size());
    }

    @Test
    public void testCreazioneTeamConNomiDiversi() {
        TeamRequest request1 = createValidRequest();
        request1.setTeamName("Team Alpha");

        TeamRequest request2 = createValidRequest();
        request2.setTeamName("Team Beta");
        request2.setIdTeamLeader(6L);

        Team response1 = controller.addTeam(request1);
        Team response2 = controller.addTeam(request2);

        Assert.assertNotNull(response1);
        Assert.assertNotNull(response2);
        Assert.assertNotEquals(response1.getNome(), response2.getNome());
    }

    @Test
    public void testCreazioneTeamConNomeCaseInsensitive() {
        TeamRequest request1 = createValidRequest();
        request1.setTeamName("Team Alpha");

        TeamRequest request2 = createValidRequest();
        request2.setTeamName("TEAM ALPHA");
        request2.setIdTeamLeader(6L);

        Team response1 = controller.addTeam(request1);
        Team response2 = controller.addTeam(request2);

        Assert.assertNotNull(response1);
        Assert.assertNull(response2);
    }

    @Test
    public void testGetTeamById() {
        TeamRequest request = createValidRequest();
        Team created = controller.addTeam(request);

        Team retrieved = service.getTeamById(created.getId());

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(created.getId(), retrieved.getId());
        Assert.assertEquals(created.getNome(), retrieved.getNome());
    }

    @Test
    public void testGetAllTeams() {
        TeamRequest request1 = createValidRequest();
        request1.setTeamName("Team Alpha");

        TeamRequest request2 = createValidRequest();
        request2.setTeamName("Team Beta");
        request2.setIdTeamLeader(6L);

        controller.addTeam(request1);
        controller.addTeam(request2);

        Assert.assertEquals(2, service.getAllTeams().size());
    }

    @Test
    public void testDeleteTeam() {
        TeamRequest request = createValidRequest();
        Team created = controller.addTeam(request);

        Team deleted = service.deleteTeam(created.getId());

        Assert.assertNotNull(deleted);
        Assert.assertEquals(created.getId(), deleted.getId());
    }

    @Test
    public void testUpdateTeam() {
        TeamRequest request = createValidRequest();
        Team created = controller.addTeam(request);

        created.setNome("Team Updated");
        Team updated = service.updateTeam(created);

        Assert.assertNotNull(updated);
        Assert.assertEquals("Team Updated", updated.getNome());
    }

    private TeamRequest createValidRequest() {
        TeamRequest request = new TeamRequest();
        request.setTeamName("Team Alpha");
        request.setIdTeamLeader(1L);
        request.setIdMembriDelTeam(Arrays.asList(2L, 3L));
        return request;
    }
}