package com.imooc.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VlogBO {
    private String id;
//    @NotBlank(message = "发布者信息不能为空")
    private String vlogerId;
//    @NotBlank(message = "获取视频url出错")
    private String url;
    private String cover;
    private String title;
    private Integer width;
    private Integer height;
    private Integer likeCounts;
    private Integer commentsCounts;
}