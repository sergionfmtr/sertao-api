package com.clinica.sertao_api.infra.exception;

public record ValidationError(String field, String message) {
}
