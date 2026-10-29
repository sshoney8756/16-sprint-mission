package com.sprint.mission.discodeit.Dto.Channel;

import java.util.List;

public record ChannelFindAllResponse(
        List<ChannelFindResponse> channels
) {
    public static ChannelFindAllResponse from(List<ChannelFindResponse> channelFindResponses) {
        return new ChannelFindAllResponse(channelFindResponses);
    }
}
