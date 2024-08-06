package com.softeem.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultInfo<T> {
    private Integer code;
    private String message;
    private T data;
}
