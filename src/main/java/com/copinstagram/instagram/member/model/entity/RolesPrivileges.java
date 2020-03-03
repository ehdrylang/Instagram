package com.copinstagram.instagram.member.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

/**
 * Role과 Privilege간의 다대다 관계를 다대일, 일대다 관계로 푸는 엔티티 클래스
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RolesPrivileges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long roleId;
    @Column
    private Long privilegeId;
}
