package co.istad.mbanking.api.notification.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreateNotificationDto(@JsonProperty("included_segments")
                                    String[] includedSegments ,
                                    ContentDto contents) {
}
