package com.crazygeniuses.notifier.evenotifier.data.entity;

import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.model.ServiceAuthenticationType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_credentials", indexes = {@Index(name = "service_credential_uuid_index", columnList = "uuid")})
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class ServiceCredentials extends BaseEntity {

    @Type(type = "uuid-char")
    @Column(name = "uuid", nullable = false, columnDefinition = "varchar(36) default ''")
    private UUID uuid;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private NotificationServiceType serviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private ServiceAuthenticationType authType;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;
}
