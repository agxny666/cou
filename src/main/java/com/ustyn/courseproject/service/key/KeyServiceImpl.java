package com.ustyn.courseproject.service.key;

import com.ustyn.courseproject.document.user.Key;
import com.ustyn.courseproject.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService {

    private final KeyRepository keyRepository;

    @Autowired
    public KeyServiceImpl(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public Key save(Key key) {
        return keyRepository.save(key);
    }

    @Override
    public void delete(Key key) {
        keyRepository.delete(key);
    }

    @Override
    public Key findById(String id) {
        return keyRepository.findById(id).orElse(null);
    }
}
