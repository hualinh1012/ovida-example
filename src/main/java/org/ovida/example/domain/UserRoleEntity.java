package org.ovida.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userroles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;

    private int userId;

    private int roleId;

}
