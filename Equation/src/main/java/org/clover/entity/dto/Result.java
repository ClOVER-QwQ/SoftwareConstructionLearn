package org.clover.entity.dto;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
}
