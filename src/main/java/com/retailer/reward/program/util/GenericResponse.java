package com.retailer.reward.program.util;

import lombok.Builder;

@Builder
public record GenericResponse<T>(boolean success, String message, T data)  {
}
