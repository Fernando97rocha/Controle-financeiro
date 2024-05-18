package com.app.controlefinanceiro.dto.authentication;

import com.app.controlefinanceiro.model.userRole.UserRole;

public record RegisterDto(String userId, String login, String password, UserRole role) {
}
