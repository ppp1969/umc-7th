package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    public Store registerStore(StoreRequestDTO.RegisterDto request, String region);
}
