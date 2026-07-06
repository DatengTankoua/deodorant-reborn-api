package com.deodorantreborn.api.plugindownload.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "plugin_downloads")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PluginDownload {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "downloaded_at", updatable = false)
    private OffsetDateTime downloadedAt;

    @PrePersist
    protected void onCreate() {
        downloadedAt = OffsetDateTime.now();
    }
}