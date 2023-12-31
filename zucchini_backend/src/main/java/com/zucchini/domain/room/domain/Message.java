package com.zucchini.domain.room.domain;

import com.zucchini.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_no", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @Column(name="content", nullable = false, length = 2000)
    private String content;

    @Column(name="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="is_read")
    private boolean isRead;

    @Builder
    public Message(Room room, User sender, String content, boolean isRead) {
        this.room = room;
        this.sender = sender;
        this.content = content;
        this.isRead = isRead;
    }

    /**
     * 비즈니스 메소드
     */

    // 현재 메세지를 읽었음을 표시함.
    public void read() {
        this.isRead = true;
    }

}
