package com.ayou.live.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkerId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String serviceKey;
}
