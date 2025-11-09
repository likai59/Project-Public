package com.gtalent.project_sb_aws_s3.file;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    private String fileName;
    private byte[] file;
}
