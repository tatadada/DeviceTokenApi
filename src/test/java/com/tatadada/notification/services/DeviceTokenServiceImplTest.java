package com.tatadada.notification.services;

import com.tatadada.notification.TatadadaBaseTest;
import com.tatadada.notification.dto.DeviceTokenDTO;
import com.tatadada.notification.model.DeviceToken;
import com.tatadada.notification.model.api.DeviceTokenRepository;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * Created by mbogheti on 29/03/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeviceTokenServiceImplTest extends TatadadaBaseTest {

    public static final String MY_DEVICE_TOKEN = "myDeviceToken";
    public static final String MY_APPLICATION_IDENTIFIER = "myApplicationIdentifier";
    public static final String MY_TATADADA_DEVICE_ID = "myTatadadaDeviceId";
    @Mock
    private DeviceTokenRepository repository;

    DeviceTokenService deviceTokenService;

    @Before
    public void setUp() {
        this.deviceTokenService = new DeviceTokenServiceImpl(repository);
    }

    @Test
    public void create_shouldCreateNewEntity() throws Exception {
        DeviceTokenDTO deviceTokenDTO = new DeviceTokenDTO(null, MY_DEVICE_TOKEN, MY_APPLICATION_IDENTIFIER, MY_TATADADA_DEVICE_ID);

        when(repository.save(isA(DeviceToken.class))).thenAnswer(invocation -> (DeviceToken) invocation.getArguments()[0]);

        deviceTokenService.create(deviceTokenDTO);

        ArgumentCaptor<DeviceToken> savedTodoArgument = ArgumentCaptor.forClass(DeviceToken.class);

        verify(repository, times(1)).save(savedTodoArgument.capture());
        verifyNoMoreInteractions(repository);

        DeviceToken savedTodo = savedTodoArgument.getValue();


        Assert.assertEquals(savedTodo.getApplicationIdentifier(), MY_APPLICATION_IDENTIFIER);
    }

    @Test
    public void findAll_OneDeviceTokenFound_ShouldReturnOneElement() {
        DeviceToken expected = DeviceToken.getBuilder()
                .tatadadaDeviceToken(MY_TATADADA_DEVICE_ID)
                .applicationIdentifier(MY_APPLICATION_IDENTIFIER)
                .deviceToken(MY_DEVICE_TOKEN)
                .build();
        ReflectionTestUtils.setField(expected, "id", 57);

        when(repository.findAll()).thenReturn(Arrays.asList(expected));

        Collection<DeviceTokenDTO> todoEntries = deviceTokenService.findAll();
        assertThat(todoEntries, IsCollectionWithSize.hasSize(Matchers.greaterThan(0)));

        final DeviceTokenDTO next = todoEntries.iterator().next();

        assertThat(next.getId(), Is.is(57L));
    }

}