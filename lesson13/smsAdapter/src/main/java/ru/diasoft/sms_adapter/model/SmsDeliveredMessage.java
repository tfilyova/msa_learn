/*
 * Created by DQCodegen
 */
package ru.diasoft.sms_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class SmsDeliveredMessage {

    @JsonProperty("guid")
    private String guid;

}
