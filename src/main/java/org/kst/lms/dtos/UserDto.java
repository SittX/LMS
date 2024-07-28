package org.kst.lms.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String contactNumber;
    private String address;
    private String gender;
    private String guardianName;
    private String guardianContactNumber;
    private List<Long> roleIds;
}
