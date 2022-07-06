package com.practice.board.domain.common.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("upload")
public class FileProperties {
    private String path;
}
