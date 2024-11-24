package com.ustyn.courseproject.service.role;

import com.ustyn.courseproject.document.user.Role;

import java.util.Collection;

public interface RoleService {
    Collection<Role> findAll();
    Role findById(String id);
    Role save(Role role);
    void deleteById(String id);
}
