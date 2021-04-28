package com.merenda.merenda.api.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgError {
    public String error;
}
