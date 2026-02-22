package com.auth.auth_app.entity;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name="users")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="user_id")
	private UUID id;
	
	@Column(name="email",unique=true)
	private String email;
	
	
	private String name;
	private String password;
	private String image;
	private boolean enable=true;
	private Instant createdAt=Instant.now();
	private Instant updatedAt=Instant.now();
	
	//if we don't provide any provide then we can use Provider.LOCAL
	@Enumerated(EnumType.STRING)
	private Provider provider;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns =@JoinColumn(name="role_id"))
	
	private Set<Role>roles=new HashSet<>();
	
	//jab bhi database mai jyy gi entity yeah method chal jyy ga 
	@PrePersist
	protected void onCreate() {
		Instant now=Instant.now();
		if(createdAt==null) {
			createdAt=now;
			updatedAt=now;
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
	List<SimpleGrantedAuthority>authorities=roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).toList();
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
