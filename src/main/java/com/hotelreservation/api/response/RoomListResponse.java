package com.hotelreservation.api.response;

import com.hotelreservation.api.dto.RoomDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class RoomListResponse extends BaseResponse {
    private List<RoomDTO> roomDTOList;
}
