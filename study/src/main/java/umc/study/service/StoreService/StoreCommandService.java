package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.web.dto.store.StoreRequestDTO;

import javax.transaction.Transactional;

public interface StoreCommandService {
    @Transactional
    public abstract Store joinStore(StoreRequestDTO.JoinStoreDto request, Long regionId);
}
