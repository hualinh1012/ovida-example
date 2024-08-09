package org.ovida.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    private int permissionId;

    private String permissionName;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDto that = (PermissionDto) o;
        return permissionId == that.permissionId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(permissionId);
    }
}
