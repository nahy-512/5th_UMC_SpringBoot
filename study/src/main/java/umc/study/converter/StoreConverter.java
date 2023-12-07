package umc.study.converter;

import umc.study.domain.Store;
import umc.study.web.dto.store.StoreRequestDTO;
import umc.study.web.dto.store.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.JoinStoreResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.JoinStoreResultDTO.builder()
                .storeId(store.getId())
                .build();
    }

    public static Store toStore(StoreRequestDTO.JoinStoreDto request) {

        return Store.builder()
                .name(request.getName())
                .information(request.getInformation())
                .address(request.getAddress())
                .category(request.getCategory())
                .build();
    }
}
