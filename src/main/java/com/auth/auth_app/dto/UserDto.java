package com.auth.auth_app.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.auth.auth_app.entity.Provider;
import com.auth.auth_app.entity.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
	private UUID id;
	private String email;
	private String name;
	private String password;
	private String image;
	private boolean enable=true;
	private Instant createdAt=Instant.now();
	private Instant updatedAt=Instant.now();
	private Provider provider;	
	private Set<RoleDto>roles=new HashSet<>();	
}


