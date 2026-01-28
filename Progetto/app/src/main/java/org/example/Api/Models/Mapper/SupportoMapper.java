package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.SupportoRequest;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Supporto;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;
import org.example.utils.UnitOfWork.IUnitOfWork;

public class SupportoMapper {

    public static Supporto toEntity(SupportoRequest request, IUnitOfWork unitOfWork){
        Supporto supporto = new Supporto();
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(request.getIdHackathon());
        Team team = unitOfWork.teamRepository().getById(request.getIdTeam());
        UserStaff userStaff = unitOfWork.userStaffRepository().getById(request.getIdMentore());

        supporto.setHackathon(hackathon);
        supporto.setTeam(team);
        supporto.setUserStaff(userStaff);

        return supporto;
    }
}
