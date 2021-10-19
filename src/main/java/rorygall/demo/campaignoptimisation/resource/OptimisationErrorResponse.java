package rorygall.demo.campaignoptimisation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptimisationErrorResponse {

    private int status;
    private String message;
    private long timestamp;

}
