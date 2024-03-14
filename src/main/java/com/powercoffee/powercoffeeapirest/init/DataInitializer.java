package com.powercoffee.powercoffeeapirest.init;

import com.powercoffee.powercoffeeapirest.model.Role;
import com.powercoffee.powercoffeeapirest.model.enums.ERole;
import com.powercoffee.powercoffeeapirest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initializeRoles();
    }

    private void initializeRoles() {
        Arrays.stream(ERole.values())
                .forEach(role -> {
                    if (roleRepository.findByName(role).isEmpty()) {
                        Role newRole = new Role();
                        newRole.setName(role);
                        roleRepository.save(newRole);
                    }
                });
    }
}
