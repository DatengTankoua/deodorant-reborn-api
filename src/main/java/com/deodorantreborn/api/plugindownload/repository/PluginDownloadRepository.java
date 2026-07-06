package com.deodorantreborn.api.plugindownload.repository;

import com.deodorantreborn.api.plugindownload.entity.PluginDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PluginDownloadRepository extends JpaRepository<PluginDownload, UUID> {}