package eu.powerdot.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {

    private String name;

    private Integer age;

    private boolean active;

    private List<AddressDto> addresses;

}
