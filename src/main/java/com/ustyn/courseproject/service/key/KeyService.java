package com.ustyn.courseproject.service.key;

import com.ustyn.courseproject.document.user.Key;

public interface KeyService {
    Key save(Key key);
    void delete(Key key);
    Key findById(String id);
}
