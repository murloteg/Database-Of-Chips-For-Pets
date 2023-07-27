package ru.nsu.sberlab.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "social_networks")
@Data
@NoArgsConstructor
public class SocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_network_id")
    private Long socialNetworkId;

    @Column(name = "social_network_name")
    private String socialNetworkName;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
