package com.crazygeniuses.notifier.evenotifier.data.entity;

import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications_history", indexes = {@Index(name = "notification_history_uuid_index", columnList = "uuid")})
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class NotificationHistory extends BaseEntity {

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
    private NotificationServiceType type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String name;

    @Type( type = "json" )
    @Column(name = "additional", columnDefinition = "json")
    private HashMap<String, Object> additionalData;
}
