package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.store.StoreRequestDTO;
import umc.study.web.dto.store.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.JoinStoreResultDTO> join (
            @PathVariable Long regionId,
            @RequestBody @Valid StoreRequestDTO.JoinStoreDto request
    ) {
        Store store = storeCommandService.joinStore(request, regionId);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }
}
