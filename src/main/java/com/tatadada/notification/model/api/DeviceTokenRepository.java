package com.tatadada.notification.model.api;

import com.tatadada.notification.model.DeviceToken;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.tatadada.notification.model.DeviceToken}
 * objects.
 * @author Mida Boghetich
 */
public interface DeviceTokenRepository extends Repository<DeviceToken, Long> {

    /**
     * Deletes a DeviceToken entry from the database.
     * @param deleted   The deleted DeviceToken entry.
     */
    void delete(DeviceToken deleted);

    /**
     * Finds all todo entries from the database.
     * @return  The information of all todo entries that are found from the database.
     */
    Collection<DeviceToken> findAll();

    /**
     * Finds the information of a single DeviceToken entry.
     * @param id    The id of the requested DeviceToken entry.
     * @return      The information of the found DeviceToken entry. If no DeviceToken entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<DeviceToken> findOne(Long id);

    /**
     * Saves a new DeviceToken entry to the database.
     * @param saved The information of the saved DeviceToken entry.
     * @return      The information of the saved DeviceToken entry.
     */
    DeviceToken save(DeviceToken saved);

}
