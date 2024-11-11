package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.MissionRepository;

@Service
@RequiredArgsConstructor // final 붙은 애들만 생성자 생성!
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;

    @Override
    public boolean existsById(Long id) {
        return missionRepository.existsById(id);
    }
}
