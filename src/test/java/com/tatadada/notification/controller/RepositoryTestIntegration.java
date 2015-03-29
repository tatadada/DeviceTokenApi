package com.tatadada.notification.controller;

import com.tatadada.notification.NotificationApplication;
import com.tatadada.notification.model.DeviceToken;
import com.tatadada.notification.model.api.DeviceTokenRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

/**
 * Created by mbogheti on 29/03/15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationApplication.class)
@ActiveProfiles("test")
public class RepositoryTestIntegration {

    @Autowired
    DeviceTokenRepository repository;

    @Test
    public void findsAllNotes() {
        final Collection<DeviceToken> all = this.repository.findAll();
        Assert.assertEquals(0, all.size());
    }

}