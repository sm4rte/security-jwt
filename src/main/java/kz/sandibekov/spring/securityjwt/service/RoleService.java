package kz.sandibekov.spring.securityjwt.service;

import kz.sandibekov.spring.securityjwt.model.entity.Role;
import kz.sandibekov.spring.securityjwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
